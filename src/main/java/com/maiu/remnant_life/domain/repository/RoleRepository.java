package com.maiu.remnant_life.domain.repository;

import com.maiu.remnant_life.domain.model.Role;

import java.util.*;

public interface RoleRepository {
    Role save(Role role);

    List<Role> findAll();

    Optional<Role> findById(Long id);

}
