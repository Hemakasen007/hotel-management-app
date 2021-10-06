package com.travel.lodge.authservice.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KeycloakConfig {

    @Value("keycloak-config.master.server-url")
    private String serverUrl;

    @Value("keycloak-config.master.realm")
    private String realm;

    @Value("keycloak-config.master.username")
    private String username;

    @Value("keycloak-config.master.password")
    private String password;

    @Value("keycloak-config.master.client-id")
    private String clientId;


    @Bean
    public Keycloak getInstance() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080/auth")
                .realm("master")
                .grantType(OAuth2Constants.PASSWORD)
                .clientId("admin-cli")
                .username("admin")
                .password("1qaz2wsx")
                .build();



    }


}
