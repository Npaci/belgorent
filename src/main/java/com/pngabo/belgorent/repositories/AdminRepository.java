package com.pngabo.belgorent.repositories;

import com.pngabo.belgorent.models.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}