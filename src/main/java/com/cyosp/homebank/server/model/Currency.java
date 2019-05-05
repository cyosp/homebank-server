package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

@Data
public class Currency {

    @XStreamAsAttribute
    private Integer key;

    @XStreamAsAttribute
    private String iso;

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private Character symb;

    @XStreamAsAttribute
    private Integer syprf;

    @XStreamAsAttribute
    private Character dchar;

    @XStreamAsAttribute
    private Character gchar;

    @XStreamAsAttribute
    private Integer frac;

    @XStreamAsAttribute
    private Integer rate;

    @XStreamAsAttribute
    private Integer mdate;
}
