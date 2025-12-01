package com.template.user.domain.model;

import java.util.Set;
import java.util.UUID;

public class Role {

    private UUID id;
    private String name;
    private Set<Permission> permissions;

    public Role(String name, Set<Permission> permissions) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.permissions = permissions;
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

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}