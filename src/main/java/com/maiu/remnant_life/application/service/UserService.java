package com.maiu.remnant_life.application.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.maiu.remnant_life.domain.model.Role;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maiu.remnant_life.domain.model.User;
import com.maiu.remnant_life.domain.repository.UserRepository;
import com.maiu.remnant_life.presentation.dto.UserDto;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UserDto createUser(User user) {

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }

        String email = user.getEmail().trim().toLowerCase();

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        user.setEmail(email);
        user.setPassword(encoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        return new UserDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }

    public List<UserDto> getUsers() {

        return userRepository.findAll().stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail()))
                .toList();
    }

    public UserDto assignRole(String email, Set<Role> roles) {

        String normalizedEmail = email.trim().toLowerCase();

        User user = userRepository.findByEmail(normalizedEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ⚠️ Optional: validate roles not empty
        if (roles == null || roles.isEmpty()) {
            throw new RuntimeException("Roles cannot be empty");
        }

        user.setRoles(roles);

        User updatedUser = userRepository.save(user);

        return new UserDto(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getEmail()
        );
    }
}
