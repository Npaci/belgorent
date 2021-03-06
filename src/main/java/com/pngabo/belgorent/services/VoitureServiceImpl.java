package com.pngabo.belgorent.services;

import com.pngabo.belgorent.exceptions.*;
import com.pngabo.belgorent.models.EtatVoiture;
import com.pngabo.belgorent.models.dtos.VoitureDTO;
import com.pngabo.belgorent.models.entities.Voiture;
import com.pngabo.belgorent.models.forms.FilterForm;
import com.pngabo.belgorent.models.forms.VoitureForm;
import com.pngabo.belgorent.models.mappers.VoitureMapper;
import com.pngabo.belgorent.repositories.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoitureServiceImpl implements VoitureService {
    private final long BLOB_SIZE = 16777215L;
    private final VoitureRepository repository;
    private final VoitureMapper mapper;

    public VoitureServiceImpl(VoitureRepository repository, VoitureMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<VoitureDTO> getAllByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    public List<VoitureDTO> getFiltered(FilterForm form) {
        String marque = form.getMarque();
        String modele = form.getModele();
        String couleur = form.getCouleur();
        String etat = form.getEtat();
        String carburant = form.getCarburant();
        String type = form.getTypev();

        return repository.findOnCriterias(marque.equals("-") || marque.equals("") || marque.equals(" ") ? "%" : marque,
                        modele.equals("-") || modele.equals("") || modele.equals(" ") ? "%" : modele,
                        etat.equals("-") || etat.equals("") || etat.equals(" ") ? "%" : etat,
                        carburant.equals("-") || carburant.equals("") || carburant.equals(" ") ? "%" : carburant,
                        form.isManuelle(),
                        type.equals("-") || type.equals("") || type.equals(" ") ? "%" : type,
                        couleur.equals("-") || couleur.equals("") || couleur.equals(" ") ? "%" : couleur)
                .stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    public List<String> getAllColors() {
        return repository.findAvailableColors();
    }

    public List<String> getAllTypes() {
        return repository.findAvailableTypes();
    }

    public List<String> getAllFuels() {
        return repository.findAvailableFuels();
    }

    public VoitureDTO changeStatus(long id, EtatVoiture status) {
        if (!repository.existsById(id))
            throw new ElementNotFoundException();

        Voiture found = repository.findById(id)
                .orElseThrow(ElementNotFoundException::new);
        found.setEtat(status);

        return mapper.entityToDTO(repository.save(found));
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
    public VoitureDTO insert(VoitureForm form) throws ImageTooLargeException {
        if (repository.existsById(form.getId_voiture()))
            throw new ElementAlreadyExistException();

        if (form.getImage().getBytes(StandardCharsets.UTF_8).length > BLOB_SIZE)
            throw new ImageTooLargeException();

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

//    public VoitureDTO changeStatus(VoitureForm form) {
//        if (!repository.existsById(form.getId_voiture()))
//            throw new ElementNotFoundException();
//
//        Voiture toUpdate = repository.findById(form.getId_voiture())
//                .orElseThrow(ElementNotFoundException::new);
//
//        Voiture fromUser = mapper.formToEntity(form);
//        toUpdate.setEtat(fromUser.getEtat());
//
//        return mapper.entityToDTO(repository.save(toUpdate));
//    }

}
