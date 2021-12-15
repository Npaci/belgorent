package com.pngabo.belgorent.services;

import com.pngabo.belgorent.exceptions.ElementAlreadyExistException;
import com.pngabo.belgorent.exceptions.ElementNotFoundException;
import com.pngabo.belgorent.models.dtos.AdminDTO;
import com.pngabo.belgorent.models.entities.Admin;
import com.pngabo.belgorent.models.forms.AdminForm;
import com.pngabo.belgorent.models.mappers.AdminMapper;
import com.pngabo.belgorent.repositories.AdminRepository;
import com.pngabo.belgorent.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository repository;
    private final AdminMapper mapper;
    private final RoleRepository rRep;

    public AdminServiceImpl(AdminRepository repository, AdminMapper mapper, RoleRepository rRep) {
        this.repository = repository;
        this.mapper = mapper;
        this.rRep = rRep;
    }

    @Override
    public List<AdminDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDTO getOne(Long aLong) {
        if (aLong == null)
            return null;

        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Admin found = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        return mapper.entityToDTO(found);
    }

    @Override
    public AdminDTO insert(AdminForm form) {
        if (form == null)
            return null;

        if (repository.existsById(form.getId()))
            throw new ElementAlreadyExistException();

        Admin toInsert = mapper.formToEntity(form);
        toInsert.setRoles(rRep.findAllById(List.of(1L,2L)));

        return mapper.entityToDTO(repository.save(toInsert));
    }

    @Override
    public AdminDTO delete(Long aLong) {
        if (aLong == null)
            return null;

        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Admin todelete = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        repository.delete(todelete);

        return mapper.entityToDTO(todelete);
    }

    @Override
    public AdminDTO update(AdminForm form) {
        if (form == null)
            return null;

        if (!repository.existsById(form.getId()))
            throw new ElementNotFoundException();

        Admin toUpdate = mapper.formToEntity(form);

        return mapper.entityToDTO(repository.save(toUpdate));
    }
}
