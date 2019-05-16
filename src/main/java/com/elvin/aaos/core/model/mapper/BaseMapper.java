package com.elvin.aaos.core.model.mapper;

import java.util.List;

public abstract class BaseMapper<E,D> {
    public static final String SPRING_MODEL = "spring";

    public abstract D mapEntityToDto(E s);

    public abstract E mapDtoToEntity(D d);

    public abstract List<D> mapEntitiesToDtos(List<E> s);

    public abstract List<E> mapDtosToEntities(List<D> d);
}
