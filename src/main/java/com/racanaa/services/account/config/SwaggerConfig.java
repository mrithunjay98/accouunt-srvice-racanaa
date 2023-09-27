package com.racanaa.services.account.config;

import com.racanaa.services.account.persistance.manager.SystemConfigManager;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Autowired
    private SystemConfigManager systemConfigManager;
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                       .type(SecurityScheme.Type.HTTP)
                       .in(SecurityScheme.In.HEADER)
                       .bearerFormat("JWT")
                       .scheme("bearer");
    }

    private SecurityScheme xApiKey() {
        return new SecurityScheme()
                       .type(SecurityScheme.Type.APIKEY)
                       .in(SecurityScheme.In.HEADER)
                       .name("x-api-key");
    }

    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("manohar.negi@racanaa.com");
        contact.setName("Racanaa Energy");
        contact.setUrl("https://www.racanaa.com");

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Server URL in Local environment");

        Server productionServer = new Server();
        productionServer.setUrl("https://api.racanaa.energy");
        productionServer.setDescription("Server URL in Production environment");

        License mitLicense = new License()
                                     .name("MIT License")
                                     .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                            .title("Account Management Service")
                            .contact(contact)
                            .version("1.0")
                            .description("This API exposes endpoints for account and user management.")
                            .termsOfService("https://www.racanaa.com/terms")
                            .license(mitLicense);

        return new OpenAPI()
                       .info(info)
                       .servers(List.of(localServer, productionServer))
                       .addSecurityItem(new SecurityRequirement()
                                                .addList("API Key")
                                                .addList("Bearer Authentication"))
                       .components(new Components()
                                           .addSecuritySchemes("API Key", xApiKey())
                                           .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()));
    }
}
