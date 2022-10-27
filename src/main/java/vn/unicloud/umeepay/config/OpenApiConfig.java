package vn.unicloud.umeepay.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class OpenApiConfig {
    @Value("${API_BASE_SERVER:http://localhost:8088}")
    private String apiServer;

    @Value("${API_BASE_SERVER:http://localhost:8088}")
    private String apiLocal;

    private static final String API_VERSION = "v1";

    private static final String API_TITLE = "Umee-Pay-Service";

    private static final String API_DESCRIPTION = "Umee-Pay-Service API";

    public final static String BEARER_SCHEME = "Bearer";

    @Bean
    public OpenAPI openAPiConfig() {
        ArrayList<Server> servers = new ArrayList<>();
        servers.add(new Server().url(apiServer).description("API server"));
        servers.add(new Server().url(apiLocal).description("API local"));

        return new OpenAPI()
                .info(new Info()
                        .title(API_TITLE)
                        .description(API_DESCRIPTION)
                        .version(API_VERSION))
                .servers(servers)
                .components(new Components()
                        .addSecuritySchemes(BEARER_SCHEME, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .name(BEARER_SCHEME)));
    }

    @Bean
    public GroupedOpenApi cmsApis() {
        String paths[] = {"/api/cms/**"};
        return GroupedOpenApi.builder().group("CMS apis").pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi portalApis() {
        String paths[] = {"/api/portal/**"};
        return GroupedOpenApi.builder().group("PORTAL apis").pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi allApis() {
        String paths[] = {"/api/**"};
        return GroupedOpenApi.builder().group("All apis").pathsToMatch(paths)
                .build();
    }
    
}
