package com.pngabo.belgorent.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class AdminDTO {
    private long id;
    private String nom;
    private String prenom;
    private String username;
    private List<String> roles;
}
