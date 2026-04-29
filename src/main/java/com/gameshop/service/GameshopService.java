package com.gameshop.service;

import com.gameshop.domain.Gameshop;
import com.gameshop.repository.GameshopRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameshopService {

    @Autowired
    private GameshopRepository gameshopRepository; // Aquí se llama gameshopRepository

    @Transactional
    public void save(Gameshop gameshop) {
        // CAMBIO: Usar gameshopRepository en lugar de gameshopDao
        gameshopRepository.save(gameshop); 
    }
}