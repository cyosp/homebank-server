package com.cyosp.homebank.server.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionsResponse {

    private Boolean accountClosed;

    private Boolean accountSummaryExclude;

    private Boolean accountBudgetExclude;

    private Boolean accountReportsExclude;
}
