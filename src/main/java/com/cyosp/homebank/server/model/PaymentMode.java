package com.cyosp.homebank.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
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

    public static final PaymentMode NO_PAYMENT_MODE_DEFINED = new PaymentMode(null, "", true);

    @Getter
    public static List<PaymentMode> paymentModes = new ArrayList<PaymentMode>() {
        {
            add(NO_PAYMENT_MODE_DEFINED);
            add(new PaymentMode(1, CREDIT_CARD, true));
            add(new PaymentMode(2, CHECK, true));
            add(new PaymentMode(3, CASH, true));
            add(new PaymentMode(4, TRANSFER, false));
            add(new PaymentMode(5, INTERNAL_TRANSFER, false));
            add(new PaymentMode(6, DEBIT_CARD, true));
            add(new PaymentMode(7, STANDING_ORDER, false));
            add(new PaymentMode(8, ELECTRONIC_PAYMENT, false));
            add(new PaymentMode(9, DEPOSIT, true));
            add(new PaymentMode(10, FI_FEE, false));
            add(new PaymentMode(11, DIRECT_DEBIT, false));
        }
    };

    private Integer code;

    private String name;

    private Boolean managed;
}
