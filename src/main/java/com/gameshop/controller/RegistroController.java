package com.gameshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    @GetMapping("/nuevo")
    public String formulario() {
        return "registro/formulario";
    }

    @PostMapping("/crear")
    public String crearCuenta() {
        // Aquí luego conectaremos con el Service para guardar en DB
        return "redirect:/login?success";
    }
}