package com.pngabo.belgorent.controllers;

import com.pngabo.belgorent.models.EtatVoiture;
import com.pngabo.belgorent.models.dtos.VoitureDTO;
import com.pngabo.belgorent.models.forms.FilterForm;
import com.pngabo.belgorent.models.forms.VoitureForm;
import com.pngabo.belgorent.services.LocationServiceImpl;
import com.pngabo.belgorent.services.VoitureServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/voiture")
public class VoitureController {
    private final VoitureServiceImpl service;
    private final LocationServiceImpl lServ;

    public VoitureController(VoitureServiceImpl service, LocationServiceImpl lServ) {
        this.service = service;
        this.lServ = lServ;
    }

    @GetMapping(path = "/ready")
    public List<VoitureDTO> getAllReady() {
        lServ.updateCurrentRentals(); // Mettre à jour les location avant de retourner les voitures
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
        lServ.updateCurrentRentals(); // Mettre à jour les location avant de retourner les voiture
        return service.getAll();
    }

    @GetMapping("/{id}")
    public VoitureDTO getOneParam(@PathVariable Long id) {
        lServ.updateCurrentRentals(); // Mettre à jour les location avant de retourner les voiture
        return service.getOne(id);
    }

    @GetMapping(params = "id")
    public VoitureDTO getOne(@RequestParam Long id) {
        lServ.updateCurrentRentals(); // Mettre à jour les location avant de retourner les voiture
        return service.getOne(id);
    }

    @PostMapping(path = {"", "/", "/add"})
    public VoitureDTO insert(@Valid @RequestBody VoitureForm form) {
        //form.setPassword(encoder.encode(form.getPassword()));
        lServ.updateCurrentRentals(); // Mettre à jour les location avant de retourner les voiture
        return service.insert(form);
    }

    @PostMapping(path = "/filter")
    public List<VoitureDTO> getFiltered(@RequestBody FilterForm form) {
        System.out.println(">>>>>>>>>>> filter: "+form.toString());
        lServ.updateCurrentRentals(); // Mettre à jour les location avant de retourner les voiture
        return service.getFiltered(form);
    }

    @PatchMapping(path = {"", "/", "/update"})
    public VoitureDTO update(@Valid @RequestBody VoitureForm form) {
        //form.setPassword(encoder.encode(form.getPassword()));
        lServ.updateCurrentRentals(); // Mettre à jour les location avant de retourner les voiture
        return service.update(form);
    }

    @PatchMapping(path = "/changestatus")
    public VoitureDTO changeStatus(@Valid @RequestBody VoitureForm form) {
        lServ.updateCurrentRentals(); // Mettre à jour les location avant de retourner les voiture
        return service.changeStatus(form.getId_voiture(), getStatus(form.getEtat()));
    }

    private EtatVoiture getStatus(String value) {
        if (value == null || value.equals(""))
            return null;

        switch (value) {
            case "PRET":
                return EtatVoiture.PRET;
            case "LOUE":
                return EtatVoiture.LOUE;
            case "REPARATION":
                return EtatVoiture.REPARATION;
            case "PREPARATION":
                return EtatVoiture.PREPARATION;
            default:
                return null;
        }
    }

    @DeleteMapping("/{id}")
    public VoitureDTO delete(@PathVariable Long id, Authentication auth) {
        //return service.deleteSecure(id, auth);
        return service.delete(id);
    }
}
