package com.dineflow.restaurant.dto.mapper;

import com.dineflow.restaurant.dto.SubscriptionPlanDTO;
import com.dineflow.restaurant.entity.SubscriptionPlan;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-28T16:38:06+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class SubscriptionPlanMapperImpl implements SubscriptionPlanMapper {

    @Override
    public SubscriptionPlanDTO toDto(SubscriptionPlan entity) {
        if ( entity == null ) {
            return null;
        }

        SubscriptionPlanDTO.SubscriptionPlanDTOBuilder subscriptionPlanDTO = SubscriptionPlanDTO.builder();

        subscriptionPlanDTO.id( entity.getId() );
        subscriptionPlanDTO.name( entity.getName() );
        subscriptionPlanDTO.price( entity.getPrice() );
        subscriptionPlanDTO.durationDays( entity.getDurationDays() );
        subscriptionPlanDTO.isTrial( entity.getIsTrial() );

        return subscriptionPlanDTO.build();
    }

    @Override
    public SubscriptionPlan toEntity(SubscriptionPlanDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SubscriptionPlan.SubscriptionPlanBuilder subscriptionPlan = SubscriptionPlan.builder();

        subscriptionPlan.id( dto.getId() );
        subscriptionPlan.name( dto.getName() );
        subscriptionPlan.price( dto.getPrice() );
        subscriptionPlan.durationDays( dto.getDurationDays() );
        subscriptionPlan.isTrial( dto.getIsTrial() );

        return subscriptionPlan.build();
    }
}
