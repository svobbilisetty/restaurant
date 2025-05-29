package com.dineflow.restaurant.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRequest {
    
    @NotNull(message = "Restaurant ID is required")
    private Long restaurantId;
    
    @NotNull(message = "Subscription plan ID is required")
    @Positive(message = "Subscription plan ID must be positive")
    private Long subscriptionPlanId;
    
    private LocalDate startDate;
    
    private LocalDate endDate;
}
