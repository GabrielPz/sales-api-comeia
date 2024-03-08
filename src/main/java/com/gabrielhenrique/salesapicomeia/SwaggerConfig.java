package com.gabrielhenrique.salesapicomeia;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> openApi.getComponents().addSecuritySchemes("Keycloak", new io.swagger.v3.oas.models.security.SecurityScheme()
                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.OAUTH2)
                .description("Autenticação Keycloak")
                .flows(new io.swagger.v3.oas.models.security.OAuthFlows()
                        .authorizationCode(new io.swagger.v3.oas.models.security.OAuthFlow()
                                .authorizationUrl("http://localhost:8080/realms/SalesApi/protocol/openid-connect/auth")
                                .tokenUrl("http://localhost:8080/realms/SalesApi/protocol/openid-connect/token")
                                .refreshUrl("http://localhost:8080/realms/SalesAPi/protocol/openid-connect/token")
                                .scopes(new io.swagger.v3.oas.models.security.Scopes().addString("openid", "Acesso openid"))
                        )));
    }
}

// http://localhost:8080/realms/SalesApi/protocol/openid-connect/token