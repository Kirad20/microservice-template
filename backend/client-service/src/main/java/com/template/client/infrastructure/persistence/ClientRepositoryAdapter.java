package com.template.client.infrastructure.persistence;

import com.template.client.domain.model.Client;
import com.template.client.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ClientRepositoryAdapter implements ClientRepository {

    private final JpaClientRepository jpaClientRepository;

    public ClientRepositoryAdapter(JpaClientRepository jpaClientRepository) {
        this.jpaClientRepository = jpaClientRepository;
    }

    @Override
    public Optional<Client> findById(UUID id) {
        return jpaClientRepository.findById(id).map(ClientEntity::toDomain);
    }

    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = ClientEntity.fromDomain(client);
        return jpaClientRepository.save(clientEntity).toDomain();
    }

    @Override
    public void deleteById(UUID id) {
        jpaClientRepository.deleteById(id);
    }
}