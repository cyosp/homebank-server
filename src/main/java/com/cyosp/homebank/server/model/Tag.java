package com.cyosp.homebank.server.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tag {

    @XStreamAsAttribute
    @XStreamAlias("key")
    private Integer key;

    @XStreamAsAttribute
    @XStreamAlias("name")
    private String name;
}
