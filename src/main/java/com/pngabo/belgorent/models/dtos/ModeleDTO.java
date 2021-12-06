package com.pngabo.belgorent.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModeleDTO {
    private long id_modele;
    private String nom;
    private MarqueIntern marqueIntern;

    @Data
    @Builder
    public static class MarqueIntern {
        private long id_marque;
        private String nom;
    }
}
