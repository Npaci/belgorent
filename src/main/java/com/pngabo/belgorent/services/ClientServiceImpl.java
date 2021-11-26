package com.pngabo.belgorent.services;

import com.pngabo.belgorent.exceptions.ElementAlreadyExistException;
import com.pngabo.belgorent.exceptions.ElementNotFoundException;
import com.pngabo.belgorent.model.dtos.ClientDTO;
import com.pngabo.belgorent.model.entities.Client;
import com.pngabo.belgorent.model.entities.Voiture;
import com.pngabo.belgorent.model.forms.ClientForm;
import com.pngabo.belgorent.model.mappers.ClientMapper;
import com.pngabo.belgorent.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{
    private final ClientRepository repository;
    private final ClientMapper mapper;

    public ClientServiceImpl(ClientRepository repository, ClientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ClientDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getOne(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Client found = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        return mapper.entityToDTO(found);
    }

    @Override
    public ClientDTO insert(ClientForm form) {
        if (repository.existsById(form.getId_client()))
            throw new ElementAlreadyExistException();

        Client toInsert = mapper.formToEntity(form);

        return mapper.entityToDTO(repository.save(toInsert));
    }

    @Override
    public ClientDTO delete(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Client todelete = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        repository.delete(todelete);

        return mapper.entityToDTO(todelete);
    }

    @Override
    public ClientDTO update(ClientForm form) {
        if (!repository.existsById(form.getId_client()))
            throw new ElementNotFoundException();

        Client toUpdate = mapper.formToEntity(form);

        return mapper.entityToDTO(repository.save(toUpdate));
    }
}
