package com.dineflow.restaurant.service;

import com.dineflow.restaurant.dto.RestaurantSubscriptionDTO;
import com.dineflow.restaurant.dto.SubscriptionPlanDTO;
import com.dineflow.restaurant.dto.request.SubscriptionRequest;

import java.util.List;

public interface SubscriptionService {
    
    /**
     * Allocate a free trial subscription to a restaurant
     * @param restaurantId The restaurant ID
     * @return The created subscription DTO
     */
    RestaurantSubscriptionDTO allocateFreeTrial(Long restaurantId);
    
    /**
     * Upgrade or downgrade a restaurant's subscription
     * @param request The subscription details
     * @return The updated subscription DTO
     */
    RestaurantSubscriptionDTO updateSubscription(SubscriptionRequest request);
    
    /**
     * Get subscription by restaurant ID
     * @param restaurantId The restaurant ID
     * @return The subscription DTO
     */
    RestaurantSubscriptionDTO getSubscriptionByRestaurantId(Long restaurantId);
    
    /**
     * Get all available subscription plans
     * @return List of subscription plan DTOs
     */
    List<SubscriptionPlanDTO> getAllSubscriptionPlans();
    
    /**
     * Check if a restaurant has an active subscription
     * @param restaurantId The restaurant ID
     * @return true if active subscription exists, false otherwise
     */
    boolean hasActiveSubscription(Long restaurantId);
}
