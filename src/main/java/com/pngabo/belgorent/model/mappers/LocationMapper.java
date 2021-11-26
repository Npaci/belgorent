package com.pngabo.belgorent.model.mappers;

import com.pngabo.belgorent.model.dtos.LocationDTO;
import com.pngabo.belgorent.model.entities.Client;
import com.pngabo.belgorent.model.entities.Location;
import com.pngabo.belgorent.model.entities.Voiture;
import com.pngabo.belgorent.model.forms.LocationForm;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper implements Mapper<Location, LocationDTO, LocationForm>{
    @Override
    public LocationDTO entityToDTO(Location entity) {
        Client c = entity.getClient();
        Voiture v = entity.getVoiture();
        return LocationDTO.builder()
                .id_location(entity.getId_location())
                .date_debut(entity.getDate_debut())
                .date_fin(entity.getDate_fin())
                .lieu_dep(entity.getLieu_dep())
                .lieu_arr(entity.getLieu_arr())
                .clientIntern(LocationDTO.ClientIntern.builder()
                        .id_client(c.getId_client())
                        .nom(c.getNom())
                        .prenom(c.getPrenom())
                        .date_naiss(c.getDate_naiss())
                        .build())
                .voitureIntern(LocationDTO.VoitureIntern.builder()
                        .id_voiture(v.getId_voiture())
                        .carburant(v.getCarburant().carburant)
                        .couleur(v.getCouleur())
                        .etat(v.getEtat().etat)
                        .kilometre(v.getKilometre())
                        .manuelle(v.isManuelle())
                        .prix(v.getPrix())
                        .type(v.getType().type)
                        .marque(v.getModele().getMarque().getNom())
                        .modele(v.getModele().getNom())
                        .build())
                .build();
    }

    @Override
    public Location formToEntity(LocationForm form) {
        return null;
    }
}
