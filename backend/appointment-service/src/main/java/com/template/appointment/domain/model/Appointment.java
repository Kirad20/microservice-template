package com.template.appointment.domain.model;

import java.time.Instant;
import java.util.UUID;

public class Appointment {

    private UUID id;
    private UUID clientId;
    private UUID productId;
    private Instant startTime;
    private Instant endTime;
    private String status;

    public Appointment(UUID clientId, UUID productId, Instant startTime, Instant endTime) {
        this.id = UUID.randomUUID();
        this.clientId = clientId;
        this.productId = productId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = "SCHEDULED";
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