package com.payment.admin.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSearchRequest {

    private String userName;
    private String email;
    private Integer limit;
    private Integer page;
}
