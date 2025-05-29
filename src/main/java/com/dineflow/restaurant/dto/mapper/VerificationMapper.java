package com.dineflow.restaurant.dto.mapper;

import com.dineflow.restaurant.dto.RestaurantVerificationDTO;
import com.dineflow.restaurant.dto.request.VerificationRequest;
import com.dineflow.restaurant.entity.RestaurantVerification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for converting between RestaurantVerification entity and DTOs
 */
@Mapper(componentModel = "spring", uses = {RestaurantMapper.class})
public interface VerificationMapper {
    
    VerificationMapper INSTANCE = Mappers.getMapper(VerificationMapper.class);
    
    /**
     * Convert VerificationRequest to RestaurantVerification entity
     *
     * @param request the request DTO
     * @return the entity
     */
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "restaurant", ignore = true),
        @Mapping(target = "verificationStatus", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(source = "locationImageUrl", target = "locationImageUrl")
    })
    RestaurantVerification toEntity(VerificationRequest request);
    
    /**
     * Convert RestaurantVerification entity to DTO
     *
     * @param entity the entity to convert
     * @return the DTO
     */
    @Mappings({
        @Mapping(source = "restaurant", target = "restaurant"),
        @Mapping(source = "locationImageUrl", target = "locationImageUrl")
    })
    RestaurantVerificationDTO toDto(RestaurantVerification entity);
}
