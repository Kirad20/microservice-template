package com.template.bff.config;

import com.template.config.ConfigServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Value("${config.service.host:localhost}")
    private String configServiceHost;

    @Value("${config.service.port:9094}")
    private int configServicePort;

    @Bean
    public ConfigServiceGrpc.ConfigServiceBlockingStub configServiceStub() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(configServiceHost, configServicePort)
                .usePlaintext()
                .build();
        return ConfigServiceGrpc.newBlockingStub(channel);
    }
}
