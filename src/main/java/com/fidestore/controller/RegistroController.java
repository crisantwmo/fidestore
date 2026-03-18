package com.fidestore.controller;

import com.fidestore.domain.Usuario;
import com.fidestore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
=======
import org.springframework.web.bind.annotation.PostMapping;
>>>>>>> 1cec6c828a55946f962a8aeb349aefdcf03a1ea2

@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

<<<<<<< HEAD
    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttrs) {
        if (usuarioService.existeCorreo(usuario.getCorreo())) {
            redirectAttrs.addFlashAttribute("error", "El correo ya está registrado.");
            return "redirect:/registro";
        }
        usuarioService.registrar(usuario);
        redirectAttrs.addFlashAttribute("exito", "Cuenta creada exitosamente. Ya puedes iniciar sesión.");
        return "redirect:/login";
    }
}
=======
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
>>>>>>> 1cec6c828a55946f962a8aeb349aefdcf03a1ea2
