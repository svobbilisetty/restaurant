package com.dineflow.restaurant.dto.mapper;

import com.dineflow.restaurant.dto.RestaurantSubscriptionDTO;
import com.dineflow.restaurant.entity.Restaurant;
import com.dineflow.restaurant.entity.RestaurantSubscription;
import com.dineflow.restaurant.entity.SubscriptionPlan;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-28T16:38:06+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class SubscriptionMapperImpl implements SubscriptionMapper {

    @Autowired
    private RestaurantMapper restaurantMapper;
    @Autowired
    private SubscriptionPlanMapper subscriptionPlanMapper;

    @Override
    public RestaurantSubscriptionDTO toDto(RestaurantSubscription entity) {
        if ( entity == null ) {
            return null;
        }

        RestaurantSubscriptionDTO.RestaurantSubscriptionDTOBuilder restaurantSubscriptionDTO = RestaurantSubscriptionDTO.builder();

        restaurantSubscriptionDTO.restaurant( restaurantMapper.toDto( entity.getRestaurant() ) );
        restaurantSubscriptionDTO.restaurantId( entityRestaurantId( entity ) );
        restaurantSubscriptionDTO.subscriptionPlan( subscriptionPlanMapper.toDto( entity.getSubscriptionPlan() ) );
        restaurantSubscriptionDTO.subscriptionPlanId( entitySubscriptionPlanId( entity ) );
        restaurantSubscriptionDTO.id( entity.getId() );
        restaurantSubscriptionDTO.startDate( entity.getStartDate() );
        restaurantSubscriptionDTO.endDate( entity.getEndDate() );
        restaurantSubscriptionDTO.createdAt( entity.getCreatedAt() );
        restaurantSubscriptionDTO.updatedAt( entity.getUpdatedAt() );

        return restaurantSubscriptionDTO.build();
    }

    @Override
    public RestaurantSubscription toEntity(RestaurantSubscriptionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RestaurantSubscription.RestaurantSubscriptionBuilder restaurantSubscription = RestaurantSubscription.builder();

        restaurantSubscription.startDate( dto.getStartDate() );
        restaurantSubscription.endDate( dto.getEndDate() );

        return restaurantSubscription.build();
    }

    @Override
    public void updateFromDto(RestaurantSubscriptionDTO dto, RestaurantSubscription entity) {
        if ( dto == null ) {
            return;
        }

        entity.setStartDate( dto.getStartDate() );
        entity.setEndDate( dto.getEndDate() );
    }

    private Long entityRestaurantId(RestaurantSubscription restaurantSubscription) {
        if ( restaurantSubscription == null ) {
            return null;
        }
        Restaurant restaurant = restaurantSubscription.getRestaurant();
        if ( restaurant == null ) {
            return null;
        }
        Long id = restaurant.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entitySubscriptionPlanId(RestaurantSubscription restaurantSubscription) {
        if ( restaurantSubscription == null ) {
            return null;
        }
        SubscriptionPlan subscriptionPlan = restaurantSubscription.getSubscriptionPlan();
        if ( subscriptionPlan == null ) {
            return null;
        }
        Long id = subscriptionPlan.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
