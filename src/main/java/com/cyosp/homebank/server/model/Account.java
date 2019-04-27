package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

import java.math.BigDecimal;

@Data
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
}
