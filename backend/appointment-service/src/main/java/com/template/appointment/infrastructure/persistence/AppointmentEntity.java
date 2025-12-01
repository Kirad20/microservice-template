package com.template.appointment.infrastructure.persistence;

import com.template.appointment.domain.model.Appointment;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "appointments")
public class AppointmentEntity {

    @Id
    private UUID id;
    private UUID clientId;
    private UUID productId;
    private Instant startTime;
    private Instant endTime;
    private String status;

    public static AppointmentEntity fromDomain(Appointment appointment) {
        AppointmentEntity entity = new AppointmentEntity();
        entity.setId(appointment.getId());
        entity.setClientId(appointment.getClientId());
        entity.setProductId(appointment.getProductId());
        entity.setStartTime(appointment.getStartTime());
        entity.setEndTime(appointment.getEndTime());
        entity.setStatus(appointment.getStatus());
        return entity;
    }

    public Appointment toDomain() {
        Appointment appointment = new Appointment(
                this.clientId,
                this.productId,
                this.startTime,
                this.endTime
        );
        appointment.setId(this.id);
        appointment.setStatus(this.status);
        return appointment;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}