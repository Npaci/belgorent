package com.pngabo.belgorent.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UtilisateurDTO {
    private long id;
    private String nom;
    private String prenom;
    private String username;
    private String date_naiss;
    private List<String> roles;
}
