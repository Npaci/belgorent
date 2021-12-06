package com.pngabo.belgorent.models.forms;

import com.pngabo.belgorent.models.entities.Modele;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MarqueForm {
    private long id_marque;
    private String nom;

    private List<Modele> listModeles;
}
