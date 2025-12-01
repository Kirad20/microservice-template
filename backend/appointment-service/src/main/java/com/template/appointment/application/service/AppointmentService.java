package com.template.appointment.application.service;

import com.template.appointment.application.port.in.AppointmentServicePort;
import com.template.appointment.domain.model.Appointment;
import com.template.appointment.domain.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AppointmentService implements AppointmentServicePort {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment getAppointmentById(UUID id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}