package com.pngabo.belgorent.models.forms;

import com.pngabo.belgorent.models.entities.Modele;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class MarqueForm {
    @NotNull
    private long id_marque;
    @NotBlank
    private String nom;

    private List<Modele> listModeles;
}
