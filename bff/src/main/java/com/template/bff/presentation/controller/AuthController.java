package com.template.bff.presentation.controller;

import com.template.security.grpc.AuthRequest;
import com.template.security.grpc.AuthResponse;
import com.template.security.grpc.AuthServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GrpcClient("security-service")
    private AuthServiceGrpc.AuthServiceBlockingStub authServiceClient;

    @PostMapping("/login")
    public Mono<AuthResponse> login(@RequestBody AuthRequest request) {
        return Mono.just(authServiceClient.authenticate(request));
    }
}