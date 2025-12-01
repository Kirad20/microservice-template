package com.template.user.domain.repository;

import com.template.user.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username);
    User save(User user);
    void deleteById(UUID id);
}