package com.pngabo.belgorent.models.forms;

import com.pngabo.belgorent.models.entities.Client;
import com.pngabo.belgorent.models.entities.Voiture;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@Validated
public class LocationForm {
    @NotNull
    private long id_location;
    @NotBlank
    private String lieu_dep;
    @NotBlank
    private String lieu_arr;
    @NotNull
    private Date date_debut;
    @NotNull
    private Date date_fin;
    @NotNull
    private Client client;
    @NotNull
    private Voiture voiture;
}
