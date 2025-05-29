package com.dineflow.restaurant.dto;

import com.dineflow.restaurant.entity.RestaurantVerification.VerificationStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantVerificationDTO {
    private Long id;
    private Long restaurantId;
    private String fssaiLicenseNumber;
    private String fssaiLicenseImageUrl;
    private String gstNumber;
    private String gstCertificateUrl;
    private String panNumber;
    private String panCardUrl;
    private String ownerPanCardUrl;
    private String ownerAadhaarCardUrl;
    private String bankAccountNumber;
    private String bankName;
    private String bankIfscCode;
    private String cancelledChequeUrl;
    private String locationImageUrl;
    private VerificationStatus verificationStatus;
    private String verificationNotes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Nested DTO for related restaurant
    private RestaurantDTO restaurant;
}
