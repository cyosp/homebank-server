package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Category {

    @XStreamAsAttribute
    @XStreamAlias("key")
    private Integer key;

    @XStreamAsAttribute
    @XStreamAlias("parent")
    private Integer parent;

    @XStreamAsAttribute
    @XStreamAlias("flags")
    private Integer flags;

    @XStreamAsAttribute
    @XStreamAlias("name")
    private String name;

    //----------------------------------

    @XStreamOmitField
    private BigDecimal balance;

    @XStreamOmitField
    private Currency currency;
}
