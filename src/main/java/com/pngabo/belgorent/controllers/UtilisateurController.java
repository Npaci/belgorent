package com.pngabo.belgorent.controllers;

import com.pngabo.belgorent.models.dtos.AdminDTO;
import com.pngabo.belgorent.models.dtos.UtilisateurDTO;
import com.pngabo.belgorent.models.entities.Client;
import com.pngabo.belgorent.models.entities.Utilisateur;
import com.pngabo.belgorent.services.UtilisateurServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UtilisateurController {
    private final UtilisateurServiceImpl service;

    public UtilisateurController(UtilisateurServiceImpl service) {
        this.service = service;
    }

    @GetMapping(params = "username")
    public UtilisateurDTO getOne(@RequestParam String username) {
        Utilisateur user = (Utilisateur) service.loadUserByUsername(username);
        UtilisateurDTO.UtilisateurDTOBuilder builder = UtilisateurDTO.builder()
                .id(user.getId())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .username(user.getUsername())
                .roles(user.getRoles().stream()
                        .map(elm -> elm.getNom())
                        .collect(Collectors.toList()));

        if (user instanceof Client)
            builder.date_naiss(((Client)user).getDate_naiss().toString());

        return builder.build();
    }
}
