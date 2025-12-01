package com.template.user.application.service;

import com.template.user.domain.model.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    private static final Logger logger = LoggerFactory.getLogger(UserEventListener.class);

    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        logger.info("User created: {}", event.getUser().getUsername());
        // Here you could, for example, send a welcome email
    }
}