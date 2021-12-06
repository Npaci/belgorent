package com.pngabo.belgorent.repositories;

import com.pngabo.belgorent.models.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}