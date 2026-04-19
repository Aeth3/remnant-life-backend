package com.maiu.remnant_life.domain.repository;

import java.util.List;
import java.util.Optional;

import com.maiu.remnant_life.domain.model.User;

public interface UserRepository {
    User save(User user);

    List<User> findAll();

    Optional<User> findByEmail(String email);
}
