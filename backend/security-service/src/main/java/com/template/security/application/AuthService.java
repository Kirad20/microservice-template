package com.template.security.application;

import com.template.user.grpc.GetUserByUsernameRequest;
import com.template.user.grpc.UserResponse;
import com.template.user.grpc.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceClient;

    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(String username, String password) {
        GetUserByUsernameRequest request = GetUserByUsernameRequest.newBuilder().setUsername(username).build();
        UserResponse user = userServiceClient.getUserByUsername(request);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return tokenProvider.generateToken(user);
    }
}