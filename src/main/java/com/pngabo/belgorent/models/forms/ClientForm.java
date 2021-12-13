package com.pngabo.belgorent.models.forms;

import com.pngabo.belgorent.models.entities.Location;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Validated
public class ClientForm {
    @NotNull
    private long id;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotNull
    @PastOrPresent
    private LocalDate date_naiss;
    private List<Location> locations;
}
