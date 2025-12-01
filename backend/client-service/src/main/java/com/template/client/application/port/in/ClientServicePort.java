package com.template.client.application.port.in;

import com.template.client.domain.model.Client;

import java.util.UUID;

public interface ClientServicePort {
    Client getClientById(UUID id);
    Client createClient(Client client);
}