package com.pngabo.belgorent.models.dtos;

import com.pngabo.belgorent.models.Carburant;
import com.pngabo.belgorent.models.EtatLocation;
import com.pngabo.belgorent.models.EtatVoiture;
import com.pngabo.belgorent.models.TypeVoiture;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LocationDTO {
    private long id_location;
    private String lieu_dep;
    private String lieu_arr;
    private LocalDateTime date_debut;
    private LocalDateTime date_fin;
    private ClientIntern client;
    private VoitureIntern voiture;
    private EtatLocation etat;

    @Data
    @Builder
    public static class ClientIntern {
        private long id_client;
        private String nom;
        private String prenom;
        private String username;
        private LocalDateTime date_naiss;
    }

    @Data
    @Builder
    public static class VoitureIntern {
        private long id_voiture;
        private double prix;
        private String couleur;
        private Carburant carburant;
        private double kilometre;
        private boolean manuelle;
        private TypeVoiture typev;
        private EtatVoiture etat;
        private String image;
        private String marque;
        private String modele;
    }

}
