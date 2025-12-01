package com.template.appointment.domain.repository;

import com.template.appointment.domain.model.Appointment;

import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository {
    Optional<Appointment> findById(UUID id);
    Appointment save(Appointment appointment);
    void deleteById(UUID id);
}