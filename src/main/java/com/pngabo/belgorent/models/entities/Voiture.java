package com.pngabo.belgorent.models.entities;

import com.pngabo.belgorent.models.Carburant;
import com.pngabo.belgorent.models.EtatVoiture;
import com.pngabo.belgorent.models.TypeVoiture;
import lombok.*;
import org.hibernate.annotations.Fetch;
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
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_voiture;
    private double prix;
    private String couleur;
    @Enumerated(EnumType.STRING)
    private Carburant carburant;
    private double kilometre;
    private boolean manuelle;
    @Enumerated(EnumType.STRING)
    private TypeVoiture type;
    @Enumerated(EnumType.STRING)
    private EtatVoiture etat;

    @Lob
    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy = "voiture", fetch = FetchType.EAGER)
    private List<Location> listLocations;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name = "modele_id")
    private Modele modele;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "Voiture_Option",
            joinColumns = @JoinColumn(name = "voiture_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option_v> listOptions;
}
