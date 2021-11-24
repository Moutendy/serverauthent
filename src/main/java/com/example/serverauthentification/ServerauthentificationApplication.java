package com.example.serverauthentification;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerauthentificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerauthentificationApplication.class, args);
    }



}
