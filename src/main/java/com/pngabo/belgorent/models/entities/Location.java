package com.pngabo.belgorent.models.entities;

import com.pngabo.belgorent.models.EtatLocation;
import com.pngabo.belgorent.models.TypeVoiture;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_location;
    private String lieu_dep;
    private String lieu_arr;
    private LocalDateTime date_debut;
    private LocalDateTime date_fin;
    @Enumerated(EnumType.STRING)
    private EtatLocation etat;

    @ManyToOne
    @JoinColumn(name = "voiture_id")
    private Voiture voiture;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
