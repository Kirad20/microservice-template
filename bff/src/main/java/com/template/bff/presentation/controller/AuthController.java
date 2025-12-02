package com.template.bff.presentation.controller;

import com.template.security.grpc.AuthRequest;
import com.template.security.grpc.AuthServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceGrpc.AuthServiceBlockingStub authServiceStub;

    @PostMapping("/login")
    public Mono<String> login(@RequestBody LoginRequest request) {
        return Mono.fromCallable(() -> {
            var response = authServiceStub.authenticate(AuthRequest.newBuilder()
                    .setUsername(request.username())
                    .setPassword(request.password())
                    .build());
            return response.getAccessToken();
        });
    }

    public record LoginRequest(String username, String password) {}
}
