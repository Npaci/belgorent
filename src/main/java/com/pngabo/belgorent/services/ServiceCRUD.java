package com.pngabo.belgorent.services;

import java.util.List;

public interface ServiceCRUD<DTO, Form, ID> {
    List<DTO> getAll();
    DTO getOne(ID id);
    DTO insert(Form form);
    DTO delete(ID id);
    DTO update(Form form);
}
