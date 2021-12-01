package com.pngabo.belgorent.model.forms;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModeleForm {
    private long id_marque;
    private String nom;
}
