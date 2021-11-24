package com.pngabo.belgorent.services;

import com.pngabo.belgorent.exceptions.ElementAlreadyExistException;
import com.pngabo.belgorent.exceptions.ElementNotFoundException;
import com.pngabo.belgorent.model.dtos.VoitureDTO;
import com.pngabo.belgorent.model.entities.Voiture;
import com.pngabo.belgorent.model.forms.VoitureForm;
import com.pngabo.belgorent.model.mappers.VoitureMapper;
import com.pngabo.belgorent.repositories.VoitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoitureServiceImpl implements VoitureService {
    private final VoitureRepository repository;
    private final VoitureMapper mapper;

    public VoitureServiceImpl(VoitureRepository repository, VoitureMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<VoitureDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VoitureDTO getOne(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Voiture found = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        return mapper.entityToDTO(found);
    }

    @Override
    public VoitureDTO insert(VoitureForm form) {
        if (repository.existsById(form.getId_voiture()))
            throw new ElementAlreadyExistException();

        Voiture toInsert = mapper.formToEntity(form);

        return mapper.entityToDTO(repository.save(toInsert));
    }

    @Override
    public VoitureDTO delete(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Voiture todelete = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        repository.delete(todelete);

        return mapper.entityToDTO(todelete);
    }

    @Override
    public VoitureDTO update(VoitureForm form) {
        if (!repository.existsById(form.getId_voiture()))
            throw new ElementNotFoundException();

        Voiture toUpdate = mapper.formToEntity(form);

        return mapper.entityToDTO(repository.save(toUpdate));
    }
}
