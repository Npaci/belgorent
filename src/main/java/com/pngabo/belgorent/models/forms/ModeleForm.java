package com.pngabo.belgorent.models.forms;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ModeleForm {
    @NotNull
    private long id_modele;
    @NotBlank
    private String nom;
}
