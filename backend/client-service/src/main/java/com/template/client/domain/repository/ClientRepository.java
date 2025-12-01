package com.template.client.domain.repository;

import com.template.client.domain.model.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {
    Optional<Client> findById(UUID id);
    Client save(Client client);
    void deleteById(UUID id);
}