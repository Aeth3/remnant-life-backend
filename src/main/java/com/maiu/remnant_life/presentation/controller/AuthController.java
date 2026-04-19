package com.maiu.remnant_life.presentation.controller;

import com.maiu.remnant_life.domain.model.LoginRequest;
import com.maiu.remnant_life.presentation.dto.AuthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maiu.remnant_life.application.service.AuthService;
import com.maiu.remnant_life.domain.model.User;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        authService.register(user);
        return ResponseEntity.status(201).body(
                Map.of("message", "User registered successfully")
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody LoginRequest request) {
        AuthDto dto = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(dto);
    }
}
