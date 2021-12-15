package com.pngabo.belgorent.models.mappers;

import com.pngabo.belgorent.models.dtos.AdminDTO;
import com.pngabo.belgorent.models.entities.Admin;
import com.pngabo.belgorent.models.forms.AdminForm;
import com.pngabo.belgorent.repositories.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdminMapper implements Mapper<Admin, AdminDTO, AdminForm> {
    private final RoleRepository repRole;

    public AdminMapper(RoleRepository repRole) {
        this.repRole = repRole;
    }

    @Override
    public AdminDTO entityToDTO(Admin entity){
        AdminDTO.AdminDTOBuilder builder = AdminDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .username(entity.getUsername());

        if (entity.getRoles() != null)
            builder.roles(entity.getRoles().stream()
                    .map((elm) -> elm.getNom())
                    .collect(Collectors.toList()));

        return builder.build();
    }

    @Override
    public Admin formToEntity(AdminForm form) {
        Admin.AdminBuilder builder = Admin.builder()
                .id(form.getId())
                .nom(form.getNom())
                .prenom(form.getPrenom())
                .username(form.getUsername())
                .password(form.getPassword())
                .roles(repRole.findAllById(List.of(1L,2L)))
                .accountNonLocked(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .enabled(true);

        return builder.build();
    }
}
