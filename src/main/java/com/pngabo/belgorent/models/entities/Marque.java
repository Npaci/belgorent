package com.pngabo.belgorent.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Marque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_marque;
    private String nom;

    @OneToMany(mappedBy = "marque")
    private List<Modele> listModeles;
}
