package com.cyosp.homebank.server.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyResponse extends RootResponse {

    private String iso;

    private String name;

    private Character symb;

    private Integer syprf;

    private Character dchar;

    private Character gchar;

    private Integer frac;

    private Integer rate;

    private Integer mdate;
}
