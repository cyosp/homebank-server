package com.cyosp.homebank.server.response;

import com.cyosp.homebank.server.model.Currency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayeeResponse extends RootResponse {

    private String name;

    private String balance;

    private Currency currency;
}
