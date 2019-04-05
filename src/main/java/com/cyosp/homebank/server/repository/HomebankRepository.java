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

@Repository
public class HomebankRepository {

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
        homeBank.checkVersion();
    }

    public HomeBank getInfos() {
        return homeBank;
    }

    public Properties getProperties() {
        return homeBank.getProperties();
    }

    public List<Account> getAccounts() {
        return homeBank.getAccounts();
    }

    public List<Operation> getOperationsByAccount(int id) {
        throw new UnsupportedOperationException();
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

    public List<Favorite> getFavorites() {
        return homeBank.getFavorites();
    }

    public List<Operation> getOperations() {
        return homeBank.getOperations();
    }

    public List<Tag> getTags() {
        return homeBank.getTags();
    }
}
