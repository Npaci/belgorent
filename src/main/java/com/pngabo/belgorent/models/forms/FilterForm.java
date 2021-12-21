package com.pngabo.belgorent.models.forms;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class FilterForm {
    @NotBlank
    private String marque;
    @NotBlank
    private String modele;
    @NotBlank
    private String couleur;
    @NotBlank
    private String typev;
    @NotBlank
    private String etat;
    @NotBlank
    private String carburant;
    @NotNull
    private boolean manuelle;
}
