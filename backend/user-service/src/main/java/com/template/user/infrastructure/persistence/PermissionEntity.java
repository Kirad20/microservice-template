package com.template.user.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

import com.template.user.domain.model.Permission;

@Entity
@Table(name = "permissions")
public class PermissionEntity {

    @Id
    private UUID id;
    private String name;

    public static PermissionEntity fromDomain(Permission permission) {
        PermissionEntity entity = new PermissionEntity();
        entity.setId(permission.getId());
        entity.setName(permission.getName());
        return entity;
    }

    public Permission toDomain() {
        Permission permission = new Permission(this.name);
        permission.setId(this.id);
        return permission;
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