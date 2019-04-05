package com.cyosp.homebank.server.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentMode {

    private static final String CREDIT_CARD = "Credit Card";
    private static final String CHECK = "Check";
    private static final String CASH = "Cash";
    private static final String TRANSFER = "Transfer";
    private static final String INTERNAL_TRANSFER = "Internal Transfer";
    private static final String DEBIT_CARD = "Debit Card";
    private static final String STANDING_ORDER = "Standing order";
    private static final String ELECTRONIC_PAYMENT = "Electronic payment";
    private static final String DEPOSIT = "Deposit";
    private static final String FI_FEE = "FI fee";
    private static final String DIRECT_DEBIT = "Direct debit";

    @Getter
    public static List<PaymentMode> paymentModes = new ArrayList<PaymentMode>() {
        {
            add(new PaymentMode(null, "", true));
            add(new PaymentMode(1, PaymentMode.CREDIT_CARD, true));
            add(new PaymentMode(2, PaymentMode.CHECK, true));
            add(new PaymentMode(3, PaymentMode.CASH, true));
            add(new PaymentMode(4, PaymentMode.TRANSFER, false));
            add(new PaymentMode(5, PaymentMode.INTERNAL_TRANSFER, false));
            add(new PaymentMode(6, PaymentMode.DEBIT_CARD, true));
            add(new PaymentMode(7, PaymentMode.STANDING_ORDER, false));
            add(new PaymentMode(8, PaymentMode.ELECTRONIC_PAYMENT, false));
            add(new PaymentMode(9, PaymentMode.DEPOSIT, true));
            add(new PaymentMode(10, PaymentMode.FI_FEE, false));
            add(new PaymentMode(11, PaymentMode.DIRECT_DEBIT, false));
        }
    };

    private Integer code;

    private String name;

    private Boolean managed;
}
