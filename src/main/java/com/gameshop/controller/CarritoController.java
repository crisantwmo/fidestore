package com.gameshop.controller;

import com.gameshop.domain.Gameshop;
import com.gameshop.repository.GameshopRepository;
import com.gameshop.service.GameshopService; // Asegúrate de que este import existe
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private GameshopRepository gameshopRepository;

    @Autowired
    private GameshopService gameshopService; 

    private List<Gameshop> items = new ArrayList<>();

    @PostMapping("/agregar")
    public String agregar(@RequestParam("idVideojuego") Long idVideojuego) {
        Optional<Gameshop> juegoOpt = gameshopRepository.findById(idVideojuego);
        if (juegoOpt.isPresent()) {
            items.add(juegoOpt.get());
        }
        return "redirect:/gameshop/listado";
    }

    @GetMapping("/ver")
    public String verCarrito(Model model) {
        double total = items.stream().mapToDouble(Gameshop::getPrecio).sum();
        model.addAttribute("itemsCarrito", items);
        model.addAttribute("total", total);
        return "carrito/listado";
    }

    @PostMapping("/facturar")
    public String facturar(Model model) {
        if (items.isEmpty()) {
            return "redirect:/carrito/ver";
        }
        double total = items.stream().mapToDouble(Gameshop::getPrecio).sum();
        model.addAttribute("fecha", new java.util.Date());
        model.addAttribute("items", new ArrayList<>(items));
        model.addAttribute("total", total);
        items.clear(); 
        return "carrito/factura"; 
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarItem(@PathVariable("id") Long id) {
        items.removeIf(i -> i.getIdVideojuego().equals(id));
        return "redirect:/carrito/ver";
    }
}
