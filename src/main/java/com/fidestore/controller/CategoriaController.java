package com.fidestore.controller;

import com.fidestore.domain.Categoria;
import com.fidestore.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "categoria/listado";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/nueva";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.getCategoria(id);
        if (categoria == null) return "redirect:/categoria/listado";
        model.addAttribute("categoria", categoria);
        return "categoria/nueva";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Categoria categoria, RedirectAttributes redirectAttrs) {
        categoriaService.save(categoria);
        redirectAttrs.addFlashAttribute("exito", "Categoría guardada correctamente.");
        return "redirect:/categoria/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        categoriaService.delete(id);
        redirectAttrs.addFlashAttribute("exito", "Categoría eliminada.");
        return "redirect:/categoria/listado";
    }
}
