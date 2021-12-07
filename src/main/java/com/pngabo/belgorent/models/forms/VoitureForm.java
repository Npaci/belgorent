package com.pngabo.belgorent.models.forms;

import com.pngabo.belgorent.models.Carburant;
import com.pngabo.belgorent.models.entities.Location;
import com.pngabo.belgorent.models.entities.Modele;
import com.pngabo.belgorent.models.entities.Option_v;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@Validated
public class VoitureForm {
    @NotNull
    private long id_voiture;
    @NotNull
    private long modele_id;
    @NotNull
    private double prix;
    @NotBlank
    private String couleur;
    @NotNull
    private Carburant carburant;
    @NotNull
    private double kilometre;
    @NotNull
    private boolean manuelle;
    @NotBlank
    private String type;
    @NotBlank
    private String etat;
    private List<Location> locationInterns;
    @NotNull
    private Modele modele;
    @NotBlank
    private String image;
    private List<Option_v> optionInterns;
}
