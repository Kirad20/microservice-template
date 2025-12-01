package com.template.bff.presentation.controller;

import com.template.user.grpc.GetUserRequest;
import com.template.user.grpc.UserResponse;
import com.template.user.grpc.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceClient;

    @GetMapping("/{userId}")
    public Mono<UserResponse> getUser(@PathVariable String userId) {
        GetUserRequest request = GetUserRequest.newBuilder()
                .setUserId(userId)
                .build();
        return Mono.just(userServiceClient.getUser(request));
    }
}