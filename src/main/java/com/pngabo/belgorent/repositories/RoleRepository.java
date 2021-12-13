package com.pngabo.belgorent.repositories;

import com.pngabo.belgorent.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}