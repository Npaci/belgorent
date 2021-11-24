package com.pngabo.belgorent.model.mappers;

public interface Mapper <ENTITY, DTO, FORM> {
    DTO entityToDTO(ENTITY entity);
    ENTITY formToEntity(FORM form);
}
