package com.template.client.infrastructure.grpc;

import com.template.client.application.port.in.ClientServicePort;
import com.template.client.domain.model.Client;
import com.template.client.grpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

@GrpcService
public class ClientServiceGrpcImpl extends ClientServiceGrpc.ClientServiceImplBase {

    private final ClientServicePort clientServicePort;

    @Autowired
    public ClientServiceGrpcImpl(ClientServicePort clientServicePort) {
        this.clientServicePort = clientServicePort;
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_client:read')")
    public void getClient(GetClientRequest request, StreamObserver<ClientResponse> responseObserver) {
        Client client = clientServicePort.getClientById(java.util.UUID.fromString(request.getClientId()));
        ClientResponse response = ClientResponse.newBuilder()
                .setClientId(client.getId().toString())
                .setFirstName(client.getFirstName())
                .setLastName(client.getLastName())
                .setEmail(client.getEmail())
                .setPhone(client.getPhone())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_client:write')")
    public void createClient(CreateClientRequest request, StreamObserver<ClientResponse> responseObserver) {
        Client client = new Client(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhone());
        Client createdClient = clientServicePort.createClient(client);
        ClientResponse response = ClientResponse.newBuilder()
                .setClientId(createdClient.getId().toString())
                .setFirstName(createdClient.getFirstName())
                .setLastName(createdClient.getLastName())
                .setEmail(createdClient.getEmail())
                .setPhone(createdClient.getPhone())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}