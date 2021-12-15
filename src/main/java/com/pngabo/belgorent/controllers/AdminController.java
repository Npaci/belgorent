package com.pngabo.belgorent.controllers;

import com.pngabo.belgorent.models.dtos.AdminDTO;
import com.pngabo.belgorent.models.forms.AdminForm;
import com.pngabo.belgorent.services.AdminServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminServiceImpl service;
    private final PasswordEncoder encoder;

    public AdminController(AdminServiceImpl service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @GetMapping(path = {"", "/", "/all"})
    public List<AdminDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AdminDTO getOneParam(@PathVariable Long id) {
        return service.getOne(id);
    }

    @GetMapping(params = "id")
    public AdminDTO getOne(@RequestParam Long id) {
        return service.getOne(id);
    }

    @PostMapping(path = {"", "/", "/add"})
    public AdminDTO insert(@Valid @RequestBody AdminForm form) {

        form.setPassword(encoder.encode(form.getPassword()));
        //System.out.println("FORM: "+form);
        return service.insert(form);
    }

    @PatchMapping(path = {"", "/", "/update"})
    public AdminDTO update(@Valid @RequestBody AdminForm form) {
        //form.setPassword(encoder.encode(form.getPassword()));
        return service.update(form);
    }

    @DeleteMapping("/{id}")
    public AdminDTO delete(@PathVariable Long id, Authentication auth) {
        //return service.deleteSecure(id, auth);
        return service.delete(id);
    }


}
