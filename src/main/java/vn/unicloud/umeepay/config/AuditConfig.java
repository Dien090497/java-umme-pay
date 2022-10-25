package vn.unicloud.umeepay.config;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}

class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null ||
                    !authentication.isAuthenticated() ||
                    !(authentication instanceof KeycloakAuthenticationToken)) {
                return Optional.empty();
            }
            KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) authentication;
            KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal) token.getPrincipal();

            AccessToken accessToken = keycloakPrincipal.getKeycloakSecurityContext().getToken();
            return Optional.of(accessToken.getName());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}