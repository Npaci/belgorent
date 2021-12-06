package com.pngabo.belgorent.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ClientDTO {
    private long id_client;
    private String nom;
    private String prenom;
    private Date date_naiss;
    private List<LocationIntern> locationInterns;

    @Data
    @Builder
    public static class LocationIntern {
        private long id_location;
        private String lieu_dep;
        private String lieu_arr;
        private Date date_debut;
        private Date date_fin;
    }
}
