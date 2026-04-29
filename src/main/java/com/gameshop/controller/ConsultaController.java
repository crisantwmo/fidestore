package com.gameshop.controller;

import com.gameshop.repository.GameshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private GameshopRepository gameshopRepository;

    // 1. Muestra la página de consulta vacía
    @GetMapping("/listado")
    public String listado(Model model) {
        return "consulta/listado";
    }

    // 2. Realiza la búsqueda por rango de precio
    @PostMapping("/query")
    public String query(@RequestParam(value = "precioInf") double precioInf,
                        @RequestParam(value = "precioSup") double precioSup, 
                        Model model) {
        
        var lista = gameshopRepository.findByPrecioBetweenOrderByTitulo(precioInf, precioSup);
        
        model.addAttribute("juegos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        
        return "consulta/listado";
    }
}