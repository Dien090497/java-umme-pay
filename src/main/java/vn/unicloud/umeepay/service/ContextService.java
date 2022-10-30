package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Using to get current log in user
 * Author: lnson
 */
@Service
@RequiredArgsConstructor
public class ContextService {

    private AccessToken getLoggedInAccessToken() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null ||
                    !authentication.isAuthenticated() ||
                    !(authentication instanceof KeycloakAuthenticationToken)) {
                return null;
            }
            KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) authentication;
            KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal) token.getPrincipal();

            AccessToken accessToken = keycloakPrincipal.getKeycloakSecurityContext().getToken();
            return accessToken;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Optional<String> getLoggedInUsername() {
        AccessToken loggedInToken = getLoggedInAccessToken();
        return loggedInToken != null
                ? Optional.of(loggedInToken.getPreferredUsername())
                : Optional.empty();
    }

    public Optional<String> getLoggedInUserId() {
        AccessToken loggedInToken = getLoggedInAccessToken();
        return loggedInToken != null
                ? Optional.of(loggedInToken.getSubject())
                : Optional.empty();
    }

}
