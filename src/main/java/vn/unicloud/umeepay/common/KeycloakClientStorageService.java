package vn.unicloud.umeepay.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.TokenVerifier;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.utils.CommonUtils;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeycloakClientStorageService implements IKeycloakClientService {

    @Value("${keycloak-client.authz.storage.endpoint}")
    private String endpoint;

    @Value("${keycloak-client.authz.storage.secret}")
    private String secret;

    @Value("${keycloak-client.authz.storage.realm}")
    private String realm;

    @Value("${keycloak-client.authz.storage.resource}")
    private String resource;

    @Value("${keycloak-client.authz.storage.bearerOnly}")
    private boolean bearerOnly;

    @Value("${keycloak-client.authz.storage.useResourceRoleMappings}")
    private boolean useResourceRoleMappings;

    private final RedisService redisService;

    @Override
    public String getToken() {
        try {
            String token = redisService.getValue(RedisKeyUtils.getKeycloakStorageTokenKey(), String.class);
            if (token != null) {
                AccessToken accessToken = TokenVerifier.create(token, AccessToken.class).getToken();
                if (CommonUtils.isExpired(accessToken.getExp())) {
                    token = null;
                }
            }

            if (token == null) {
                Map<String, Object> credentials = new HashMap<>();
                credentials.put("secret", secret);

                Configuration configuration = new Configuration();
                configuration.setAuthServerUrl(endpoint);
                configuration.setRealm(realm);
                configuration.setSslRequired("none");
                configuration.setResource(resource);
                configuration.setCredentials(credentials);
                configuration.setBearerOnly(bearerOnly);
                configuration.setUseResourceRoleMappings(false);

                AuthzClient authzClient = AuthzClient.create(configuration);
                token = authzClient.authorization().authorize().getToken();
                redisService.setValue(RedisKeyUtils.getKeycloakStorageTokenKey(), token);
            }
            return token;
        } catch (Exception ex) {
            log.error("Get Storage token failed. {}", ex.getMessage());
        }
        return null;
    }
}
