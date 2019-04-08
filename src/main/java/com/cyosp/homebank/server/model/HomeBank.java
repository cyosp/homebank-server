package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Getter;
import lombok.Setter;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XStreamAlias("homebank")
public class HomeBank {

    private static final int BIGDECIMAL_SCALE_0 = 0;
    private static final int BIGDECIMAL_SCALE_2 = 2;
    private static final RoundingMode BIGDECIMAL_ROUNDINGMODE = RoundingMode.HALF_UP;

    @XStreamAlias("v")
    @XStreamAsAttribute
    private String v;

    @XStreamAlias("d")
    @XStreamAsAttribute
    private String d;

    @XStreamAlias("mpa")
    private String mpa;

    @XStreamAlias("properties")
    private Properties properties;

    @XStreamImplicit(itemFieldName = "cur")
    private List<Currency> currencies = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "account")
    private List<Account> accounts = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "pay")
    private List<Payee> payees = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "cat")
    private List<Category> categories = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "tag")
    private List<Tag> tags = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "fav")
    private List<Favorite> favorites = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "ope")
    private List<Operation> operations = new ArrayList<>();
}
