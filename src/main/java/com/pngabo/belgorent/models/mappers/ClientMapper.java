package com.pngabo.belgorent.models.mappers;

import com.pngabo.belgorent.exceptions.ElementNotFoundException;
import com.pngabo.belgorent.models.dtos.ClientDTO;
import com.pngabo.belgorent.models.entities.Client;
import com.pngabo.belgorent.models.forms.ClientForm;
import com.pngabo.belgorent.repositories.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper implements Mapper<Client, ClientDTO, ClientForm> {
    private final RoleRepository repRole;

    public ClientMapper(RoleRepository repRole) {
        this.repRole = repRole;
    }

    @Override
    public ClientDTO entityToDTO(Client entity) {
        ClientDTO.ClientDTOBuilder builder = ClientDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .username(entity.getUsername())
                .date_naiss(entity.getDate_naiss());

        if (entity.getRoles() != null)
            builder.roles(entity.getRoles().stream()
                    .map((elm) -> elm.getNom())
                    .collect(Collectors.toList()));

        if (entity.getListLocations() != null)
            builder.locationInterns(entity.getListLocations().stream()
                    .map((elm) -> {
                        return ClientDTO.LocationIntern.builder()
                                .id_location(elm.getId_location())
                                .date_debut(elm.getDate_debut())
                                .date_fin(elm.getDate_fin())
                                .lieu_dep(elm.getLieu_dep())
                                .lieu_arr(elm.getLieu_arr())
                                .etat(elm.getEtat())
                                .build();
                    })
                    .collect(Collectors.toList()));

        return builder.build();
    }

    @Override
    public Client formToEntity(ClientForm form) {
        Client.ClientBuilder builder = Client.builder()
                .id(form.getId())
                .nom(form.getNom())
                .prenom(form.getPrenom())
                .username(form.getUsername())
                .password(form.getPassword())
                .date_naiss(form.getDate_naiss())
                .roles(List.of(repRole.findById(2L).orElseThrow(ElementNotFoundException::new)))
                .accountNonLocked(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .enabled(true);

        if (form.getLocations() != null)
            builder.listLocations(form.getLocations());

        return builder.build();
    }
}
