package com.pngabo.belgorent.model.mappers;

import com.pngabo.belgorent.model.Carburant;
import com.pngabo.belgorent.model.EtatVoiture;
import com.pngabo.belgorent.model.TypeVoiture;
import com.pngabo.belgorent.model.dtos.VoitureDTO;
import com.pngabo.belgorent.model.entities.Voiture;
import com.pngabo.belgorent.model.forms.VoitureForm;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class VoitureMapper implements Mapper<Voiture, VoitureDTO, VoitureForm> {
//    private final ModeleRepository;
    @Override
    public VoitureDTO entityToDTO(Voiture voiture) {
        return VoitureDTO.builder()
                .id_voiture(voiture.getId_voiture())
                .carburant(voiture.getCarburant().name())
                .couleur(voiture.getCouleur())
                .etat(voiture.getEtat())
                .kilometre(voiture.getKilometre())
                .locationInterns(voiture.getListLocations().stream()
                        .map((loc) -> {
                            return VoitureDTO.LocationIntern.builder()
                                .id_location(loc.getId_location())
                                .date_debut(loc.getDate_debut())
                                .date_fin(loc.getDate_fin())
                                .lieu_dep(loc.getLieu_dep())
                                .lieu_arr(loc.getLieu_arr())
                                .build();
                        })
                        .collect(Collectors.toList()))
                .manuelle(voiture.isManuelle())
                .modeleIntern(VoitureDTO.ModeleIntern.builder()
                        .id_modele(voiture.getModele().getId_modele())
                        .nom(voiture.getModele().getNom())
                        .nomMarque(voiture.getModele().getMarque().getNom())
                        .build())
                .optionInterns(voiture.getListOptions().stream()
                        .map((op) -> {
                             return VoitureDTO.OptionIntern.builder()
                                    .id_option(op.getId_option())
                                    .nom(op.getNom())
                                    .prix(op.getPrix())
                                    .build();
                        })
                        .collect(Collectors.toList()))
                .prix(voiture.getPrix())
                .type(voiture.getType())
                .build();
    }

    @Override
    public Voiture formToEntity(VoitureForm form) {
        return Voiture.builder()
                .id_voiture(form.getId_voiture())
                .carburant(form.getCarburant())
                .couleur(form.getCouleur())
                .etat(EtatVoiture.valueOf(form.getEtat()))
                .kilometre(form.getKilometre())
                .manuelle(form.isManuelle())
                .type(TypeVoiture.valueOf(form.getType()))
                .prix(form.getPrix())
                .modele(form.getModeleIntern())
                .listLocations(form.getLocationInterns())
                .listOptions(form.getOptionInterns())
                .build();
    }
}
