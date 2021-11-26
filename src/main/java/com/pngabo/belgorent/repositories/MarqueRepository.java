package com.pngabo.belgorent.repositories;

import com.pngabo.belgorent.model.entities.Marque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarqueRepository extends JpaRepository<Marque, Long> {
}