package com.dineflow.restaurant.service;

import com.dineflow.restaurant.dto.RestaurantVerificationDTO;
import com.dineflow.restaurant.dto.request.VerificationRequest;

import java.util.List;

public interface VerificationService {
    
    /**
     * Submit restaurant verification details
     * @param request The verification details
     * @return The created verification DTO
     */
    RestaurantVerificationDTO submitVerification(VerificationRequest request);
    
    /**
     * Get verification by restaurant ID
     * @param restaurantId The restaurant ID
     * @return The verification DTO
     */
    RestaurantVerificationDTO getVerificationByRestaurantId(Long restaurantId);
    
    /**
     * Update verification status
     * @param verificationId The verification ID
     * @param status The new status
     * @param notes Optional notes
     * @return The updated verification DTO
     */
    RestaurantVerificationDTO updateVerificationStatus(Long verificationId, String status, String notes);
    
    /**
     * Get all verifications by status
     * @param status The status to filter by
     * @return List of verification DTOs
     */
    List<RestaurantVerificationDTO> getVerificationsByStatus(String status);
    
    /**
     * Check if a restaurant is verified
     * @param restaurantId The restaurant ID
     * @return true if verified, false otherwise
     */
    boolean isRestaurantVerified(Long restaurantId);
}
