package com.cyosp.homebank.server.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OperationResponse extends RootResponse {

    private String date;

    private String amount;

    private String balance;

    private String paymode;

    private Integer flags;

    private String payee;

    private String wording;

    private String category;
}
