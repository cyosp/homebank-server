package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;

import static com.cyosp.homebank.server.model.HomeBank.NO_KEY;

@Data
public class Payee {

    @XStreamOmitField
    public static final Payee NO_PAYEE = new Payee();

    static {
        NO_PAYEE.key = NO_KEY;
        NO_PAYEE.name = "";
    }

    @XStreamAsAttribute
    private Integer key;

    @XStreamAsAttribute
    private String name;

    @XStreamOmitField
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Map<Account, BigDecimal> balances;
}
