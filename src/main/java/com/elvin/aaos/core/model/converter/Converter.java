package com.elvin.aaos.core.model.converter;

public interface Converter<E, D> {

    /***
     * Convert DTO to Entity
     *
     * @param dto
     * @return
     */
    E convertToEntity(D dto);

    /***
     * Convert Entity to DTO
     *
     * @param entity
     * @return
     * @throws Exception
     */
    D convertToDto(E entity);

}
