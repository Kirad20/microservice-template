package com.template.security.infrastructure.grpc;

import com.template.security.application.AuthService;
import com.template.security.grpc.AuthRequest;
import com.template.security.grpc.AuthResponse;
import com.template.security.grpc.AuthServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class AuthServiceGrpcImpl extends AuthServiceGrpc.AuthServiceImplBase {

    private final AuthService authService;

    @Autowired
    public AuthServiceGrpcImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void authenticate(AuthRequest request, StreamObserver<AuthResponse> responseObserver) {
        String token = authService.authenticate(request.getUsername(), request.getPassword());
        AuthResponse response = AuthResponse.newBuilder().setAccessToken(token).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}