package com.dineflow.restaurant.dto.mapper;

import com.dineflow.restaurant.dto.RestaurantSubscriptionDTO;
import com.dineflow.restaurant.entity.RestaurantSubscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for converting between subscription-related entities and DTOs
 */
@Mapper(componentModel = "spring", uses = {RestaurantMapper.class, SubscriptionPlanMapper.class})
public interface SubscriptionMapper extends BaseMapper<RestaurantSubscription, RestaurantSubscriptionDTO> {
    
    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);
    
    /**
     * Convert RestaurantSubscription entity to DTO
     *
     * @param entity the entity to convert
     * @return the DTO
     */
    @Override
    @Mappings({
        @Mapping(source = "restaurant", target = "restaurant"),
        @Mapping(source = "restaurant.id", target = "restaurantId"),
        @Mapping(source = "subscriptionPlan", target = "subscriptionPlan"),
        @Mapping(source = "subscriptionPlan.id", target = "subscriptionPlanId")
    })
    RestaurantSubscriptionDTO toDto(RestaurantSubscription entity);
    
    /**
     * Convert RestaurantSubscriptionDTO to entity
     *
     * @param dto the DTO to convert
     * @return the entity
     */
    @Override
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "restaurant", ignore = true),
        @Mapping(target = "subscriptionPlan", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true)
    })
    RestaurantSubscription toEntity(RestaurantSubscriptionDTO dto);
    
    /**
     * Update entity from DTO
     *
     * @param dto the DTO with updated values
     * @param entity the entity to update
     */
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "restaurant", ignore = true),
        @Mapping(target = "subscriptionPlan", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true)
    })
    void updateFromDto(RestaurantSubscriptionDTO dto, @MappingTarget RestaurantSubscription entity);
}
