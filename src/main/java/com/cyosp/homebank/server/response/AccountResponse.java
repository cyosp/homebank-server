package com.cyosp.homebank.server.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountResponse extends RootResponse {

    private AccountParamsResponse params;

    private Integer pos;

    private Integer type;

    private String name;

    private BigDecimal initial;

    private BigDecimal minimum;

    private Long cheque1;

    private Long cheque2;

    private String balance;
}
