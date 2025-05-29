package com.dineflow.restaurant.repository;

import com.dineflow.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    Optional<Restaurant> findByName(String name);
    List<Restaurant> findByActiveTrue();
}
