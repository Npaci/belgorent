package com.pngabo.belgorent.models.entities;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "Voiture_Option",
            joinColumns = @JoinColumn(name = "option_id"),
            inverseJoinColumns = @JoinColumn(name = "voiture_id")
    )
    private List<Voiture> listVoitures;
}
