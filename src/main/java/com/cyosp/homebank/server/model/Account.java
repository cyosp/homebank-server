package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Account {

    @XStreamAsAttribute
    @XStreamAlias("key")
    private Integer key;

    @XStreamAsAttribute
    @XStreamAlias("flags")
    private Integer flags;

    @XStreamAsAttribute
    @XStreamAlias("pos")
    private Integer pos;

    @XStreamAsAttribute
    @XStreamAlias("type")
    private Integer type;

    @XStreamAsAttribute
    @XStreamAlias("curr")
    private Integer curr;

    @XStreamAsAttribute
    @XStreamAlias("name")
    private String name;

    @XStreamAsAttribute
    @XStreamAlias("initial")
    private BigDecimal initial;

    @XStreamAsAttribute
    @XStreamAlias("minimum")
    private BigDecimal minimum;

    @XStreamAsAttribute
    @XStreamAlias("cheque1")
    private Long cheque1;

    @XStreamAsAttribute
    @XStreamAlias("cheque2")
    private Long cheque2;

    //----------------------------------

    @XStreamOmitField
    private BigDecimal balance;

    @XStreamOmitField
    private Options options;

    @XStreamOmitField
    private Currency currency;
}
