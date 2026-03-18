package com.fidestore.repository;

import com.fidestore.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :keyword, '%')) AND p.activo = true")
    List<Producto> buscarPorNombre(@Param("keyword") String keyword);

    List<Producto> findByActivoTrue();
}
