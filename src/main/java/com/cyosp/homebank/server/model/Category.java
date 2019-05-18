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
public class Category {

    @XStreamOmitField
    public static final Category NO_CATEGORY =  new Category();

    static {
        NO_CATEGORY.key = NO_KEY;
        NO_CATEGORY.name = "";
    }

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
