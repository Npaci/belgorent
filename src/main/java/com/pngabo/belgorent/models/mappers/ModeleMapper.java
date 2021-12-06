package com.pngabo.belgorent.models.mappers;

import com.pngabo.belgorent.models.dtos.ModeleDTO;
import com.pngabo.belgorent.models.entities.Modele;
import com.pngabo.belgorent.models.forms.ModeleForm;

public class ModeleMapper implements Mapper<Modele, ModeleDTO, ModeleForm>{
    @Override
    public ModeleDTO entityToDTO(Modele modele) {
        return null;
    }

    @Override
    public Modele formToEntity(ModeleForm modeleForm) {
        return null;
    }
}
