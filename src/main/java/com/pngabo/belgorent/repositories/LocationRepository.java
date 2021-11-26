package com.pngabo.belgorent.repositories;

import com.pngabo.belgorent.model.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}