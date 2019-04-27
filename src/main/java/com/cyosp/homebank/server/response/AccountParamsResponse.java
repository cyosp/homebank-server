package com.cyosp.homebank.server.response;

import lombok.Data;

@Data
public class AccountParamsResponse {

    private boolean closed;

    private boolean summaryExclude;

    private boolean budgetExclude;

    private boolean reportsExclude;
}
