package com.template.appointment.infrastructure.persistence;

import com.template.appointment.domain.model.Appointment;
import com.template.appointment.domain.repository.AppointmentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AppointmentRepositoryAdapter implements AppointmentRepository {

    private final JpaAppointmentRepository jpaAppointmentRepository;

    public AppointmentRepositoryAdapter(JpaAppointmentRepository jpaAppointmentRepository) {
        this.jpaAppointmentRepository = jpaAppointmentRepository;
    }

    @Override
    public Optional<Appointment> findById(UUID id) {
        return jpaAppointmentRepository.findById(id).map(AppointmentEntity::toDomain);
    }

    @Override
    public Appointment save(Appointment appointment) {
        AppointmentEntity appointmentEntity = AppointmentEntity.fromDomain(appointment);
        return jpaAppointmentRepository.save(appointmentEntity).toDomain();
    }

    @Override
    public void deleteById(UUID id) {
        jpaAppointmentRepository.deleteById(id);
    }
}