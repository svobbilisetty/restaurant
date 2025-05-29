package com.dineflow.restaurant.repository;

import com.dineflow.restaurant.entity.Restaurant;
import com.dineflow.restaurant.entity.RestaurantVerification;
import com.dineflow.restaurant.entity.RestaurantVerification.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantVerificationRepository extends JpaRepository<RestaurantVerification, Long> {
    
    Optional<RestaurantVerification> findByRestaurant(Restaurant restaurant);
    
    Optional<RestaurantVerification> findByRestaurantId(Long restaurantId);
    
    List<RestaurantVerification> findByVerificationStatus(VerificationStatus status);
    
    boolean existsByRestaurant(Restaurant restaurant);
    
    boolean existsByRestaurantAndVerificationStatus(Restaurant restaurant, VerificationStatus status);
}
