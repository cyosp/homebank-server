package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

@Data
@XStreamAlias("homebank")
public class HomeBank {

    @XStreamAsAttribute
    private String v;

    @XStreamAsAttribute
    private String d;

    private Properties properties;

    @XStreamImplicit(itemFieldName = "cur")
    private List<Currency> currencies;

    @XStreamImplicit(itemFieldName = "account")
    private List<Account> accounts;

    @XStreamImplicit(itemFieldName = "pay")
    private List<Payee> payees;

    @XStreamImplicit(itemFieldName = "cat")
    private List<Category> categories;

    @XStreamImplicit(itemFieldName = "tag")
    private List<Tag> tags;

    @XStreamImplicit(itemFieldName = "fav")
    private List<Favorite> favorites;

    @XStreamImplicit(itemFieldName = "ope")
    private List<Operation> operations;
}
