package com.template.user.infrastructure.persistence;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.template.user.domain.model.Role;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    private UUID id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionEntity> permissions;

    public static RoleEntity fromDomain(Role role) {
        RoleEntity entity = new RoleEntity();
        entity.setId(role.getId());
        entity.setName(role.getName());
        entity.setPermissions(role.getPermissions().stream().map(PermissionEntity::fromDomain).collect(Collectors.toSet()));
        return entity;
    }

    public Role toDomain() {
        Role role = new Role(
                this.name,
                this.permissions.stream().map(PermissionEntity::toDomain).collect(Collectors.toSet())
        );
        role.setId(this.id);
        return role;
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

    public Set<PermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionEntity> permissions) {
        this.permissions = permissions;
    }
}