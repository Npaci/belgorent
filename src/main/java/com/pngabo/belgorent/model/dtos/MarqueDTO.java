package com.pngabo.belgorent.model.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MarqueDTO {
    private long id_marque;
    private String nom;
    private List<ModeleIntern> modeleInterns;

    @Data
    @Builder
    public static class ModeleIntern {
        private long id_modele;
        private String nom;
    }
}
