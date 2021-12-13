package com.pngabo.belgorent.controllers;

import com.pngabo.belgorent.exceptions.InvalidDateException;
import com.pngabo.belgorent.models.dtos.ClientDTO;
import com.pngabo.belgorent.models.entities.Utilisateur;
import com.pngabo.belgorent.models.forms.ClientForm;
import com.pngabo.belgorent.services.ClientServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.YEARS;

@RestController
@RequestMapping("/user")
public class ClientController {
    private final ClientServiceImpl service;
    private final PasswordEncoder encoder;

    public ClientController(ClientServiceImpl service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @GetMapping(path = {"", "/", "/all"})
    public List<ClientDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ClientDTO getOneParam(@PathVariable Long id) {
        return service.getOne(id);
    }

    @GetMapping(params = "id")
    public ClientDTO getOne(@RequestParam Long id) {
        return service.getOne(id);
    }

    @PostMapping(path = {"", "/", "/add"})
    public ClientDTO insert(@Valid @RequestBody ClientForm form) {

        form.setPassword(encoder.encode(form.getPassword()));
        //System.out.println("FORM: "+form);
        return service.insert(form);
    }

    @PatchMapping(path = {"", "/", "/update"})
    public ClientDTO update(@Valid @RequestBody ClientForm form) {
        //form.setPassword(encoder.encode(form.getPassword()));
        return service.update(form);
    }

    @DeleteMapping("/{id}")
    public ClientDTO delete(@PathVariable Long id, Authentication auth) {
        //return service.deleteSecure(id, auth);
        return service.delete(id);
    }


}
