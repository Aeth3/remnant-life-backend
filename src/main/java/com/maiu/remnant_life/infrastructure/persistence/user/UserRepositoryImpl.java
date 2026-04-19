package com.maiu.remnant_life.infrastructure.persistence.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.maiu.remnant_life.domain.model.User;
import com.maiu.remnant_life.domain.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryImpl(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {

        UserEntity entity;

        // ✅ Check if updating or creating
        if (user.getId() != null) {
            entity = jpaRepository.findById(user.getId())
                    .orElse(new UserEntity());
        } else {
            entity = new UserEntity();
        }

        // ✅ Set fields
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());

        // ⚠️ If you have roles, set them here too
        // entity.setRoles(mapRoles(user.getRoles()));

        UserEntity saved = jpaRepository.save(entity);

        return toDomain(saved);
    }
    private User toDomain(UserEntity e) {
        User u = new User();
        u.setId(e.getId());
        u.setName(e.getName());
        u.setEmail(e.getEmail());
        u.setPassword(e.getPassword());
        return u;
    }
    @Override
    public List<User> findAll() {
        return jpaRepository.findAll().stream().map(e -> {
            User u = new User();
            u.setId(e.getId());
            u.setName(e.getName());
            u.setEmail(e.getEmail());
            u.setPassword(e.getPassword());
            return u;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(e -> {
            User u = new User();
            u.setId(e.getId());
            u.setName(e.getName());
            u.setEmail(e.getEmail());
            u.setPassword(e.getPassword());
            return u;
        });
    }
}
