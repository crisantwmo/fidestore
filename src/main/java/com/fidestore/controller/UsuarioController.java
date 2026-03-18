package com.fidestore.controller;

import com.fidestore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario") // Esto agrupa todo bajo /usuario
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

<<<<<<< HEAD
    @GetMapping("/usuario/listado")
    public String listado(Model model) {
        model.addAttribute("usuarios", usuarioService.getUsuarios());
        return "usuario/usuarios";
    }

    @GetMapping("/")
    public String index() {
        return "index";
=======
    // Ahora la ruta completa es /usuario/listado
    @GetMapping("/listado")
    public String listado(Model model) {
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        // Retorna el archivo que está en templates/usuario/listado.html
        return "/usuario/listado"; 
>>>>>>> 1cec6c828a55946f962a8aeb349aefdcf03a1ea2
    }
}
