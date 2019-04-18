package com.cyosp.homebank.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
public class PaymentMode {

    public static final PaymentMode NO_PAYMENT_MODE_DEFINED = new PaymentMode(null, "", true);

    public static final List<PaymentMode> PAYMENT_MODES = new ArrayList<PaymentMode>() {
        {
            add(NO_PAYMENT_MODE_DEFINED);
            add(new PaymentMode(1, "Credit Card", true));
            add(new PaymentMode(2, "Check", true));
            add(new PaymentMode(3, "Cash", true));
            add(new PaymentMode(4, "Transfer", false));
            add(new PaymentMode(5, "Internal Transfer", false));
            add(new PaymentMode(6, "Debit Card", true));
            add(new PaymentMode(7, "Standing order", false));
            add(new PaymentMode(8, "Electronic payment", false));
            add(new PaymentMode(9, "Deposit", true));
            add(new PaymentMode(10, "FI fee", false));
            add(new PaymentMode(11, "Direct debit", false));
        }
    };

    private Integer code;

    private String name;

    private Boolean managed;
}
