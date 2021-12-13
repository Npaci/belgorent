package com.pngabo.belgorent.repositories;

import com.pngabo.belgorent.models.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {
    @Query(value = "SELECT * FROM Voiture WHERE etat like ?1", nativeQuery = true)
    List<Voiture> findByStatus(String status);

    @Query(value = "select * from voiture v join modele m " +
            "on v.modele_id = m.id_modele join marque m2 on m.marque_id = m2.id_marque " +
            "where m2.nom like ?1 " +
            "and m.nom like ?2 " +
            "and etat like ?3 " +
            "and carburant like ?4 " +
            "and manuelle = ?5 " +
            "and type like ?6 " +
            "and couleur like ?7", nativeQuery = true)
    List<Voiture> findOnCriterias(String marque, String modele, String etat, String carburant, boolean manuelle, String type, String couleur);

    @Query(value = "SELECT distinct v.couleur FROM Voiture v", nativeQuery = true)
    List<String> findAvailableColors();

    @Query(value = "SELECT distinct v.type FROM Voiture v", nativeQuery = true)
    List<String> findAvailableTypes();

    @Query(value = "SELECT distinct v.carburant FROM Voiture v", nativeQuery = true)
    List<String> findAvailableFuels();
}
