package com.pngabo.belgorent.models.forms;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModeleForm {
    private long id_modele;
    private String nom;
}
