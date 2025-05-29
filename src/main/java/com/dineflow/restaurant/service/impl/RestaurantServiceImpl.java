package com.dineflow.restaurant.service.impl;

import com.dineflow.restaurant.dto.RestaurantDTO;
import com.dineflow.restaurant.dto.mapper.RestaurantMapper;
import com.dineflow.restaurant.dto.request.CreateRestaurantRequest;
import com.dineflow.restaurant.dto.request.UpdateRestaurantRequest;
import com.dineflow.restaurant.entity.Restaurant;
import com.dineflow.restaurant.entity.RestaurantSubscription;
import com.dineflow.restaurant.entity.RestaurantVerification;
import com.dineflow.restaurant.entity.SubscriptionPlan;
import com.dineflow.restaurant.exception.BadRequestException;
import com.dineflow.restaurant.exception.ResourceNotFoundException;
import com.dineflow.restaurant.repository.RestaurantRepository;
import com.dineflow.restaurant.repository.RestaurantSubscriptionRepository;
import com.dineflow.restaurant.repository.RestaurantVerificationRepository;
import com.dineflow.restaurant.repository.SubscriptionPlanRepository;
import com.dineflow.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantSubscriptionRepository restaurantSubscriptionRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final RestaurantVerificationRepository verificationRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    @Transactional
    public RestaurantDTO onboardRestaurant(CreateRestaurantRequest request) {
        log.info("Onboarding new restaurant: {}", request.getName());

        // Check if restaurant with the same name already exists
        if (restaurantRepository.existsByName(request.getName())) {
            throw new BadRequestException("Restaurant with name " + request.getName() + " already exists");
        }

        // Check if restaurant with the same email already exists
        if (request.getEmail() != null && restaurantRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email " + request.getEmail() + " is already registered with another restaurant");
        }

        try {
            // Map request to entity
            Restaurant restaurant = restaurantMapper.toEntity(request);

            // Save the restaurant
            Restaurant savedRestaurant = restaurantRepository.save(restaurant);

            // Create initial verification record
            createInitialVerificationRecord(savedRestaurant);

            log.info("Successfully onboarded restaurant with ID: {}", savedRestaurant.getId());
            return restaurantMapper.toDto(savedRestaurant);
        } catch (Exception e) {
            log.error("Error while onboarding restaurant: {}", e.getMessage());
            throw new BadRequestException("Error while creating restaurant. Please try again.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantDTO getRestaurantById(Long id) {
        log.debug("Fetching restaurant with ID: {}", id);

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", id));

        return restaurantMapper.toDto(restaurant);
    }

    @Override
    public boolean existsById(Long id) {
        return restaurantRepository.existsById(id);
    }

    @Override
    @Transactional
    public RestaurantDTO deactivateRestaurant(Long id) {
        log.info("Deactivating restaurant with ID: {}", id);

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", id));

        if (!restaurant.isActive()) {
            throw new BadRequestException("Restaurant is already deactivated");
        }

        restaurant.setActive(false);
        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        log.info("Successfully deactivated restaurant with ID: {}", id);

        return restaurantMapper.toDto(updatedRestaurant);
    }

    @Override
    @Transactional
    public RestaurantDTO updateRestaurant(Long id, UpdateRestaurantRequest request) {
        log.info("Updating restaurant with ID: {}", id);

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", id));

        // Check if another restaurant with the same email already exists
        if (request.getEmail() != null && !request.getEmail().equals(restaurant.getEmail()) &&
                restaurantRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email " + request.getEmail() + " is already registered with another restaurant");
        }

        try {
            // Update fields from request
            restaurant.setName(request.getName());
            restaurant.setDescription(request.getDescription());
            restaurant.setCuisineType(request.getCuisineType());
            restaurant.setAddress(request.getAddress());
            restaurant.setCity(request.getCity());
            restaurant.setState(request.getState());
            restaurant.setCountry(request.getCountry());
            restaurant.setPostalCode(request.getPostalCode());
            restaurant.setPhoneNumber(request.getPhoneNumber());
            restaurant.setEmail(request.getEmail());
            restaurant.setWebsite(request.getWebsite());
            restaurant.setImageUrl(request.getImageUrl());

            Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
            log.info("Successfully updated restaurant with ID: {}", id);

            return restaurantMapper.toDto(updatedRestaurant);
        } catch (Exception e) {
            log.error("Error while updating restaurant: {}", e.getMessage());
            throw new BadRequestException("Error while updating restaurant. Please try again.");
        }
    }

    private void createInitialVerificationRecord(Restaurant restaurant) {
        try {
            // Check if verification already exists (double-check in case of race condition)
            if (verificationRepository.existsByRestaurant(restaurant)) {
                log.warn("Verification record already exists for restaurant ID: {}", restaurant.getId());
                return; // Skip creation if it already exists
            }

            // Create verification record with PENDING status and all fields null
            RestaurantVerification verification = new RestaurantVerification();
            verification.setRestaurant(restaurant);
            verification.setVerificationStatus(RestaurantVerification.VerificationStatus.PENDING);
            
            // Explicitly set all other fields to null
            verification.setFssaiLicenseNumber(null);
            verification.setFssaiLicenseImageUrl(null);
            verification.setGstNumber(null);
            verification.setGstCertificateUrl(null);
            verification.setPanNumber(null);
            verification.setPanCardUrl(null);
            verification.setOwnerPanCardUrl(null);
            verification.setOwnerAadhaarCardUrl(null);
            verification.setBankAccountNumber(null);
            verification.setBankName(null);
            verification.setBankIfscCode(null);
            verification.setCancelledChequeUrl(null);
            verification.setLocationImageUrl(null);
            verification.setVerificationNotes(null);

            verificationRepository.save(verification);
            log.info("Created initial verification record for restaurant ID: {}", restaurant.getId());
        } catch (Exception e) {
            log.error("Error creating initial verification record for restaurant ID {}: {}", 
                    restaurant.getId(), e.getMessage(), e);
            // Don't fail the entire operation if verification record creation fails
            // The verification can be created later via the verification endpoint
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantDTO> getRestaurants() {
        log.debug("Fetching all active restaurants");
        List<Restaurant> activeRestaurants = restaurantRepository.findByActiveTrue();
        if (activeRestaurants.isEmpty()) {
            throw new ResourceNotFoundException("Restaurant", "active status");
        }

        return activeRestaurants.stream()
                .map(restaurantMapper::toDto)
                .collect(Collectors.toList());
    }


}
