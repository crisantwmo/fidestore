package com.gameshop.controller;

import com.gameshop.domain.Gameshop;
import com.gameshop.repository.GameshopRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gameshop")
public class GameshopController {

    @Autowired
    private GameshopRepository gameshopRepository;

    // 1. LISTAR: Muestra todos los juegos (Requisito Práctica #1)
    @GetMapping("/listado")
    public String listado(Model model) {
        List<Gameshop> lista = gameshopRepository.findAll();
        
        // Enviamos la lista y el conteo al HTML
        model.addAttribute("juegos", lista);
        model.addAttribute("totalJuegos", (lista != null) ? lista.size() : 0);
        return "/gameshop/listado";
    }

    // 2. ELIMINAR: Borra un juego por su ID (Requisito Práctica #1)
    @GetMapping("/eliminar/{idVideojuego}")
    public String eliminar(@PathVariable("idVideojuego") Long idVideojuego) {
        gameshopRepository.deleteById(idVideojuego);
        return "redirect:/gameshop/listado";
    }

    // 3. GUARDAR: Recibe los datos del formulario (Requisito Práctica #1)
    @PostMapping("/guardar")
    public String guardar(Gameshop gameshop) {
        gameshopRepository.save(gameshop);
        return "redirect:/gameshop/listado";
    }
    
    // 4. NUEVO: Abre el formulario para incluir un juego
    @GetMapping("/nuevo")
    public String nuevo(Gameshop gameshop) {
        return "/gameshop/modificar";
    }
}