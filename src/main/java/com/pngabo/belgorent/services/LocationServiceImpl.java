package com.pngabo.belgorent.services;

import com.pngabo.belgorent.exceptions.ElementAlreadyExistException;
import com.pngabo.belgorent.exceptions.ElementNotFoundException;
import com.pngabo.belgorent.exceptions.InvalidDateException;
import com.pngabo.belgorent.models.EtatLocation;
import com.pngabo.belgorent.models.EtatVoiture;
import com.pngabo.belgorent.models.dtos.LocationDTO;
import com.pngabo.belgorent.models.entities.Location;
import com.pngabo.belgorent.models.forms.LocationForm;
import com.pngabo.belgorent.models.mappers.LocationMapper;
import com.pngabo.belgorent.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService{
    private final LocationRepository lReposotory;
    private final LocationMapper mapper;
    private final VoitureServiceImpl vServ;

    public LocationServiceImpl(LocationRepository lReposotory, LocationMapper mapper, VoitureServiceImpl vServ) {
        this.lReposotory = lReposotory;
        this.mapper = mapper;
        this.vServ = vServ;
    }

    public void updateCurrentRentals() {
        List<Location> listLocFut = lReposotory.getAllFutureRentals();
        List<Location> listLocPres = lReposotory.getAllCurrentRentals();


        for (Location loc : listLocFut) {
            if (loc.getDate_debut().isBefore(LocalDateTime.now()) && loc.getDate_fin().isAfter(LocalDateTime.now())){
                changeStatus(loc.getId_location(), EtatLocation.PRESENT);
                vServ.changeStatus(loc.getVoiture().getId_voiture(), EtatVoiture.LOUE);
            } else if (loc.getDate_fin().isBefore(LocalDateTime.now())) {
                changeStatus(loc.getId_location(), EtatLocation.PAST);
                vServ.changeStatus(loc.getVoiture().getId_voiture(), EtatVoiture.PREPARATION);
            }
        }

        for (Location loc : listLocPres) {
            if (loc.getDate_fin().isBefore(LocalDateTime.now())){
                changeStatus(loc.getId_location(), EtatLocation.PAST);
                vServ.changeStatus(loc.getVoiture().getId_voiture(), EtatVoiture.PREPARATION);
            } else
                vServ.changeStatus(loc.getVoiture().getId_voiture(), EtatVoiture.LOUE);
        }

    }

    public LocationDTO changeStatus(long id, EtatLocation status) {
        if (!lReposotory.existsById(id))
            throw new ElementNotFoundException();

        Location found = lReposotory.findById(id)
                .orElseThrow(ElementNotFoundException::new);
        found.setEtat(status);

        return mapper.entityToDTO(lReposotory.save(found));
    }

    @Override
    public List<LocationDTO> getAll() {
        return lReposotory.findAll().stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LocationDTO getOne(Long aLong) {
        if (!lReposotory.existsById(aLong))
            throw new ElementNotFoundException();

        Location found = lReposotory.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        return mapper.entityToDTO(found);
    }

    @Override
    public LocationDTO insert(LocationForm form) {
        if (lReposotory.existsById(form.getId_location()))
            throw new ElementAlreadyExistException();

        if (!form.getDate_debut().isAfter(LocalDateTime.now()))
            throw new InvalidDateException("La date de d??but de location doit ??tre post??rieur ?? la date du jour");

        if (!form.getDate_fin().isAfter(form.getDate_debut()))
            throw new InvalidDateException("La date de fin de location doit ??tre post??rieur ?? la date de d??part");

        Location toInsert = mapper.formToEntity(form);
        Location inserted = lReposotory.save(toInsert);
//        vServ.changeStatus(toInsert.getVoiture().getId_voiture(), EtatVoiture.LOUE);

        return mapper.entityToDTO(inserted);
    }

    @Override
    public LocationDTO delete(Long aLong) {
        if (!lReposotory.existsById(aLong))
            throw new ElementNotFoundException();

        Location todelete = lReposotory.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        lReposotory.delete(todelete);

        return mapper.entityToDTO(todelete);
    }

    @Override
    public LocationDTO update(LocationForm form) {
        if (!lReposotory.existsById(form.getId_location()))
            throw new ElementNotFoundException();

        Location toUpdate = mapper.formToEntity(form);

        return mapper.entityToDTO(lReposotory.save(toUpdate));
    }
}
