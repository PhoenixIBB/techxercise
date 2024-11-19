//package com.application.techXercise.configs;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("TechXercise API")
//                        .version("1.0")
//                        .description("API for managing tasks and users"))
//                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
//                .components(new io.swagger.v3.oas.models.Components()
//                        .addSecuritySchemes("basicAuth", new SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("basic")));
//    }
//}