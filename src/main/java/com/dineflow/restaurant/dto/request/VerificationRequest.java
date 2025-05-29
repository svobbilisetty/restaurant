package com.dineflow.restaurant.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationRequest {

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

}
