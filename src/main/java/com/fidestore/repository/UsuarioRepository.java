package com.fidestore.repository;

import com.fidestore.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Agregar métodos personalizados luego, como buscar por correo
}