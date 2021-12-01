package com.pngabo.belgorent.model.dtos;

import com.pngabo.belgorent.model.Carburant;
import com.pngabo.belgorent.model.EtatVoiture;
import com.pngabo.belgorent.model.TypeVoiture;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LocationDTO {
    private long id_location;
    private String lieu_dep;
    private String lieu_arr;
    private Date date_debut;
    private Date date_fin;
    private ClientIntern client;
    private VoitureIntern voiture;

    @Data
    @Builder
    public static class ClientIntern {
        private long id_client;
        private String nom;
        private String prenom;
        private Date date_naiss;
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
        private TypeVoiture type;
        private EtatVoiture etat;
        private String marque;
        private String modele;
    }

}
