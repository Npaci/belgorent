package com.pngabo.belgorent.models.forms;

import com.pngabo.belgorent.models.entities.Location;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Validated
public class ClientForm {
    @NotNull
    private long id_client;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotNull
    private Date date_naiss;
    private List<Location> locations;
}
