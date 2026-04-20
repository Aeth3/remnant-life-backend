package com.maiu.remnant_life.application.service;

import com.maiu.remnant_life.presentation.dto.AuthDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maiu.remnant_life.domain.model.User;
import com.maiu.remnant_life.domain.repository.UserRepository;

import com.maiu.remnant_life.utils.JwtUtil;

import com.maiu.remnant_life.presentation.dto.RegisterRequest;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
            PasswordEncoder encoder,
            JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    public void register(RegisterRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setName(request.getName());
        userRepository.save(user);
    }

    public AuthDto login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(email);

        return new AuthDto(token);
    }
}
