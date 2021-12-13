package com.pngabo.belgorent.controllers;

import com.pngabo.belgorent.models.dtos.MarqueDTO;
import com.pngabo.belgorent.models.forms.MarqueForm;
import com.pngabo.belgorent.services.MarqueServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/marque")
public class MarqueController {
    private final MarqueServiceImpl service;

    public MarqueController(MarqueServiceImpl service) {
        this.service = service;
    }

    @GetMapping(path = {"", "/", "/all"})
    public List<MarqueDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MarqueDTO getOneParam(@PathVariable Long id) {
        return service.getOne(id);
    }

    @GetMapping(params = "id")
    public MarqueDTO getOne(@RequestParam Long id) {
        return service.getOne(id);
    }

    @GetMapping(path = "/usedbrands")
    public List<MarqueDTO> getAllUsed() {
        return service.getAllUsedBrands();
    }


    @PostMapping(path = {"", "/", "/add"})
    public MarqueDTO insert(@Valid @RequestBody MarqueForm form) {
        return service.insert(form);
    }

    @PatchMapping(path = {"", "/", "/update"})
    public MarqueDTO update(@Valid @RequestBody MarqueForm form) {
        return service.update(form);
    }

    @DeleteMapping("/{id}")
    public MarqueDTO delete(@PathVariable Long id, Authentication auth) {
        //return service.deleteSecure(id, auth);
        return service.delete(id);
    }
}

