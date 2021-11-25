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
public class Option_v {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_option;
    private String nom;
    private double prix;

    @ManyToMany(mappedBy = "listOptions")
    private List<Voiture> listVoitures;
}
