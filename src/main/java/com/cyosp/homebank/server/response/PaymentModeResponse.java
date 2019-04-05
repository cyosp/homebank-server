package com.cyosp.homebank.server.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentModeResponse {

    private Integer code;

    private String name;

    private Boolean managed;
}
