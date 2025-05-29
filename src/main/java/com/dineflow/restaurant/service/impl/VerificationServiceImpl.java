package com.dineflow.restaurant.service.impl;

import com.dineflow.restaurant.config.VerificationProperties;
import com.dineflow.restaurant.dto.RestaurantVerificationDTO;
import com.dineflow.restaurant.dto.mapper.VerificationMapper;
import com.dineflow.restaurant.dto.request.VerificationRequest;
import com.dineflow.restaurant.entity.Restaurant;
import com.dineflow.restaurant.entity.RestaurantVerification;
import com.dineflow.restaurant.entity.RestaurantVerification.VerificationStatus;
import com.dineflow.restaurant.exception.BadRequestException;
import com.dineflow.restaurant.exception.ResourceNotFoundException;
import com.dineflow.restaurant.repository.RestaurantRepository;
import com.dineflow.restaurant.repository.RestaurantVerificationRepository;
import com.dineflow.restaurant.service.VerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VerificationServiceImpl implements VerificationService {

    private final RestaurantVerificationRepository verificationRepository;
    private final RestaurantRepository restaurantRepository;
    private final VerificationMapper verificationMapper;
    private final VerificationProperties verificationProperties;

    @Override
    @Transactional
    public RestaurantVerificationDTO submitVerification(VerificationRequest request) {
        if (request == null || request.getRestaurantId() == null) {
            throw new BadRequestException("Invalid verification request: restaurant ID is required");
        }

        log.info("Submitting verification for restaurant ID: {}", request.getRestaurantId());

        try {
            // Check if restaurant exists
            Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                    .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", request.getRestaurantId()));

            // Get existing verification or create a new one if it doesn't exist
            RestaurantVerification verification = verificationRepository.findByRestaurant(restaurant)
                    .orElseGet(() -> createNewVerification(restaurant));

            // If verification is already APPROVED or REJECTED, don't allow updates
            if (verification.getVerificationStatus() == VerificationStatus.APPROVED ||
                verification.getVerificationStatus() == VerificationStatus.REJECTED) {
                throw new BadRequestException("Cannot update verification with status: " + verification.getVerificationStatus());
            }

            // Update verification fields
            updateVerificationFromRequest(verification, request);

            // Update status based on completeness
            VerificationStatus newStatus = determineVerificationStatus(verification);
            verification.setVerificationStatus(newStatus);

            log.info("Updating verification for restaurant ID: {}", request.getRestaurantId());
            RestaurantVerification savedVerification = verificationRepository.save(verification);
            log.info("Successfully updated verification for restaurant ID: {}", request.getRestaurantId());

            return verificationMapper.toDto(savedVerification);
        } catch (Exception e) {
            log.error("Error submitting verification for restaurant ID {}: {}",
                    request.getRestaurantId(), e.getMessage(), e);
            throw new BadRequestException("Error submitting verification: " + e.getMessage());
        }
    }

    private RestaurantVerification createNewVerification(Restaurant restaurant) {
        log.info("Creating new verification record for restaurant ID: {}", restaurant.getId());
        RestaurantVerification verification = new RestaurantVerification();
        verification.setRestaurant(restaurant);
        verification.setVerificationStatus(VerificationStatus.PENDING);
        return verificationRepository.save(verification);
    }

    private VerificationStatus determineVerificationStatus(RestaurantVerification verification) {
        // Check if all required fields are present and not empty
        if (isNullOrEmpty(verification.getFssaiLicenseImageUrl()) ||
            isNullOrEmpty(verification.getGstCertificateUrl()) ||
            isNullOrEmpty(verification.getPanCardUrl()) ||
            isNullOrEmpty(verification.getOwnerPanCardUrl()) ||
            isNullOrEmpty(verification.getOwnerAadhaarCardUrl()) ||
            isNullOrEmpty(verification.getCancelledChequeUrl()) ||
            isNullOrEmpty(verification.getLocationImageUrl())) {
            return VerificationStatus.IN_PROGRESS;
        }

        // If auto-approval is enabled, return APPROVED, otherwise PENDING
        return verificationProperties.isAutoApprove() ?
               VerificationStatus.APPROVED :
               VerificationStatus.PENDING;
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private void updateVerificationFromRequest(RestaurantVerification verification, VerificationRequest request) {
        // Only update fields that are provided in the request
        if (request.getFssaiLicenseNumber() != null) {
            verification.setFssaiLicenseNumber(request.getFssaiLicenseNumber().trim());
        }
        if (request.getFssaiLicenseImageUrl() != null) {
            verification.setFssaiLicenseImageUrl(request.getFssaiLicenseImageUrl().trim());
        }
        if (request.getGstNumber() != null) {
            verification.setGstNumber(request.getGstNumber().trim());
        }
        if (request.getGstCertificateUrl() != null) {
            verification.setGstCertificateUrl(request.getGstCertificateUrl().trim());
        }
        if (request.getPanNumber() != null) {
            verification.setPanNumber(request.getPanNumber().trim());
        }
        if (request.getPanCardUrl() != null) {
            verification.setPanCardUrl(request.getPanCardUrl().trim());
        }
        if (request.getOwnerPanCardUrl() != null) {
            verification.setOwnerPanCardUrl(request.getOwnerPanCardUrl());
        }
        if (request.getOwnerAadhaarCardUrl() != null) {
            verification.setOwnerAadhaarCardUrl(request.getOwnerAadhaarCardUrl());
        }
        if (request.getBankAccountNumber() != null) {
            verification.setBankAccountNumber(request.getBankAccountNumber());
        }
        if (request.getBankName() != null) {
            verification.setBankName(request.getBankName());
        }
        if (request.getBankIfscCode() != null) {
            verification.setBankIfscCode(request.getBankIfscCode());
        }
        if (request.getCancelledChequeUrl() != null) {
            verification.setCancelledChequeUrl(request.getCancelledChequeUrl());
        }
        if (request.getLocationImageUrl() != null) {
            verification.setLocationImageUrl(request.getLocationImageUrl());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantVerificationDTO getVerificationByRestaurantId(Long restaurantId) {
        log.debug("Fetching verification for restaurant ID: {}", restaurantId);

        RestaurantVerification verification = verificationRepository.findByRestaurantId(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Verification", "restaurantId", restaurantId));

        return verificationMapper.toDto(verification);
    }

    @Override
    @Transactional
    public RestaurantVerificationDTO updateVerificationStatus(Long verificationId, String status, String notes) {
        log.info("Updating verification status for verification ID: {}", verificationId);

        // Find verification
        RestaurantVerification verification = verificationRepository.findById(verificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Verification", "id", verificationId));

        // Parse status
        VerificationStatus verificationStatus;
        try {
            verificationStatus = VerificationStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid verification status: " + status);
        }

        // Update verification
        verification.setVerificationStatus(verificationStatus);
        verification.setVerificationNotes(notes);

        RestaurantVerification updatedVerification = verificationRepository.save(verification);
        log.info("Successfully updated verification status to {} for verification ID: {}", status, verificationId);

        return verificationMapper.toDto(updatedVerification);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantVerificationDTO> getVerificationsByStatus(String status) {
        log.debug("Fetching verifications with status: {}", status);

        VerificationStatus verificationStatus;
        try {
            verificationStatus = VerificationStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid verification status: " + status);
        }

        return verificationRepository.findByVerificationStatus(verificationStatus).stream()
                .map(verificationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isRestaurantVerified(Long restaurantId) {
        return verificationRepository.findByRestaurantId(restaurantId)
                .map(verification -> verification.getVerificationStatus() == VerificationStatus.APPROVED)
                .orElse(false);
    }
}
