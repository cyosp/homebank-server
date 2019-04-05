package com.cyosp.homebank.server.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class AccountResponse extends RootResponse {

    private OptionsResponse options;

    private Integer pos;

    private Integer type;

    private CurrencyResponse currency;

    private String name;

    private BigDecimal initial;

    private BigDecimal minimum;

    private Long cheque1;

    private Long cheque2;

    private String balance;
}
