package vn.unicloud.umeepay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;
import vn.unicloud.umeepay.utils.CommonUtils;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class KeycloakService {

    @Value("${keycloak.auth-server-url}")
    String authUrl;

    @Value("${keycloak.credentials.secret}")
    private String secretKey;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.realm}")
    String realm;

    @Value("${keycloak-client.username}")
    String username;

    @Value("${keycloak-client.password}")
    String password;

    private Keycloak keycloakAdmin;

    @Bean
    private void initKeycloakService() {
        keycloakAdmin = KeycloakBuilder.builder()
                .serverUrl(authUrl)
                .realm("master")
                .clientId("admin-cli")
                .username(username)
                .password(password)
                .resteasyClient(
                        new ResteasyClientBuilder()
                                .connectionPoolSize(10).build()
                )
                .build();
    }

    public AccessTokenResponseCustom getUserJWT(String username, String password) throws VerificationException {
        Keycloak keycloakUser = KeycloakBuilder.builder()
                .serverUrl(authUrl)
                .realm(realm)
                .username(username)
                .password(password)
                .grantType("password")
                .clientId(clientId)
                .clientSecret(secretKey)
                .build();

        AccessTokenResponse accessTokenResponse = keycloakUser.tokenManager().getAccessToken();

        return new AccessTokenResponseCustom(accessTokenResponse);
    }

    public AccessTokenResponseCustom getUserJWTByCode(String code) {

        // Create header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("client_secret", secretKey);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        String url = authUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        try {
            ResponseEntity<String> keycloakResponse = restTemplate.postForEntity(url, request, String.class);
            return new ObjectMapper().readValue(keycloakResponse.getBody(), AccessTokenResponseCustom.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public AccessTokenResponseCustom clientLogin(String clientId, String secretKey) {
        // Create header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("grant_type", "client_credentials");
        map.add("client_secret", secretKey);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        String url = authUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        try {
            ResponseEntity<String> keycloakResponse = restTemplate.postForEntity(url, request, String.class);
            return new ObjectMapper().readValue(keycloakResponse.getBody(), AccessTokenResponseCustom.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean invalidateToken(String refreshToken) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("client_id", clientId);
        requestParams.add("client_secret", secretKey);
        requestParams.add("refresh_token", refreshToken);
        requestParams.add("revoke_tokens_on_logout ", "true");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestParams, headers);

        String url = authUrl + "/realms/" + realm + "/protocol/openid-connect/logout";

        RestTemplate restTemplate = new RestTemplate();
        try {
            log.info("logout response {}", restTemplate.postForEntity(url, request, Object.class));
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public String createUser(String phone, String password, String email, String name) {
        UserRepresentation user = new UserRepresentation();
        user.setFirstName(name);
        user.setEnabled(true);
        user.setEmailVerified(false);
        user.setUsername(phone);
        if (email != null) {
            user.setEmail(email);
        }

        RealmResource realmResource = keycloakAdmin.realm(realm);
        UsersResource usersResource = realmResource.users();

        try (Response response = usersResource.create(user)) {
            log.info("Create keycloak user response: {}", response.getStatus());
            if (response.getStatus() == 201 || response.getStatus() == 200) {

                String userId = CreatedResponseUtil.getCreatedId(response);

                CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
                credentialRepresentation.setTemporary(false);
                credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
                credentialRepresentation.setValue(password);

                UserResource userResource = usersResource.get(userId);
                userResource.resetPassword(credentialRepresentation);

                return userId;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getLocalizedMessage());
        }

        return null;
    }


    /**
     * Create Keycloak user with roles
     * @param phone
     * @param password
     * @param email
     * @param name
     * @param roles
     * @return keycloak userID
     */
    public String createUser(String phone, String password, String email, String name, List<String> roles) {
        List<RoleRepresentation> roleRepresentations =
                roles.stream()
                        .map(this::getUserRoleRepresentation)
                        .collect(Collectors.toList());

        for (RoleRepresentation r : roleRepresentations) {
            if (r == null) {
                log.error("Create keycloak user failed because of invalid role name");
                return null;
            }
        }

        UserRepresentation user = new UserRepresentation();
        if (name != null) {
            user.setFirstName(name);
        }
        user.setEnabled(true);
        user.setEmailVerified(false);
        user.setUsername(phone);
        if (email != null) {
            user.setEmail(email);
        }

        RealmResource realmResource = keycloakAdmin.realm(realm);
        UsersResource usersResource = realmResource.users();

        try (Response response = usersResource.create(user)) {
            log.info("Create keycloak user response: {}", response.getStatus());
            if (response.getStatus() == 201 || response.getStatus() == 200) {

                String userId = CreatedResponseUtil.getCreatedId(response);

                CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
                credentialRepresentation.setTemporary(false);
                credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
                credentialRepresentation.setValue(password);

                UserResource userResource = usersResource.get(userId);
                userResource.resetPassword(credentialRepresentation);

                String client_id = keycloakAdmin
                        .realm(realm)
                        .clients()
                        .findByClientId(clientId)
                        .get(0)
                        .getId();

                userResource.roles().clientLevel(client_id).add(roleRepresentations);

                return userId;
            }
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
        }

        return null;
    }

    public RoleRepresentation getUserRoleRepresentation(String roleName) {
        ClientRepresentation clientRep = keycloakAdmin
                .realm(realm)
                .clients()
                .findByClientId(clientId)
                .get(0);
        try {
            RoleRepresentation result = keycloakAdmin
                    .realm(realm)
                    .clients()
                    .get(clientRep.getId())
                    .roles()
                    .get(roleName)
                    .toRepresentation();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String createClient(String clientId) {

        RealmResource realmResource = keycloakAdmin.realm(realm);
        ClientsResource clientsResource = realmResource.clients();

        ClientRepresentation clientRepresentation = new ClientRepresentation();
        clientRepresentation.setClientId(clientId);
        clientRepresentation.setEnabled(true);
        clientRepresentation.setRedirectUris(List.copyOf(Collections.singletonList("*")));
        clientRepresentation.setServiceAccountsEnabled(true);
        String secret = CommonUtils.getSecureRandomKey(32);
        clientRepresentation.setSecret(secret);

        try (Response response = clientsResource.create(clientRepresentation)) {
            log.info("Create keycloak user response: {}", response.getStatus());
            if (response.getStatus() == 201 || response.getStatus() == 200) {
                log.info("Create client success");
                return secret;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getLocalizedMessage());
        }

        return null;
    }

    public void deleteUser(String id) {
        try {
            keycloakAdmin.realm(realm).users().delete(id);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }

    @SneakyThrows
    public AccessTokenResponseCustom refreshToken(String refreshToken) {
        // Create header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", refreshToken);
        map.add("client_secret", secretKey);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        String url = authUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        ResponseEntity<String> keycloakResponse = restTemplate.postForEntity(url, request, String.class);
        if (keycloakResponse.getStatusCode() == HttpStatus.OK) {
            // JSONObject response = new JSONObject(keycloakResponse.getBody());
            return new ObjectMapper().readValue(keycloakResponse.getBody(), AccessTokenResponseCustom.class);
        }
        return null;
    }

    public String getUserIdByEmail(String email) {
        try {
            UsersResource usersResource = keycloakAdmin.realm(realm).users();
            List<UserRepresentation> users = usersResource.search(null, null, null, email, null, null);
            UserRepresentation userId = users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
            return userId != null ? userId.getId() : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUserIdByUserName(String username) {
        try {
            UsersResource usersResource = keycloakAdmin.realm(realm).users();
            List<UserRepresentation> users = usersResource.search(username, null, null, null, null, null);
            UserRepresentation userId = users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
            return userId != null ? userId.getId() : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean setUserPassword(String userId, String newPassword) {
        try {
            UserResource userResource = keycloakAdmin.realm(realm).users().get(userId);
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(newPassword);
            userResource.resetPassword(credentialRepresentation);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
