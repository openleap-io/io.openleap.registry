package io.openleap.registry.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.openleap.registry.config.ClientRegistrationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Profile("default")
@EnableConfigurationProperties({ClientRegistrationProperties.class})
public class SpringAuthServerInstanceUnregistrationService implements InstanceUnregistrationService {
    Logger logger = LoggerFactory.getLogger(SpringAuthServerInstanceUnregistrationService.class);
    private final ClientRegistrationProperties clientRegistrationProperties;

    public SpringAuthServerInstanceUnregistrationService(ClientRegistrationProperties clientRegistrationProperties) {
        this.clientRegistrationProperties = clientRegistrationProperties;
    }

    @Override
    public void unregisterInstance(String instanceId) {
        unRegister(instanceId);
    }

    private void unRegister(String instanceId) {
        logger.debug("Unregistering instanceId: {}", instanceId);

        RestTemplate restTemplate = new RestTemplate();

        String url = clientRegistrationProperties.getUnregistrationEndpoint();

        String token = createRegistrationToken();
        if (token == null) {
            logger.warn("Unable to unregister instance due to missing token");
            return;
        }

        var headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("instanceId", instanceId);


        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("Successfully unregistered instanceId: {}", instanceId);
            } else {
                logger.error("Failed to fetch resource. HTTP Status: {}", response.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("Error occurred: {}", e.getMessage());
        }
    }

    private String createRegistrationToken() {
        URI url = clientRegistrationProperties.getTokenEndpoint();

        var headers = new HttpHeaders();
        headers.setBasicAuth(clientRegistrationProperties.getRegistrationUsername(), clientRegistrationProperties.getRegistrationPassword());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var body = new LinkedMultiValueMap<String, String>();
        body.put("grant_type", List.of("client_credentials"));
        body.put("scope", List.of("client.create"));

        var request = new RequestEntity<>(
                body,
                headers,
                HttpMethod.POST,
                url);

        RestTemplate restTemplate = new RestTemplate();

        String accessToken = null;
        try {
            var result = restTemplate.exchange(request, ObjectNode.class);
            ObjectNode responseBody = result.getBody();
            if (result.getStatusCode().is2xxSuccessful() && responseBody != null) {
                accessToken = responseBody.get("access_token").asText();
            } else {
                logger.error("Failed to retrieve access token. HTTP Status: {}", result.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("Error occurred: {}", e.getMessage());
        }
        return accessToken;
    }
}
