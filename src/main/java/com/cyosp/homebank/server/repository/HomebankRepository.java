package com.cyosp.homebank.server.repository;

import com.cyosp.homebank.server.model.*;
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
import java.util.function.Predicate;

import static java.math.BigDecimal.ZERO;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Repository
public class HomebankRepository {

    private final Integer NO_KEY = -1;
    private final Integer FIRST_KEY = 1;

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

            BigDecimal balance = account.getInitial();
            int key = FIRST_KEY;
            for (Operation operation : operations(account)) {
                operation.setKey(key++);

                ofNullable(operation.getCategoryKey()).ifPresent(categoryKey ->
                {
                    Predicate<Category> categoryPredicate = category -> category.getKey().equals(operation.getCategoryKey());

                    Category category = homeBank.getCategories().stream()
                            .filter(categoryPredicate)
                            .findFirst()
                            .orElseGet(() -> {
                                throw new IllegalStateException();
                            });
                    operation.setCategory(category);

                    if (account.getCategories().stream().noneMatch(categoryPredicate)) {
                        account.getCategories().add(category);

                        if (!ofNullable(category.getBalances()).isPresent())
                            category.setBalances(new HashMap<>());

                        if (!ofNullable(category.getBalances().get(account)).isPresent())
                            category.getBalances().put(account, ZERO);
                    }

                    category.getBalances().put(account, category.getBalances().get(account).add(operation.getAmount()));
                });

                Payee payee = homeBank.getPayees().stream()
                        .filter(pay -> pay.getKey().equals(operation.getPayeeKey()))
                        .findFirst()
                        .orElseGet(Payee::new);
                operation.setPayee(payee);

                balance = balance.add(operation.getAmount());
                operation.setBalance(balance);
            }
            account.setBalance(balance);
        });

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

    public List<Operation> operations(Account account) {
        return homeBank.getOperations().stream()
                .filter(operation -> ofNullable(operation.getAccount()).orElse(NO_KEY).equals(account.getKey()))
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
