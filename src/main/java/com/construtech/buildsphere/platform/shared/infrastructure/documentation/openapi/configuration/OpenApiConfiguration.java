package com.construtech.buildsphere.platform.shared.infrastructure.documentation.openapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.ExternalDocumentation;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI BuildSphereOpenApi() {
        var openApi = new OpenAPI();
        openApi.info(
                new Info()
                        .title("BuildSphere API")
                        .description("BuildSphere API")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
        )
                .externalDocs(new ExternalDocumentation()
                        .description("BuildSphere Documentation")
                        .url("https://buildsphere.com"));

        return openApi;

    }
}
