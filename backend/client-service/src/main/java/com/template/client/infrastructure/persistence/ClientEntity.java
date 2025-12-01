package com.template.client.infrastructure.persistence;

import com.template.client.domain.model.Client;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public static ClientEntity fromDomain(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.setId(client.getId());
        entity.setFirstName(client.getFirstName());
        entity.setLastName(client.getLastName());
        entity.setEmail(client.getEmail());
        entity.setPhone(client.getPhone());
        return entity;
    }

    public Client toDomain() {
        Client client = new Client(
                this.firstName,
                this.lastName,
                this.email,
                this.phone
        );
        client.setId(this.id);
        return client;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}