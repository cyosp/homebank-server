package com.cyosp.homebank.server.response;

import com.cyosp.homebank.server.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Data;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
public class PaymentModeResponse {

    private Integer code;

    private String name;

    private Boolean managed;

    public static PaymentModeResponse from(PaymentMode paymentMode) {
        return new PaymentModeResponse(paymentMode.getCode(), paymentMode.getName(), paymentMode.getManaged());
    }
}
