package io.openleap.registry.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.util.List;

@ConfigurationProperties(prefix = "oleap.client.registration")
public final class ClientRegistrationProperties {

    String registrationUsername;
    String registrationPassword;
    List<String> registrationScopes;
    List<String> grantTypes;
    String unregistrationEndpoint;
    URI tokenEndpoint;

    public URI getTokenEndpoint() {
        return tokenEndpoint;
    }

    public void setTokenEndpoint(URI tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    public String getUnregistrationEndpoint() {
        return unregistrationEndpoint;
    }

    public void setUnregistrationEndpoint(String unregistrationEndpoint) {
        this.unregistrationEndpoint = unregistrationEndpoint;
    }

    public String getRegistrationUsername() {
        return registrationUsername;
    }

    public void setRegistrationUsername(String registrationUsername) {
        this.registrationUsername = registrationUsername;
    }

    public String getRegistrationPassword() {
        return registrationPassword;
    }

    public void setRegistrationPassword(String registrationPassword) {
        this.registrationPassword = registrationPassword;
    }

    public List<String> getRegistrationScopes() {
        return registrationScopes;
    }

    public void setRegistrationScopes(List<String> registrationScopes) {
        this.registrationScopes = registrationScopes;
    }

    public List<String> getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(List<String> grantTypes) {
        this.grantTypes = grantTypes;
    }

}
