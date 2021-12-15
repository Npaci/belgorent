package com.pngabo.belgorent.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Admin extends Utilisateur{
    //private String functions;
}
