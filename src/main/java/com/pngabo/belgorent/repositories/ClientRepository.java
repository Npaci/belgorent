package com.pngabo.belgorent.repositories;

import com.pngabo.belgorent.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}