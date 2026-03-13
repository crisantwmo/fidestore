package com.fidestore.controller;

import com.fidestore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public String inicio(Model model) {
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios"; 
    }
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
}