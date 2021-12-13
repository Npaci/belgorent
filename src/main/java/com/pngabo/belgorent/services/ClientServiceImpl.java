package com.pngabo.belgorent.services;

import com.pngabo.belgorent.exceptions.ElementAlreadyExistException;
import com.pngabo.belgorent.exceptions.ElementNotFoundException;
import com.pngabo.belgorent.exceptions.InvalidDateException;
import com.pngabo.belgorent.models.dtos.ClientDTO;
import com.pngabo.belgorent.models.entities.Client;
import com.pngabo.belgorent.models.entities.Utilisateur;
import com.pngabo.belgorent.models.forms.ClientForm;
import com.pngabo.belgorent.models.mappers.ClientMapper;
import com.pngabo.belgorent.repositories.ClientRepository;
import com.pngabo.belgorent.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{
    private final ClientRepository repository;
    private final ClientMapper mapper;
    private final RoleRepository repRole;

    public ClientServiceImpl(ClientRepository repository, ClientMapper mapper, RoleRepository repRole) {
        this.repository = repository;
        this.mapper = mapper;
        this.repRole = repRole;
    }

    @Override
    public List<ClientDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getOne(Long aLong) {
        if (aLong == null)
            return null;

        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Client found = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        return mapper.entityToDTO(found);
    }

    @Override
    public ClientDTO insert(ClientForm form) {
        if (form == null)
            return null;

        if (!form.getDate_naiss().isBefore(LocalDate.now()) || Utilisateur.calculAge(form.getDate_naiss()) < 18)
            throw new InvalidDateException("L'utilisateur doit être majeur");

        if (repository.existsById(form.getId()))
            throw new ElementAlreadyExistException();

        Client toInsert = mapper.formToEntity(form);
        toInsert.setRoles(List.of(repRole.findById(2L).orElseThrow(ElementNotFoundException::new)));

        return mapper.entityToDTO(repository.save(toInsert));
    }

    @Override
    public ClientDTO delete(Long aLong) {
        if (aLong == null)
            return null;

        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Client todelete = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        repository.delete(todelete);

        return mapper.entityToDTO(todelete);
    }

    @Override
    public ClientDTO update(ClientForm form) {
        if (form == null)
            return null;

        if (!repository.existsById(form.getId()))
            throw new ElementNotFoundException();

        Client toUpdate = mapper.formToEntity(form);

        return mapper.entityToDTO(repository.save(toUpdate));
    }
}
