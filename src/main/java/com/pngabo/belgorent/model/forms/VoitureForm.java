package com.pngabo.belgorent.model.forms;

import com.pngabo.belgorent.model.entities.Location;
import com.pngabo.belgorent.model.entities.Modele;
import com.pngabo.belgorent.model.entities.Option;
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
    private double prix;
    @NotBlank
    private String couleur;
    @NotBlank
    private String carburant;
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
    private Modele modeleIntern;
    private List<Option> optionInterns;
}
