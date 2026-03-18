package com.fidestore.controller;

import com.fidestore.domain.Usuario;
import com.fidestore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro/nuevo";
    }

    // ¡ESTO ES LO QUE FALTA! El método que recibe los datos
    @PostMapping("/registro/crear")
    public String crear(Usuario usuario) {
        // Aquí podrías añadir lógica para encriptar la contraseña luego
        usuarioService.save(usuario, true); 
        return "redirect:/login"; // Si todo sale bien, lo manda a loguearse
    }
}