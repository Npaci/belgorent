package com.pngabo.belgorent.controllers;

import com.pngabo.belgorent.model.dtos.VoitureDTO;
import com.pngabo.belgorent.model.forms.VoitureForm;
import com.pngabo.belgorent.services.VoitureServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/voiture")
public class VoitureController {
    private final VoitureServiceImpl service;

    public VoitureController(VoitureServiceImpl service) {
        this.service = service;
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
