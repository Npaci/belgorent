package com.pngabo.belgorent.models.mappers;

public interface Mapper <ENTITY, DTO, FORM> {
    DTO entityToDTO(ENTITY entity);
    ENTITY formToEntity(FORM form);
}
