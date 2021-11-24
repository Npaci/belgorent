package com.pngabo.belgorent.model.entities;

import com.pngabo.belgorent.model.Carburant;
import com.pngabo.belgorent.model.EtatVoiture;
import com.pngabo.belgorent.model.TypeVoiture;
import lombok.*;

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

    @OneToMany(mappedBy = "voiture")
    private List<Location> listLocations;

    @ManyToOne
    @JoinColumn(name = "modele_id")
    private Modele modele;

    @ManyToMany
    @JoinTable(name = "Voiture_Option",
            joinColumns = @JoinColumn(name = "voiture_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> listOptions;
}
