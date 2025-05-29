package com.dineflow.restaurant.service;

import com.dineflow.restaurant.dto.RestaurantDTO;
import com.dineflow.restaurant.dto.request.CreateRestaurantRequest;
import com.dineflow.restaurant.dto.request.UpdateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    /**
     * Onboard a new restaurant
     * @param request The restaurant details
     * @return The created restaurant DTO
     */
    RestaurantDTO onboardRestaurant(CreateRestaurantRequest request);

    /**
     * Get restaurant by ID
     * @param id The restaurant ID
     * @return The restaurant DTO
     */
    RestaurantDTO getRestaurantById(Long id);

    /**
     * Check if a restaurant exists by ID
     * @param id The restaurant ID
     * @return true if exists, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Deactivate a restaurant
     * @param id The restaurant ID
     * @return The deactivated restaurant DTO
     */
    RestaurantDTO deactivateRestaurant(Long id);

    /**
     * Update restaurant details
     * @param id The restaurant ID
     * @param request The updated restaurant details
     * @return The updated restaurant DTO
     */
    RestaurantDTO updateRestaurant(Long id, UpdateRestaurantRequest request);


    /**
     * Get all active restaurants
     *
     * @return The list of active restaurant DTOs
     */
    List<RestaurantDTO> getRestaurants();

}
