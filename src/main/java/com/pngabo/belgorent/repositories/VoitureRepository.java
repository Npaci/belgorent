package com.pngabo.belgorent.repositories;

import com.pngabo.belgorent.model.EtatVoiture;
import com.pngabo.belgorent.model.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {
    @Query(value = "SELECT * FROM Voiture WHERE etat like ?1", nativeQuery = true)
    List<Voiture> findByStatus(String status);
}
