package com.pngabo.belgorent.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OptionDTO {
    private long id_option;
    private String nom;
    private double prix;
    private List<VoitureIntern> voitureInterns;

    @Data
    @Builder
    public static class VoitureIntern {
        private long id_voiture;
        private String VIN;
        private double prix;
        private String couleur;
        private String carburant;
        private double kilometre;
        private boolean manuelle;
        private String type;
        private String etat;
    }
}
