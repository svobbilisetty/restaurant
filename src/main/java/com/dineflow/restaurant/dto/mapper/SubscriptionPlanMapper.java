package com.dineflow.restaurant.dto.mapper;

import com.dineflow.restaurant.dto.SubscriptionPlanDTO;
import com.dineflow.restaurant.entity.SubscriptionPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for converting between SubscriptionPlan entity and DTO
 */
@Mapper(componentModel = "spring")
public interface SubscriptionPlanMapper extends BaseMapper<SubscriptionPlan, SubscriptionPlanDTO> {
    
    SubscriptionPlanMapper INSTANCE = Mappers.getMapper(SubscriptionPlanMapper.class);
    
    /**
     * Convert SubscriptionPlan entity to DTO
     *
     * @param entity the entity to convert
     * @return the DTO
     */
    @Override
    @Mappings({
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true)
    })
    SubscriptionPlanDTO toDto(SubscriptionPlan entity);
    
    /**
     * Convert DTO to SubscriptionPlan entity
     *
     * @param dto the DTO to convert
     * @return the entity
     */
    @Override
    @Mappings({
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true)
    })
    SubscriptionPlan toEntity(SubscriptionPlanDTO dto);
}
