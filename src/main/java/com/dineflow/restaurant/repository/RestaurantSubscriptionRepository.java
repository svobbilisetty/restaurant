package com.dineflow.restaurant.repository;

import com.dineflow.restaurant.entity.Restaurant;
import com.dineflow.restaurant.entity.RestaurantSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantSubscriptionRepository extends JpaRepository<RestaurantSubscription, Long> {
    
    Optional<RestaurantSubscription> findByRestaurant(Restaurant restaurant);
    
    @Query("SELECT rs FROM RestaurantSubscription rs WHERE rs.restaurant.id = :restaurantId AND rs.endDate >= CURRENT_DATE")
    Optional<RestaurantSubscription> findActiveSubscriptionByRestaurantId(@Param("restaurantId") Long restaurantId);
    
    @Query("SELECT rs FROM RestaurantSubscription rs WHERE rs.endDate BETWEEN :startDate AND :endDate")
    List<RestaurantSubscription> findSubscriptionsExpiringBetween(@Param("startDate") LocalDate startDate, 
                                                                @Param("endDate") LocalDate endDate);
    
    boolean existsByRestaurantIdAndActiveTrue(Long restaurantId);
    
    @Query("SELECT rs FROM RestaurantSubscription rs WHERE rs.restaurant = :restaurant AND rs.endDate > :currentDate AND rs.active = true")
    Optional<RestaurantSubscription> findActiveSubscriptionByRestaurant(
            @Param("restaurant") Restaurant restaurant,
            @Param("currentDate") LocalDateTime currentDate
    );
}
