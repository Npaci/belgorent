package com.pngabo.belgorent.repositories;

import com.pngabo.belgorent.model.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {
}
