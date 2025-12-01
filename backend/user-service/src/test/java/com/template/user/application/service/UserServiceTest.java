package com.template.user.application.service;

import com.template.user.domain.model.User;
import com.template.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testuser", "test@example.com", "password", Collections.emptySet());
    }

    @Test
    void whenGetUserById_thenReturnUser() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User found = userService.getUserById(user.getId());
        assertEquals(user.getUsername(), found.getUsername());
    }

    @Test
    void whenCreateUser_thenReturnSavedUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        User created = userService.createUser(new User("newuser", "new@example.com", "password", Collections.emptySet()));
        assertNotNull(created);
        assertEquals(user.getUsername(), created.getUsername());
    }
}