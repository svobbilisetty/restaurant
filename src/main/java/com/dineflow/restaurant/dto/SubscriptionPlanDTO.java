package com.dineflow.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionPlanDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer durationDays;
    private Boolean isTrial;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
