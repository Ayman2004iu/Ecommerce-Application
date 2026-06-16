package com.example.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ecommerceOpenAPI() {
        Server localhost = new Server();
        localhost.setUrl("http://localhost:8080");
        localhost.setDescription("Development server");

        Contact contact = new Contact();
        contact.setEmail("ayman.ibrahim.seddik@gmail.com");
        contact.setName("Ayman Ibrahim Seddik");
        contact.setUrl("https://github.com/Ayman2004iu");

        Info info = new Info()
                .title("E-Commerce API")
                .version("1.0")
                .description("E-Commerce REST API with JWT authentication")
                .contact(contact);

        return new OpenAPI()
                .info(info)
                .servers(List.of(localhost));
    }
}
