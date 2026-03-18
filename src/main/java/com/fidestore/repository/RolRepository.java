package com.fidestore.repository;

import com.fidestore.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    // Aquí no hace falta escribir nada más, JpaRepository ya trae 
    // los métodos para guardar, borrar y buscar por defecto.
}