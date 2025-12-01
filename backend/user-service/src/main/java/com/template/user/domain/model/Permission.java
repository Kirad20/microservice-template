package com.template.user.domain.model;

import java.util.UUID;

public class Permission {

    private UUID id;
    private String name;

    public Permission(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}