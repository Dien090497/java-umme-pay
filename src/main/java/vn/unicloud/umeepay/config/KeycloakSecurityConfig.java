package vn.unicloud.umeepay.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import vn.unicloud.umeepay.enums.RoleType;

@KeycloakConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@ComponentScan(basePackageClasses = {KeycloakSpringBootConfigResolver.class})
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Value("${umeepay.paygate.access}")
    private String basicAuth;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        super.configure(http);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/",
                        "/*",
                        "/webjars/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/api/auth/**",
                        "/api/payment/**",
                        "/api/paygate/callback/**",
                        "/api/portal/user/v1/register/**",
                        "/api/portal/user/v1/changePassword/**",
                        "/api/auth/**/login/**",
                        "/api/auth/**/refreshToken/**"
                ).permitAll()
                .antMatchers("/api/**/cms/**").hasRole(RoleType.ADMIN.toString())
                .antMatchers("api/**/portal/**").hasRole(RoleType.MERCHANT.toString())
                .anyRequest().authenticated()
                .and()
//            .addFilterBefore(new BasicTokenFilter("/api/paygate/callback", Base64.getEncoder().encodeToString(basicAuth.getBytes(StandardCharsets.UTF_8))), WebAsyncManagerIntegrationFilter.class)
                .addFilterBefore(new SimpleCORSFilter(), WebAsyncManagerIntegrationFilter.class)
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .logout()
                .addLogoutHandler(keycloakLogoutHandler())
                .logoutUrl("/user/logout").permitAll()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID");
    }

    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        SimpleAuthorityMapper simpleAuthorityMapper = new SimpleAuthorityMapper();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(simpleAuthorityMapper);
        auth.authenticationProvider(keycloakAuthenticationProvider);

    }

    /**
     * Defines the session authentication strategy
     *
     * @return null
     */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Override
    protected AuthenticationEntryPoint authenticationEntryPoint() throws Exception {
        return new RestAuthenticationEntryPoint(adapterDeploymentContext());
    }
}
