package com.pngabo.belgorent.controllers;

import com.pngabo.belgorent.model.dtos.LocationDTO;
import com.pngabo.belgorent.model.forms.LocationForm;
import com.pngabo.belgorent.services.LocationServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {
    private final LocationServiceImpl service;

    public LocationController(LocationServiceImpl service) {
        this.service = service;
    }

    @GetMapping(path = {"", "/", "/all"})
    public List<LocationDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public LocationDTO getOneParam(@PathVariable Long id) {
        return service.getOne(id);
    }

    @GetMapping(params = "id")
    public LocationDTO getOne(@RequestParam Long id) {
        return service.getOne(id);
    }

    @PostMapping(path = {"", "/", "/add"})
    public LocationDTO insert(@Valid @RequestBody LocationForm form) {
        //form.setPassword(encoder.encode(form.getPassword()));
        return service.insert(form);
    }

    @PatchMapping(path = {"", "/", "/update"})
    public LocationDTO update(@Valid @RequestBody LocationForm form) {
        //form.setPassword(encoder.encode(form.getPassword()));
        return service.update(form);
    }

    @DeleteMapping("/{id}")
    public LocationDTO delete(@PathVariable Long id, Authentication auth) {
        //return service.deleteSecure(id, auth);
        return service.delete(id);
    }
}
