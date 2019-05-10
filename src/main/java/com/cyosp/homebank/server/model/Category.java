package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;

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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Map<Account, BigDecimal> balances;
}
