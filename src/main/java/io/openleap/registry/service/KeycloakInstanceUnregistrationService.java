package io.openleap.registry.service;


import io.openleap.registry.config.KeycloakProperties;
import jakarta.annotation.PostConstruct;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.ClientRepresentation;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("keycloak")
@EnableConfigurationProperties({KeycloakProperties.class})
public class KeycloakInstanceUnregistrationService implements InstanceUnregistrationService {
    private final KeycloakProperties keycloakProperties;

    public KeycloakInstanceUnregistrationService(KeycloakProperties keycloakProperties) {
        this.keycloakProperties = keycloakProperties;
    }

    private Keycloak keycloak;

    @Override
    public void unregisterInstance(String instanceId) {
        List<ClientRepresentation> clients = keycloak.realm(keycloakProperties.getRealm()).clients().findAll();

        for (ClientRepresentation client : clients) {
            if (instanceId.equals(client.getAttributes().get("instanceId"))) {
                keycloak.realm(keycloakProperties.getRealm()).clients().get(client.getId()).remove();
                System.out.println("Deleted client: " + client.getClientId());
            }
        }
    }

    @PostConstruct
    public void init() {
        keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakProperties.getServerUrl())
                .realm(keycloakProperties.getRealm())
                .clientId(keycloakProperties.getClientId())
                .clientSecret(keycloakProperties.getClientSecret())
                .grantType("client_credentials")
                .build();
    }
}
