package com.template.client.application.service;

import com.template.client.application.port.in.ClientServicePort;
import com.template.client.domain.model.Client;
import com.template.client.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService implements ClientServicePort {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getClientById(UUID id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }
}