package com.template.client.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaClientRepository extends JpaRepository<ClientEntity, UUID> {
}