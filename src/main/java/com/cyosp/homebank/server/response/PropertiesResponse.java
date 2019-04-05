package com.cyosp.homebank.server.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertiesResponse {

    private String title;

    private Integer curr;

    private Integer autoSmode;

    private Integer autoWeekday;
}
