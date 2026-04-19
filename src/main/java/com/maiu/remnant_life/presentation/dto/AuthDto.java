package com.maiu.remnant_life.presentation.dto;

public class AuthDto {
    private final String token;

    public AuthDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
