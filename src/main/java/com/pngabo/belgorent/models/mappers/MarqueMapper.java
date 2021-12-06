package com.pngabo.belgorent.models.mappers;

import com.pngabo.belgorent.models.dtos.MarqueDTO;
import com.pngabo.belgorent.models.entities.Marque;
import com.pngabo.belgorent.models.entities.Modele;
import com.pngabo.belgorent.models.forms.MarqueForm;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MarqueMapper implements Mapper<Marque, MarqueDTO, MarqueForm>{
    @Override
    public MarqueDTO entityToDTO(Marque marque) {
        return MarqueDTO.builder()
                .id_marque(marque.getId_marque())
                .nom(marque.getNom())
                .modeleInterns(marque.getListModeles().stream()
                        .map((elm)-> {
                            return MarqueDTO.ModeleIntern.builder()
                                    .id_modele(elm.getId_modele())
                                    .nom(elm.getNom())
                                    .build();
                        })
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Marque formToEntity(MarqueForm form) {
        return Marque.builder()
                .id_marque(form.getId_marque())
                .nom(form.getNom())
                .listModeles(form.getListModeles().stream()
                        .map((elm)-> {
                            return Modele.builder()
                                    .id_modele(elm.getId_modele())
                                    .nom(elm.getNom())
                                    .build();
                        })
                        .collect(Collectors.toList()))
                .build();
    }
}
