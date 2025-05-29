package com.dineflow.restaurant.dto.mapper;

/**
 * Base mapper interface for mapping between entities and DTOs
 *
 * @param <E> Entity type
 * @param <D> DTO type
 */
public interface BaseMapper<E, D> {
    
    /**
     * Convert entity to DTO
     *
     * @param entity the entity to convert
     * @return the DTO
     */
    D toDto(E entity);
    
    /**
     * Convert DTO to entity
     *
     * @param dto the DTO to convert
     * @return the entity
     */
    E toEntity(D dto);
}
