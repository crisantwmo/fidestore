package com.gameshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author keny
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        // Por ahora, como no hay BD de usuarios, pasamos un mensaje de prueba
        model.addAttribute("totalUsuarios", 3); // Kendall, Brandon y Deyrron
        return "admin/usuarios"; 
    }
}