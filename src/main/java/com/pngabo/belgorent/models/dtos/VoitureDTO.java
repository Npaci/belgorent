package com.pngabo.belgorent.models.dtos;

import com.pngabo.belgorent.models.EtatLocation;
import com.pngabo.belgorent.models.EtatVoiture;
import com.pngabo.belgorent.models.TypeVoiture;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class VoitureDTO {
    private long id_voiture;
    private double prix;
    private String couleur;
    private String carburant;
    private double kilometre;
    private boolean manuelle;
    private TypeVoiture typev;
    private EtatVoiture etat;
    private String image;
    private List<LocationIntern> locationInterns;
    private ModeleIntern modeleIntern;
    private List<OptionIntern> optionInterns;

    @Data
    @Builder
    public static class LocationIntern {
        private long id_location;
        private String lieu_dep;
        private String lieu_arr;
        private LocalDateTime date_debut;
        private LocalDateTime date_fin;
        private EtatLocation etat;
    }

    @Data
    @Builder
    public static class ModeleIntern {
        private long id_modele;
        private String nom;
        private String nomMarque;
    }

    @Data
    @Builder
    public static class OptionIntern {
        private long id_option;
        private String nom;
        private double prix;
    }
}
