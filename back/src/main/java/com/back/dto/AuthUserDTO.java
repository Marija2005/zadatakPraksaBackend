package com.back.dto;

import javax.validation.constraints.NotBlank;

public class AuthUserDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public AuthUserDTO() {}

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
