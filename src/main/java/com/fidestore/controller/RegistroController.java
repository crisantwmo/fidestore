package com.fidestore.controller;

import com.fidestore.domain.Usuario;
import com.fidestore.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

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
