package com.pngabo.belgorent.repositories;

import com.pngabo.belgorent.models.entities.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarqueRepository extends JpaRepository<Marque, Long> {
    @Query(value = "select distinct (m.id_marque) , m.nom " +
            "from marque m join modele m2 " +
            "on m.id_marque = m2.marque_id join voiture v " +
            "on m2.id_modele = v.modele_id", nativeQuery = true)
    List<Marque> findAllUsed();
}