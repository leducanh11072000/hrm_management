package com.payment.admin.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;
}
