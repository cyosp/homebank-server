package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Properties {

    @XStreamAsAttribute
    @XStreamAlias("title")
    private String title;

    @XStreamAsAttribute
    @XStreamAlias("curr")
    private Integer curr;

    @XStreamAsAttribute
    @XStreamAlias("auto_smode")
    private Integer autoSmode;

    @XStreamAsAttribute
    @XStreamAlias("auto_weekday")
    private Integer autoWeekday;
}
