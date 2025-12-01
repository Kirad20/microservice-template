package com.template.user.infrastructure.grpc;

import com.template.user.application.port.in.UserServicePort;
import com.template.user.domain.model.User;
import com.template.user.grpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.stream.Collectors;

@GrpcService
public class UserServiceGrpcImpl extends UserServiceGrpc.UserServiceImplBase {

    private final UserServicePort userServicePort;

    @Autowired
    public UserServiceGrpcImpl(UserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public void getUser(GetUserRequest request, StreamObserver<UserResponse> responseObserver) {
        User user = userServicePort.getUserById(java.util.UUID.fromString(request.getUserId()));
        UserResponse response = UserResponse.newBuilder()
                .setUserId(user.getId().toString())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setPassword(user.getPassword())
                .addAllRoles(user.getRoles().stream().map(role ->
                        com.template.user.grpc.Role.newBuilder()
                                .setRoleId(role.getId().toString())
                                .setName(role.getName())
                                .addAllPermissions(role.getPermissions().stream().map(permission ->
                                        com.template.user.grpc.Permission.newBuilder()
                                                .setPermissionId(permission.getId().toString())
                                                .setName(permission.getName())
                                                .build()
                                ).collect(Collectors.toList()))
                                .build()
                ).collect(Collectors.toList()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<UserResponse> responseObserver) {
        User user = new User(request.getUsername(), request.getEmail(), request.getPassword(), java.util.Collections.emptySet());
        User createdUser = userServicePort.createUser(user);
        UserResponse response = UserResponse.newBuilder()
                .setUserId(createdUser.getId().toString())
                .setUsername(createdUser.getUsername())
                .setEmail(createdUser.getEmail())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUserByUsername(GetUserByUsernameRequest request, StreamObserver<UserResponse> responseObserver) {
        User user = userServicePort.getUserByUsername(request.getUsername());
        UserResponse response = UserResponse.newBuilder()
                .setUserId(user.getId().toString())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .addAllRoles(user.getRoles().stream().map(role ->
                        com.template.user.grpc.Role.newBuilder()
                                .setRoleId(role.getId().toString())
                                .setName(role.getName())
                                .addAllPermissions(role.getPermissions().stream().map(permission ->
                                        com.template.user.grpc.Permission.newBuilder()
                                                .setPermissionId(permission.getId().toString())
                                                .setName(permission.getName())
                                                .build()
                                ).collect(Collectors.toList()))
                                .build()
                ).collect(Collectors.toList()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}