package com.fidestore.controller;

import com.fidestore.domain.Usuario;
import com.fidestore.service.UsuarioService;
import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/perfil")
    public String verPerfil(Model model, Principal principal) {
        String username = principal.getName();
        Optional<Usuario> opt = usuarioService.findByCorreo(username);

        if (opt.isEmpty()) {
            Usuario temporal = new Usuario();
            temporal.setNombre(username);
            temporal.setCorreo(username);
            temporal.setRol("N/A");
            model.addAttribute("usuario", temporal);
            model.addAttribute("soloLectura", true);
        } else {
            model.addAttribute("usuario", opt.get());
            model.addAttribute("soloLectura", false);
        }
        return "perfil";
    }

    @PostMapping("/perfil")
    public String actualizarPerfil(@RequestParam Long idUsuario,
                                   @RequestParam String nombre,
                                   @RequestParam(required = false) String nuevaContrasena,
                                   Principal principal,
                                   RedirectAttributes redirectAttrs) {

        Optional<Usuario> opt = usuarioService.findByCorreo(principal.getName());
        if (opt.isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "No se puede editar este perfil.");
            return "redirect:/perfil";
        }

        Usuario usuario = opt.get();
        if (!usuario.getIdUsuario().equals(idUsuario)) {
            return "redirect:/acceso_denegado";
        }

        usuario.setNombre(nombre);
        if (nuevaContrasena != null && !nuevaContrasena.isBlank()) {
            usuario.setContrasena(passwordEncoder.encode(nuevaContrasena));
        }

        usuarioService.save(usuario);
        redirectAttrs.addFlashAttribute("exito", "Perfil actualizado correctamente.");
        return "redirect:/perfil";
    }
}
