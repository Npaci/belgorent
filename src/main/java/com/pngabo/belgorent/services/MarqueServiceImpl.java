package com.pngabo.belgorent.services;

import com.pngabo.belgorent.exceptions.ElementAlreadyExistException;
import com.pngabo.belgorent.exceptions.ElementNotFoundException;
import com.pngabo.belgorent.models.dtos.MarqueDTO;
import com.pngabo.belgorent.models.entities.Marque;
import com.pngabo.belgorent.models.forms.MarqueForm;
import com.pngabo.belgorent.models.mappers.MarqueMapper;
import com.pngabo.belgorent.repositories.MarqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarqueServiceImpl implements MarqueService {
    private final MarqueRepository repository;
    private final MarqueMapper mapper;

    public MarqueServiceImpl(MarqueRepository repository, MarqueMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<MarqueDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MarqueDTO getOne(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Marque found = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        return mapper.entityToDTO(found);
    }

    @Override
    public MarqueDTO insert(MarqueForm form) {
        if (repository.existsById(form.getId_marque()))
            throw new ElementAlreadyExistException();

        Marque toInsert = mapper.formToEntity(form);

        return mapper.entityToDTO(repository.save(toInsert));
    }

    @Override
    public MarqueDTO delete(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Marque todelete = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        repository.delete(todelete);

        return mapper.entityToDTO(todelete);
    }

    @Override
    public MarqueDTO update(MarqueForm form) {
        if (!repository.existsById(form.getId_marque()))
            throw new ElementNotFoundException();

        Marque toUpdate = mapper.formToEntity(form);

        return mapper.entityToDTO(repository.save(toUpdate));
    }
}
