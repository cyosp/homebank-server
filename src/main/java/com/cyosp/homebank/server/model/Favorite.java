package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Favorite {

    @XStreamOmitField
    private Integer key;

    @XStreamAsAttribute
    private BigDecimal amount;

    @XStreamAsAttribute
    private Integer account;

    @XStreamAsAttribute
    private Integer paymode;

    @XStreamAsAttribute
    private Integer flags;

    @XStreamAsAttribute
    private Integer payee;

    @XStreamAsAttribute
    private Integer category;

    @XStreamAsAttribute
    private String wording;

    @XStreamAsAttribute
    private Long nextdate;

    @XStreamAsAttribute
    private Integer every;

    @XStreamAsAttribute
    private Integer unit;

    @XStreamAsAttribute
    private Integer limit;
}
