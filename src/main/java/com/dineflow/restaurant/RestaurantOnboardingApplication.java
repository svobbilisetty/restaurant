package com.dineflow.restaurant;

import com.dineflow.restaurant.config.VerificationProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(VerificationProperties.class)
@OpenAPIDefinition(
    info = @Info(
        title = "Restaurant Management API",
        version = "1.0",
        description = "API for managing restaurants, subscriptions, and verifications"
    )
)
public class RestaurantOnboardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantOnboardingApplication.class, args);
    }
}
