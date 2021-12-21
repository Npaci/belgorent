package com.pngabo.belgorent.repositories;

import com.pngabo.belgorent.models.entities.Location;
import com.pngabo.belgorent.models.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query(value = "select * from location where etat like 'FUTURE'", nativeQuery = true)
    List<Location> getAllFutureRentals();

    @Query(value = "select * from location where etat like 'PRESENT'", nativeQuery = true)
    List<Location> getAllCurrentRentals();
}