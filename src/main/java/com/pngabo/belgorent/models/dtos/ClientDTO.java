package com.pngabo.belgorent.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ClientDTO {
    private long id;
    private String nom;
    private String prenom;
    private String username;
    private LocalDateTime date_naiss;
    private List<LocationIntern> locationInterns;
    private List<String> roles;

    @Data
    @Builder
    public static class LocationIntern {
        private long id_location;
        private String lieu_dep;
        private String lieu_arr;
        private LocalDateTime date_debut;
        private LocalDateTime date_fin;
    }
}
