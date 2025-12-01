package com.template.user.application.port.in;

import com.template.user.domain.model.User;

import java.util.UUID;

public interface UserServicePort {
    User getUserById(UUID id);
    User getUserByUsername(String username);
    User createUser(User user);
}