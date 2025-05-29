package com.dineflow.restaurant.service.impl;

import com.dineflow.restaurant.dto.RestaurantSubscriptionDTO;
import com.dineflow.restaurant.dto.SubscriptionPlanDTO;
import com.dineflow.restaurant.dto.mapper.SubscriptionMapper;
import com.dineflow.restaurant.dto.mapper.SubscriptionPlanMapper;
import com.dineflow.restaurant.dto.request.SubscriptionRequest;
import com.dineflow.restaurant.entity.Restaurant;
import com.dineflow.restaurant.entity.RestaurantSubscription;
import com.dineflow.restaurant.entity.SubscriptionPlan;
import com.dineflow.restaurant.exception.BadRequestException;
import com.dineflow.restaurant.exception.ResourceNotFoundException;
import com.dineflow.restaurant.repository.RestaurantRepository;
import com.dineflow.restaurant.repository.RestaurantSubscriptionRepository;
import com.dineflow.restaurant.repository.SubscriptionPlanRepository;
import com.dineflow.restaurant.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

    private final RestaurantSubscriptionRepository restaurantSubscriptionRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final RestaurantRepository restaurantRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionPlanMapper subscriptionPlanMapper;

    @Override
    @Transactional
    public RestaurantSubscriptionDTO allocateFreeTrial(Long restaurantId) {
        log.info("Allocating free trial for restaurant ID: {}", restaurantId);

        // Check if restaurant exists
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", restaurantId));

        // Check if restaurant already has an active subscription
        if (restaurantSubscriptionRepository.existsByRestaurantIdAndActiveTrue(restaurantId)) {
            throw new BadRequestException("Restaurant already has an active subscription");
        }

        // Find a free trial subscription plan
        List<SubscriptionPlan> trialPlans = subscriptionPlanRepository.findByIsTrial(true);
        if (trialPlans.isEmpty()) {
            throw new ResourceNotFoundException("No free trial subscription plan found");
        }

        // Use the first available trial plan
        SubscriptionPlan trialPlan = trialPlans.get(0);
        LocalDate now = LocalDate.now();

        // Create subscription
        RestaurantSubscription subscription = RestaurantSubscription.builder()
                .restaurant(restaurant)
                .subscriptionPlan(trialPlan)
                .startDate(now)
                .endDate(now.plusDays(trialPlan.getDurationDays()))
                .active(true)
                .build();

        RestaurantSubscription savedSubscription = restaurantSubscriptionRepository.save(subscription);
        log.info("Successfully allocated free trial to restaurant ID: {}", restaurantId);

        return subscriptionMapper.toDto(savedSubscription);
    }

    @Override
    @Transactional
    public RestaurantSubscriptionDTO updateSubscription(SubscriptionRequest request) {
        log.info("Updating subscription for restaurant ID: {}", request.getRestaurantId());

        // Validate request
        if (request.getRestaurantId() == null) {
            throw new BadRequestException("Restaurant ID is required");
        }

        if (request.getSubscriptionPlanId() == null) {
            throw new BadRequestException("Subscription plan ID is required");
        }

        // Get existing subscription
        RestaurantSubscription existingSubscription = restaurantSubscriptionRepository
                .findByRestaurant(restaurantRepository.findById(request.getRestaurantId())
                        .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", request.getRestaurantId())))
                .orElseThrow(() -> new ResourceNotFoundException("Subscription", "restaurantId", request.getRestaurantId()));

        // Get new subscription plan
        SubscriptionPlan newPlan = subscriptionPlanRepository.findById(request.getSubscriptionPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("SubscriptionPlan", "id", request.getSubscriptionPlanId()));

        // Update subscription
        LocalDate startDate = request.getStartDate() != null ? request.getStartDate() : LocalDate.now();
        LocalDate endDate = request.getEndDate() != null ? request.getEndDate() :
                startDate.plusDays(newPlan.getDurationDays());

        existingSubscription.setSubscriptionPlan(newPlan);
        existingSubscription.setStartDate(startDate);
        existingSubscription.setEndDate(endDate);
        existingSubscription.setActive(true);

        RestaurantSubscription updatedSubscription = restaurantSubscriptionRepository.save(existingSubscription);
        log.info("Successfully updated subscription for restaurant ID: {}", request.getRestaurantId());

        return subscriptionMapper.toDto(updatedSubscription);
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantSubscriptionDTO getSubscriptionByRestaurantId(Long restaurantId) {
        log.debug("Fetching subscription for restaurant ID: {}", restaurantId);

        Restaurant subscription = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", restaurantId));
        RestaurantSubscription restaurantSubscription = restaurantSubscriptionRepository.findByRestaurant(subscription)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription", "restaurantId", restaurantId));

        return subscriptionMapper.toDto(restaurantSubscription);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionPlanDTO> getAllSubscriptionPlans() {
        log.debug("Fetching all subscription plans");

        return subscriptionPlanRepository.findAll().stream()
                .map(subscriptionPlanMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasActiveSubscription(Long restaurantId) {
        return restaurantSubscriptionRepository.existsByRestaurantIdAndActiveTrue(restaurantId);
    }
}
