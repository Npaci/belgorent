package com.pngabo.belgorent.models.forms;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class FilterForm {
    private String marque;
    private String modele;
    private String couleur;
    private String typev;
    private String etat;
    private String carburant;
    private boolean manuelle;
}
