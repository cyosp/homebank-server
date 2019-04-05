package com.cyosp.homebank.server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Options {

    public static final Integer IS_ACCOUNT_CLOSED_BITMASK = 0x02;
    private Boolean accountClosed = false;

    public static final Integer IS_ACCOUNT_SUMMARY_EXCLUDE_BITMASK = 0x10;
    private Boolean accountSummaryExclude = false;

    public static final Integer IS_ACCOUNT_BUDGET_EXCLUDE_BITMASK = 0x20;
    private Boolean accountBudgetExclude = false;

    public static final Integer IS_ACCOUNT_REPORTS_EXCLUDE_BITMASK = 0x40;
    private Boolean accountReportsExclude = false;

    public void setOptions(Integer decimalOptionsValue) {
        accountClosed = (decimalOptionsValue & IS_ACCOUNT_CLOSED_BITMASK) > 0;

        accountSummaryExclude = (decimalOptionsValue & IS_ACCOUNT_SUMMARY_EXCLUDE_BITMASK) > 0;

        accountBudgetExclude = (decimalOptionsValue & IS_ACCOUNT_BUDGET_EXCLUDE_BITMASK) > 0;

        accountReportsExclude = (decimalOptionsValue & IS_ACCOUNT_REPORTS_EXCLUDE_BITMASK) > 0;
    }
}
