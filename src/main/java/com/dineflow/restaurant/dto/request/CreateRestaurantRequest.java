package com.dineflow.restaurant.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRestaurantRequest {
    
    @NotBlank(message = "Restaurant name is required")
    private String name;
    
    // Add other fields as needed for restaurant creation
    private String address;
    private String phoneNumber;
    private String email;
}
