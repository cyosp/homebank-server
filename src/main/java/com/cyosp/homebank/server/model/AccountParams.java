package com.cyosp.homebank.server.model;

import lombok.Data;

@Data
public class AccountParams {

    private boolean closed;

    private boolean summaryExclude;

    private boolean budgetExclude;

    private boolean reportsExclude;

    public AccountParams(int decimalOptionsValue) {
        closed = (decimalOptionsValue & 0x02) > 0;
        summaryExclude = (decimalOptionsValue & 0x10) > 0;
        budgetExclude = (decimalOptionsValue & 0x20) > 0;
        reportsExclude = (decimalOptionsValue & 0x40) > 0;
    }
}
