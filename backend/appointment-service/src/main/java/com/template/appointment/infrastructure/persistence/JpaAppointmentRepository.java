package com.template.appointment.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {
}