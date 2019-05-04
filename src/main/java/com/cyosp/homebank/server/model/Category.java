package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Category {

    @XStreamAsAttribute
    private Integer key;

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private Integer parent;

    @XStreamAsAttribute
    private Integer flags;

    @XStreamOmitField
    private BigDecimal balance;

    @XStreamOmitField
    private Currency currency;
}
