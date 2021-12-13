package com.pngabo.belgorent.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("CLIENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Client extends Utilisateur {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id_client;
//    private String nom;
//    private String prenom;
    private LocalDate date_naiss;

    @OneToMany(mappedBy = "client")
    private List<Location> listLocations;
}
