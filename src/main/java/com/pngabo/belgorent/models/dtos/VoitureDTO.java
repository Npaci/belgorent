package com.pngabo.belgorent.models.dtos;

import com.pngabo.belgorent.models.EtatVoiture;
import com.pngabo.belgorent.models.TypeVoiture;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
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
    private TypeVoiture type;
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
        private Date date_debut;
        private Date date_fin;
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
