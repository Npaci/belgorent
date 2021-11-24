package com.pngabo.belgorent.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Modele {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_modele;
    private String nom;

    @OneToMany(mappedBy = "modele")
    private List<Voiture> listVoitures;

    @ManyToOne
    @JoinColumn(name = "marque_id")
    private Marque marque;
}
