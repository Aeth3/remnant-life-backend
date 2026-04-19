package com.maiu.remnant_life.presentation.controller;

import com.maiu.remnant_life.domain.model.Role;
import com.maiu.remnant_life.presentation.dto.UserDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.maiu.remnant_life.domain.model.User;



import com.maiu.remnant_life.application.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody User user) {
        UserDto dto = userService.createUser(user);
        return ResponseEntity.status(201).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PutMapping("/assign-role")
    public ResponseEntity<UserDto> assignRole(
            @RequestParam String email,
            @RequestBody Set<Role> roles) {

        UserDto dto = userService.assignRole(email, roles);

        return ResponseEntity.ok(dto);
    }
}