package com.pngabo.belgorent.controllers;

import com.pngabo.belgorent.models.EtatVoiture;
import com.pngabo.belgorent.models.dtos.VoitureDTO;
import com.pngabo.belgorent.models.forms.FilterForm;
import com.pngabo.belgorent.models.forms.VoitureForm;
import com.pngabo.belgorent.services.VoitureServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/voiture")
public class VoitureController {
    private final VoitureServiceImpl service;

    public VoitureController(VoitureServiceImpl service) {
        this.service = service;
    }

    @GetMapping(path = "/ready")
    public List<VoitureDTO> getAllReady() {
        return service.getAllByStatus(EtatVoiture.PRET.name());
    }

    @GetMapping(path = "/colors")
    public List<String> getAllColors() {
        return service.getAllColors();
    }

    @GetMapping(path = "/types")
    public List<String> getAllTypes() {
        return service.getAllTypes();
    }

    @GetMapping(path = "/fuels")
    public List<String> getAllFuels() {
        return service.getAllFuels();
    }

    @GetMapping(path = {"", "/", "/all"})
    public List<VoitureDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public VoitureDTO getOneParam(@PathVariable Long id) {
        return service.getOne(id);
    }

    @GetMapping(params = "id")
    public VoitureDTO getOne(@RequestParam Long id) {
        return service.getOne(id);
    }

    @PostMapping(path = {"", "/", "/add"})
    public VoitureDTO insert(@Valid @RequestBody VoitureForm form) {
        //form.setPassword(encoder.encode(form.getPassword()));
        return service.insert(form);
    }

    @PostMapping(path = "/filter")
    public List<VoitureDTO> getFiltered(@RequestBody FilterForm form) {
        System.out.println(">>>>>>>>>>> filter: "+form.toString());
        return service.getFiltered(form);
    }

    @PatchMapping(path = {"", "/", "/update"})
    public VoitureDTO update(@Valid @RequestBody VoitureForm form) {
        //form.setPassword(encoder.encode(form.getPassword()));
        return service.update(form);
    }

    @DeleteMapping("/{id}")
    public VoitureDTO delete(@PathVariable Long id, Authentication auth) {
        //return service.deleteSecure(id, auth);
        return service.delete(id);
    }
}
