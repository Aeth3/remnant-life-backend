package com.maiu.remnant_life.presentation.dto;

import jakarta.validation.constraints.*;

public class RegisterRequest {
    
    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
