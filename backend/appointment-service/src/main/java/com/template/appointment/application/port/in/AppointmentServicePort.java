package com.template.appointment.application.port.in;

import com.template.appointment.domain.model.Appointment;

import java.util.UUID;

public interface AppointmentServicePort {
    Appointment getAppointmentById(UUID id);
    Appointment createAppointment(Appointment appointment);
}