package com.construtech.buildsphere.platform.shared.infraestructure.documentation.openapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.ExternalDocumentation;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI BuildSphereOpenApi(){
        var openApi = new OpenAPI()
                .info(new Info()
                        .title("BuildSphere API")
                        .description("BuildSphere API Documentation")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("BuildSphere Documentation")
                        .url("https://docs.buildsphere.com"));
        return openApi;
    }
}