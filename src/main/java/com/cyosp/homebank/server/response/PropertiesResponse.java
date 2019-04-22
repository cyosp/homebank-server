package com.cyosp.homebank.server.response;

import lombok.Data;

@Data
public class PropertiesResponse {

    private String title;

    private Integer curr;

    private Integer autoSmode;

    private Integer autoWeekday;
}
