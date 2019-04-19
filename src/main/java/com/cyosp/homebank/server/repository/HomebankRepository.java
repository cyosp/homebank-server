package com.cyosp.homebank.server.repository;

import com.cyosp.homebank.server.model.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

@Repository
public class HomebankRepository {

    private final Integer NO_KEY = -1;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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
        LOGGER.info("Loaded: " + xstream.toXML(homeBank));
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

    public Stream<Operation> operations(Account account) {
        return homeBank.getOperations().stream()
                .filter(operation -> ofNullable(operation.getAccount()).orElse(NO_KEY).equals(account.getKey()));
    }

    public List<Category> getCategoriesByAccount(int id) {
        throw new UnsupportedOperationException();
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

    public List<Operation> operations() {
        return homeBank.getOperations();
    }

    public List<Tag> getTags() {
        return homeBank.getTags();
    }
}
