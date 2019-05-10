package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Account {

    @XStreamAsAttribute
    private Integer key;

    @XStreamAsAttribute
    private Integer flags;

    @XStreamAsAttribute
    private Integer pos;

    @XStreamAsAttribute
    private Integer type;

    @XStreamAsAttribute
    private Integer curr;

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private BigDecimal initial;

    @XStreamAsAttribute
    private BigDecimal minimum;

    @XStreamAsAttribute
    private Long cheque1;

    @XStreamAsAttribute
    private Long cheque2;

    @XStreamOmitField
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Category> categories;

    @XStreamOmitField
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BigDecimal balance;
}
