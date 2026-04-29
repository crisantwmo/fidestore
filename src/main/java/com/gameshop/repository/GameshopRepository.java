package com.gameshop.repository;

import com.gameshop.domain.Gameshop;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameshopRepository extends JpaRepository<Gameshop, Long> {
    
    public List<Gameshop> findByPrecioBetweenOrderByTitulo(double precioInf, double precioSup);
    
}