package com.pngabo.belgorent.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_client;
    private String nom;
    private String prenom;
    private Date date_naiss;

    @OneToMany(mappedBy = "client")
    private List<Location> listLocations;
}
