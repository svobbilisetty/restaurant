package com.dineflow.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "restaurant", schema = "restaurants")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "cuisine_type")
    private String cuisineType;
    
    @Column(columnDefinition = "TEXT")
    private String address;
    
    private String city;
    private String state;
    private String country;
    
    @Column(name = "postal_code")
    private String postalCode;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(unique = true)
    private String email;
    
    private String website;
    
    @Column(name = "image_url", length = 1000)
    private String imageUrl;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private boolean active = true;
}
