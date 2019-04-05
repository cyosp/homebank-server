package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Favorite {

    @XStreamOmitField
    private Integer key;

    //-----------------------------

    @XStreamAsAttribute
    @XStreamAlias("amount")
    private BigDecimal amount;

    @XStreamAsAttribute
    @XStreamAlias("account")
    private Integer account;

    @XStreamAsAttribute
    @XStreamAlias("paymode")
    private Integer paymode;

    @XStreamAsAttribute
    @XStreamAlias("flags")
    private Integer flags;

    @XStreamAsAttribute
    @XStreamAlias("payee")
    private Integer payee;

    @XStreamAsAttribute
    @XStreamAlias("category")
    private Integer category;

    @XStreamAsAttribute
    @XStreamAlias("wording")
    private String wording;

    @XStreamAsAttribute
    @XStreamAlias("nextdate")
    private Long nextdate;

    @XStreamAsAttribute
    @XStreamAlias("every")
    private Integer every;

    @XStreamAsAttribute
    @XStreamAlias("unit")
    private Integer unit;

    @XStreamAsAttribute
    @XStreamAlias("limit")
    private Integer limit;
}
