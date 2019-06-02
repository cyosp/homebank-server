package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Payee {

    @XStreamAsAttribute
    private Integer key;

    @XStreamAsAttribute
    private String name;

    @XStreamOmitField
    private BigDecimal balance;

    @XStreamOmitField
    private Currency currency;
}
