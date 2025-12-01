package com.template.user.application.service;

import com.template.user.application.port.in.UserServicePort;
import com.template.user.domain.model.User;
import com.template.user.domain.model.UserCreatedEvent;
import com.template.user.domain.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserServicePort {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    public UserService(UserRepository userRepository, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        eventPublisher.publishEvent(new UserCreatedEvent(this, savedUser));
        return savedUser;
    }
}