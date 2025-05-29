package com.dineflow.restaurant.dto.mapper;

import com.dineflow.restaurant.dto.RestaurantDTO;
import com.dineflow.restaurant.dto.request.CreateRestaurantRequest;
import com.dineflow.restaurant.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for converting between Restaurant entity and DTOs
 */
@Mapper(componentModel = "spring",
        uses = {SubscriptionPlanMapper.class},
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface RestaurantMapper extends BaseMapper<Restaurant, RestaurantDTO> {
    
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
    
    /**
     * Convert CreateRestaurantRequest to Restaurant entity
     *
     * @param request the request DTO
     * @return the Restaurant entity
     */
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true)
    })
    Restaurant toEntity(CreateRestaurantRequest request);
    
    /**
     * Convert Restaurant entity to DTO
     *
     * @param entity the entity to convert
     * @return the DTO
     */
    @Override
    RestaurantDTO toDto(Restaurant entity);
}
