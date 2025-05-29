package com.dineflow.restaurant.controller;

import com.dineflow.restaurant.dto.RestaurantVerificationDTO;
import com.dineflow.restaurant.dto.request.VerificationRequest;
import com.dineflow.restaurant.dto.response.ApiResponse;
import com.dineflow.restaurant.service.VerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verifications")
@RequiredArgsConstructor
@Tag(name = "Verification Management", description = "APIs for managing restaurant verifications")
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping
    @Operation(summary = "Submit restaurant verification details")
    public ResponseEntity<ApiResponse<RestaurantVerificationDTO>> submitVerification(
            @Valid @RequestBody VerificationRequest request) {
        RestaurantVerificationDTO verificationDTO = verificationService.submitVerification(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Verification submitted successfully", verificationDTO));
    }

    @GetMapping("/restaurant/{restaurantId}")
    @Operation(summary = "Get verification details by restaurant ID")
    public ResponseEntity<ApiResponse<RestaurantVerificationDTO>> getVerificationByRestaurantId(
            @PathVariable Long restaurantId) {
        RestaurantVerificationDTO verificationDTO = verificationService.getVerificationByRestaurantId(restaurantId);
        return ResponseEntity.ok(ApiResponse.success(verificationDTO));
    }

    @PutMapping("/{verificationId}/status")
    @Operation(summary = "Update verification status")
    public ResponseEntity<ApiResponse<RestaurantVerificationDTO>> updateVerificationStatus(
            @PathVariable Long verificationId,
            @RequestParam @Parameter(description = "New status (PENDING, IN_PROGRESS, APPROVED, REJECTED)") String status,
            @RequestParam(required = false) String notes) {
        RestaurantVerificationDTO verificationDTO = verificationService.updateVerificationStatus(verificationId, status, notes);
        return ResponseEntity.ok(ApiResponse.success("Verification status updated successfully", verificationDTO));
    }

    @GetMapping
    @Operation(summary = "Get verifications by status")
    public ResponseEntity<ApiResponse<List<RestaurantVerificationDTO>>> getVerificationsByStatus(
            @RequestParam @Parameter(description = "Status to filter by (PENDING, IN_PROGRESS, APPROVED, REJECTED)") String status) {
        List<RestaurantVerificationDTO> verifications = verificationService.getVerificationsByStatus(status);
        return ResponseEntity.ok(ApiResponse.success(verifications));
    }

    @GetMapping("/restaurant/{restaurantId}/status")
    @Operation(summary = "Check if a restaurant is verified")
    public ResponseEntity<ApiResponse<Boolean>> isRestaurantVerified(@PathVariable Long restaurantId) {
        boolean isVerified = verificationService.isRestaurantVerified(restaurantId);
        return ResponseEntity.ok(ApiResponse.success(isVerified));
    }
}
