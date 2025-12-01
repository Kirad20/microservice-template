package com.template.user.infrastructure.persistence;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.template.user.domain.model.User;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private UUID id;
    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    public static UserEntity fromDomain(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setRoles(user.getRoles().stream().map(RoleEntity::fromDomain).collect(Collectors.toSet()));
        return entity;
    }

    public User toDomain() {
        User user = new User(
                this.username,
                this.email,
                this.password,
                this.roles.stream().map(RoleEntity::toDomain).collect(Collectors.toSet())
        );
        user.setId(this.id);
        return user;
    }

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}