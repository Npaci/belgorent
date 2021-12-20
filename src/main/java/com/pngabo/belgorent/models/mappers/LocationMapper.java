package com.pngabo.belgorent.models.mappers;

import com.pngabo.belgorent.models.dtos.LocationDTO;
import com.pngabo.belgorent.models.entities.Client;
import com.pngabo.belgorent.models.entities.Location;
import com.pngabo.belgorent.models.entities.Voiture;
import com.pngabo.belgorent.models.forms.LocationForm;
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
                .client(LocationDTO.ClientIntern.builder()
                        .id_client(c.getId())
                        .nom(c.getNom())
                        .prenom(c.getPrenom())
                        .username(c.getUsername())
                        .date_naiss(c.getDate_naiss())
                        .build())
                .voiture(LocationDTO.VoitureIntern.builder()
                        .id_voiture(v.getId_voiture())
                        .carburant(v.getCarburant())
                        .couleur(v.getCouleur())
                        .etat(v.getEtat())
                        .kilometre(v.getKilometre())
                        .manuelle(v.isManuelle())
                        .prix(v.getPrix())
                        .typev(v.getType())
                        .image(v.getImage())
                        .marque(v.getModele().getMarque() != null? v.getModele().getMarque().getNom() : null )
                        .modele(v.getModele() != null? v.getModele().getNom() : null )
                        .build())
                .build();
    }

    @Override
    public Location formToEntity(LocationForm form) {
        return Location.builder()
                .id_location(form.getId_location())
                .date_debut(form.getDate_debut())
                .date_fin(form.getDate_fin())
                .lieu_dep(form.getLieu_dep())
                .lieu_arr(form.getLieu_arr())
                .client(form.getClient())
                .voiture(form.getVoiture())
                .build();
    }
}
