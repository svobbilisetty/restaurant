package com.dineflow.restaurant.dto.mapper;

import com.dineflow.restaurant.dto.RestaurantDTO;
import com.dineflow.restaurant.dto.request.CreateRestaurantRequest;
import com.dineflow.restaurant.entity.Restaurant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-28T16:38:06+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class RestaurantMapperImpl implements RestaurantMapper {

    @Override
    public Restaurant toEntity(RestaurantDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Restaurant.RestaurantBuilder restaurant = Restaurant.builder();

        restaurant.id( dto.getId() );
        restaurant.name( dto.getName() );
        restaurant.createdAt( dto.getCreatedAt() );
        restaurant.updatedAt( dto.getUpdatedAt() );

        return restaurant.build();
    }

    @Override
    public Restaurant toEntity(CreateRestaurantRequest request) {
        if ( request == null ) {
            return null;
        }

        Restaurant.RestaurantBuilder restaurant = Restaurant.builder();

        restaurant.name( request.getName() );
        restaurant.address( request.getAddress() );
        restaurant.phoneNumber( request.getPhoneNumber() );
        restaurant.email( request.getEmail() );

        return restaurant.build();
    }

    @Override
    public RestaurantDTO toDto(Restaurant entity) {
        if ( entity == null ) {
            return null;
        }

        RestaurantDTO.RestaurantDTOBuilder restaurantDTO = RestaurantDTO.builder();

        restaurantDTO.id( entity.getId() );
        restaurantDTO.name( entity.getName() );
        restaurantDTO.createdAt( entity.getCreatedAt() );
        restaurantDTO.updatedAt( entity.getUpdatedAt() );

        return restaurantDTO.build();
    }
}
