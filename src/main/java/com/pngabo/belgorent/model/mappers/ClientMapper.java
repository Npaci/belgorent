package com.pngabo.belgorent.model.mappers;

import com.pngabo.belgorent.model.dtos.ClientDTO;
import com.pngabo.belgorent.model.entities.Client;
import com.pngabo.belgorent.model.forms.ClientForm;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements Mapper<Client, ClientDTO, ClientForm> {
    @Override
    public ClientDTO entityToDTO(Client entity) {
        return ClientDTO.builder()
                .id_client(entity.getId_client())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .date_naiss(entity.getDate_naiss())
                .build();
    }

    @Override
    public Client formToEntity(ClientForm form) {
        return Client.builder()
                .id_client(form.getId_client())
                .nom(form.getNom())
                .prenom(form.getPrenom())
                .date_naiss(form.getDate_naiss())
                .build();
    }
}
