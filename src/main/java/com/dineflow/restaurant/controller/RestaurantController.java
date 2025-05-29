package com.dineflow.restaurant.controller;

import com.dineflow.restaurant.dto.RestaurantDTO;
import com.dineflow.restaurant.dto.request.CreateRestaurantRequest;
import com.dineflow.restaurant.dto.request.UpdateRestaurantRequest;
import com.dineflow.restaurant.dto.response.ApiResponse;
import com.dineflow.restaurant.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
@Tag(name = "Restaurant Management", description = "APIs for managing restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    @Operation(summary = "Onboard a new restaurant")
    public ResponseEntity<ApiResponse<RestaurantDTO>> onboardRestaurant(
            @Valid @RequestBody CreateRestaurantRequest request) {
        RestaurantDTO restaurantDTO = restaurantService.onboardRestaurant(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Restaurant onboarded successfully", restaurantDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant by ID")
    public ResponseEntity<ApiResponse<RestaurantDTO>> getRestaurantById(@PathVariable Long id) {
        RestaurantDTO restaurantDTO = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(ApiResponse.success(restaurantDTO));
    }

    @GetMapping
    @Operation(summary = "Get all active restaurant")
    public ResponseEntity<ApiResponse<List<RestaurantDTO>>> getAllActiveRestaurants() {
        List<RestaurantDTO> restaurantDTO = restaurantService.getRestaurants();
        return ResponseEntity.ok(ApiResponse.success(restaurantDTO));
    }

    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate a restaurant")
    public ResponseEntity<ApiResponse<RestaurantDTO>> deactivateRestaurant(@PathVariable Long id) {
        RestaurantDTO restaurantDTO = restaurantService.deactivateRestaurant(id);
        return ResponseEntity.ok(ApiResponse.success("Restaurant deactivated successfully", restaurantDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update restaurant details")
    public ResponseEntity<ApiResponse<RestaurantDTO>> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRestaurantRequest request) {
        RestaurantDTO restaurantDTO = restaurantService.updateRestaurant(id, request);
        return ResponseEntity.ok(ApiResponse.success("Restaurant updated successfully", restaurantDTO));
    }


}
