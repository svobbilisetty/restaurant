package com.dineflow.restaurant.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI restaurantOnboardingOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Restaurant Onboarding API")
                        .description("API for managing restaurant onboarding, verification, and subscriptions")
                        .version("1.0")
                        .contact(new Contact()
                                .name("DineFlow Support")
                                .email("support@dineflow.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
