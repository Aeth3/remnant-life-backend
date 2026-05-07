package com.maiu.remnant_life.modules.auth.domain.repository;

import java.util.List;
import java.util.Optional;

import com.maiu.remnant_life.modules.auth.domain.model.User;



public interface UserRepository {
    User save(User user);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    void deleteUser(Long id);
}
