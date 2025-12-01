package com.template.appointment.infrastructure.grpc;

import com.google.protobuf.Timestamp;
import com.template.appointment.application.port.in.AppointmentServicePort;
import com.template.appointment.domain.model.Appointment;
import com.template.appointment.grpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

@GrpcService
public class AppointmentServiceGrpcImpl extends AppointmentServiceGrpc.AppointmentServiceImplBase {

    private final AppointmentServicePort appointmentServicePort;

    @Autowired
    public AppointmentServiceGrpcImpl(AppointmentServicePort appointmentServicePort) {
        this.appointmentServicePort = appointmentServicePort;
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_appointment:read')")
    public void getAppointment(GetAppointmentRequest request, StreamObserver<AppointmentResponse> responseObserver) {
        Appointment appointment = appointmentServicePort.getAppointmentById(UUID.fromString(request.getAppointmentId()));
        AppointmentResponse response = AppointmentResponse.newBuilder()
                .setAppointmentId(appointment.getId().toString())
                .setClientId(appointment.getClientId().toString())
                .setProductId(appointment.getProductId().toString())
                .setStartTime(Timestamp.newBuilder().setSeconds(appointment.getStartTime().getEpochSecond()).build())
                .setEndTime(Timestamp.newBuilder().setSeconds(appointment.getEndTime().getEpochSecond()).build())
                .setStatus(appointment.getStatus())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_appointment:write')")
    public void createAppointment(CreateAppointmentRequest request, StreamObserver<AppointmentResponse> responseObserver) {
        Appointment appointment = new Appointment(
                UUID.fromString(request.getClientId()),
                UUID.fromString(request.getProductId()),
                java.time.Instant.ofEpochSecond(request.getStartTime().getSeconds()),
                java.time.Instant.ofEpochSecond(request.getEndTime().getSeconds())
        );
        Appointment createdAppointment = appointmentServicePort.createAppointment(appointment);
        AppointmentResponse response = AppointmentResponse.newBuilder()
                .setAppointmentId(createdAppointment.getId().toString())
                .setClientId(createdAppointment.getClientId().toString())
                .setProductId(createdAppointment.getProductId().toString())
                .setStartTime(Timestamp.newBuilder().setSeconds(createdAppointment.getStartTime().getEpochSecond()).build())
                .setEndTime(Timestamp.newBuilder().setSeconds(createdAppointment.getEndTime().getEpochSecond()).build())
                .setStatus(createdAppointment.getStatus())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}