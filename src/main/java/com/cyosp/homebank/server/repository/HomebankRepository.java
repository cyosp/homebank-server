package com.cyosp.homebank.server.repository;

import com.cyosp.homebank.server.model.*;
import com.cyosp.homebank.server.request.OperationQueryRequest;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static com.cyosp.homebank.server.model.Category.NO_CATEGORY;
import static com.cyosp.homebank.server.model.HomeBank.NO_KEY;
import static com.cyosp.homebank.server.model.Payee.NO_PAYEE;
import static java.math.BigDecimal.ZERO;
import static java.util.Comparator.comparing;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Repository
public class HomebankRepository {

    private static final Integer FIRST_KEY = 1;

    @Value("${homebank.file}")
    private File homebankFilePath;

    @Value("${version}")
    private String version;

    private HomeBank homeBank;

    private static XStream xstream;

    static {
        // Allow to manage attributes with _ in their names
        xstream = new XStream(new StaxDriver(new NoNameCoder()));

        // Set same class loader than current thread
        // It allows to recognize object in loading
        xstream.setClassLoader(Thread.currentThread().getContextClassLoader());

        xstream.processAnnotations(HomeBank.class);
    }

    @PostConstruct
    public void load() {
        homeBank = (HomeBank) xstream.fromXML(homebankFilePath);
        addMissingData();
    }

    private void addMissingData() {
        homeBank.getAccounts().forEach(account -> {
            account.setCategories(new ArrayList<>());
            account.setPayees(new ArrayList<>());

            BigDecimal balance = account.getInitial();
            int key = FIRST_KEY;
            for (Operation operation : operations(account)) {
                operation.setKey(key++);

                setOperationCategory(account, operation);

                setOperationPayee(account, operation);

                balance = balance.add(operation.getAmount());
                operation.setBalance(balance);
            }
            account.setBalance(balance);
        });

    }

    private void setOperationCategory(Account account, Operation operation) {
        Category operationCategory;
        if (ofNullable(operation.getCategoryKey()).isPresent()) {
            operationCategory = homeBank.getCategories().stream()
                    .filter(category -> category.getKey().equals(operation.getCategoryKey()))
                    .findFirst()
                    .orElseGet(() ->
                    {
                        throw new IllegalStateException();
                    });
        } else
            operationCategory = NO_CATEGORY;
        operation.setCategory(operationCategory);

        if (account.getCategories().stream()
                .noneMatch(category -> category.getKey().equals(operation.getCategoryKey()))) {
            account.getCategories().add(operationCategory);

            if (!ofNullable(operationCategory.getBalances()).isPresent())
                operationCategory.setBalances(new HashMap<>());

            if (!ofNullable(operationCategory.getBalances().get(account)).isPresent())
                operationCategory.getBalances().put(account, ZERO);
        }

        operationCategory.getBalances().put(account, operationCategory.getBalances().get(account).add(operation.getAmount()));
    }

    private void setOperationPayee(Account account, Operation operation) {
        Payee operationPayee;
        if (ofNullable(operation.getPayeeKey()).isPresent()) {
            operationPayee = homeBank.getPayees().stream()
                    .filter(payee -> payee.getKey().equals(operation.getPayeeKey()))
                    .findFirst()
                    .orElseGet(() ->
                    {
                        throw new IllegalStateException();
                    });
        } else
            operationPayee = NO_PAYEE;
        operation.setPayee(operationPayee);

        if (account.getPayees().stream()
                .noneMatch(payee -> payee.getKey().equals(operation.getPayeeKey()))) {
            account.getPayees().add(operationPayee);

            if (!ofNullable(operationPayee.getBalances()).isPresent())
                operationPayee.setBalances(new HashMap<>());

            if (!ofNullable(operationPayee.getBalances().get(account)).isPresent())
                operationPayee.getBalances().put(account, ZERO);
        }

        operationPayee.getBalances().put(account, operationPayee.getBalances().get(account).add(operation.getAmount()));
    }

    public Properties getProperties() {
        return homeBank.getProperties();
    }

    public List<Account> getAccounts() {
        return homeBank.getAccounts();
    }

    public Account account(int id) {
        return homeBank.getAccounts().stream()
                .filter(account -> account.getKey() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    private Stream<Operation> operationsAsStream(Account account) {
        return homeBank.getOperations().stream()
                .filter(operation -> ofNullable(operation.getAccount()).orElse(NO_KEY).equals(account.getKey()));
    }

    private List<Operation> operations(Account account) {
        return operationsAsStream(account)
                .sorted(comparing(Operation::getDate))
                .collect(toList());
    }

    public List<Operation> operations(Account account, OperationQueryRequest operationQueryRequest, long from, long limit) {
        AtomicBoolean fromFound = new AtomicBoolean(false);
        return operationsAsStream(account)
                .filter(operation -> operation.match(operationQueryRequest))
                .sorted(comparing(Operation::getDate).reversed())
                .filter(operation -> {
                    if (!fromFound.get() && (from == NO_KEY || operation.getKey() == from))
                        fromFound.set(true);
                    return fromFound.get();
                })
                .limit(limit)
                .collect(toList());
    }

    public List<Payee> getPayeesByAccount(int id) {
        throw new UnsupportedOperationException();
    }

    public List<Currency> getCurrencies() {
        return homeBank.getCurrencies();
    }

    public Currency currency(Account account) {
        return homeBank.getCurrencies().stream()
                .filter(currency -> ofNullable(currency.getKey()).orElse(NO_KEY).equals(account.getCurr()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public List<Favorite> getFavorites() {
        return homeBank.getFavorites();
    }

    public List<Tag> getTags() {
        return homeBank.getTags();
    }
}
