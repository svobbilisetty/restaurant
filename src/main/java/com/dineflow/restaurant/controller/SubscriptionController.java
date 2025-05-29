package com.dineflow.restaurant.controller;

import com.dineflow.restaurant.dto.RestaurantSubscriptionDTO;
import com.dineflow.restaurant.dto.SubscriptionPlanDTO;
import com.dineflow.restaurant.dto.request.SubscriptionRequest;
import com.dineflow.restaurant.dto.response.ApiResponse;
import com.dineflow.restaurant.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Subscription Management", description = "APIs for managing restaurant subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/free-trial/{restaurantId}")
    @Operation(summary = "Allocate a free trial to a restaurant")
    public ResponseEntity<ApiResponse<RestaurantSubscriptionDTO>> allocateFreeTrial(
            @PathVariable Long restaurantId) {
        RestaurantSubscriptionDTO subscriptionDTO = subscriptionService.allocateFreeTrial(restaurantId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Free trial allocated successfully", subscriptionDTO));
    }

    @PostMapping
    @Operation(summary = "Update a restaurant's subscription")
    public ResponseEntity<ApiResponse<RestaurantSubscriptionDTO>> updateSubscription(
            @Valid @RequestBody SubscriptionRequest request) {
        RestaurantSubscriptionDTO subscriptionDTO = subscriptionService.updateSubscription(request);
        return ResponseEntity.ok(ApiResponse.success("Subscription updated successfully", subscriptionDTO));
    }

    @GetMapping("/restaurant/{restaurantId}")
    @Operation(summary = "Get subscription by restaurant ID")
    public ResponseEntity<ApiResponse<RestaurantSubscriptionDTO>> getSubscriptionByRestaurantId(
            @PathVariable Long restaurantId) {
        RestaurantSubscriptionDTO subscriptionDTO = subscriptionService.getSubscriptionByRestaurantId(restaurantId);
        return ResponseEntity.ok(ApiResponse.success(subscriptionDTO));
    }

    @GetMapping("/plans")
    @Operation(summary = "Get all available subscription plans")
    public ResponseEntity<ApiResponse<List<SubscriptionPlanDTO>>> getAllSubscriptionPlans() {
        List<SubscriptionPlanDTO> plans = subscriptionService.getAllSubscriptionPlans();
        return ResponseEntity.ok(ApiResponse.success(plans));
    }
}
