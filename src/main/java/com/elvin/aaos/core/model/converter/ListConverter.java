package com.elvin.aaos.core.model.converter;

import java.util.List;

public interface ListConverter<E, D> {

    /***
     * Convert Entity List to DTO list
     *
     * @param entityList
     * @return
     */
    List<D> convertToDtoList(List<E> entityList);

    /***
     * Convert DTO List to Entity List
     *
     * @param dtoList
     * @return
     */
    List<E> convertToEntityList(List<D> dtoList);

}
