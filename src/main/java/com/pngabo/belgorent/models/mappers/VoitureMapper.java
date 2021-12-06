package com.pngabo.belgorent.models.mappers;

import com.pngabo.belgorent.models.EtatVoiture;
import com.pngabo.belgorent.models.TypeVoiture;
import com.pngabo.belgorent.models.dtos.VoitureDTO;
import com.pngabo.belgorent.models.entities.Voiture;
import com.pngabo.belgorent.models.forms.VoitureForm;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class VoitureMapper implements Mapper<Voiture, VoitureDTO, VoitureForm> {
//    private final ModeleRepository;
    @Override
    public VoitureDTO entityToDTO(Voiture voiture) {
        VoitureDTO.VoitureDTOBuilder builder =  VoitureDTO.builder()
                .id_voiture(voiture.getId_voiture())
                .carburant(voiture.getCarburant().name())
                .couleur(voiture.getCouleur())
                .etat(voiture.getEtat())
                .kilometre(voiture.getKilometre())
                .manuelle(voiture.isManuelle())
                .modeleIntern(VoitureDTO.ModeleIntern.builder()
                        .id_modele(voiture.getModele().getId_modele())
                        .nom(voiture.getModele().getNom())
                        .nomMarque( voiture.getModele().getMarque() != null ? voiture.getModele().getMarque().getNom() : "" )
                        .build())
                .prix(voiture.getPrix())
                .type(voiture.getType());

        if( voiture.getListLocations() != null)
            builder.locationInterns(voiture.getListLocations().stream()
                .map((loc) -> {
                    return VoitureDTO.LocationIntern.builder()
                            .id_location(loc.getId_location())
                            .date_debut(loc.getDate_debut())
                            .date_fin(loc.getDate_fin())
                            .lieu_dep(loc.getLieu_dep())
                            .lieu_arr(loc.getLieu_arr())
                            .build();
                })
                .collect(Collectors.toList()));

        if( voiture.getListOptions() != null)
            builder.optionInterns(voiture.getListOptions().stream()
                    .map((op) -> {
                        return VoitureDTO.OptionIntern.builder()
                                .id_option(op.getId_option())
                                .nom(op.getNom())
                                .prix(op.getPrix())
                                .build();
                    })
                    .collect(Collectors.toList()));

        return builder.build();

    }

    @Override
    public Voiture formToEntity(VoitureForm form) {
        return Voiture.builder()
                .id_voiture(form.getId_voiture())
//                .modele_id(form.getModele_id())
                .carburant(form.getCarburant())
                .couleur(form.getCouleur())
                .etat(EtatVoiture.valueOf(form.getEtat()))
                .kilometre(form.getKilometre())
                .manuelle(form.isManuelle())
                .type(TypeVoiture.valueOf(form.getType()))
                .prix(form.getPrix())
                .modele(form.getModele())
                .listLocations(form.getLocationInterns())
                .listOptions(form.getOptionInterns())
                .build();
    }
}
