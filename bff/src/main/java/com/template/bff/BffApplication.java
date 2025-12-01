package com.template.bff;

import com.fasterxml.jackson.databind.Module;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BffApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffApplication.class, args);
    }

    @Bean
    public Module protobufModule() {
        return new ProtobufModule();
    }
}