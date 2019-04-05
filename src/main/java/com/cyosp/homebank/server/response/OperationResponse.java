package com.cyosp.homebank.server.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationResponse extends RootResponse {

    private Long date;

    private String dateFormatted;

    private String amount;

    private String balance;

    private Integer account;

    private Integer paymode;

    private String paymodeName;

    private Integer flags;

    private Integer payee;

    private String payeeName;

    private String wording;

    private Integer category;

    private String categoryName;
}
