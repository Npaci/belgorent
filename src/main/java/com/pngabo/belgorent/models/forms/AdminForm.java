package com.pngabo.belgorent.models.forms;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Validated
public class AdminForm {
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
}
