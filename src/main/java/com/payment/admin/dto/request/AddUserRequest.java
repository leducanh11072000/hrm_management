package com.payment.admin.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddUserRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    private List<AddRoleRequest> roles;
}
