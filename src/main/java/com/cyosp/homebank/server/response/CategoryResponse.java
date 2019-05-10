package com.cyosp.homebank.server.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {

    private String name;

    private String balance;
}
