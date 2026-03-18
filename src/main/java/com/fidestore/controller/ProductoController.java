package com.fidestore.controller;

import com.fidestore.domain.Producto;
import com.fidestore.domain.Usuario;
import com.fidestore.service.CategoriaService;
import com.fidestore.service.ProductoService;
import com.fidestore.service.UsuarioService;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listado")
    public String listado(Model model, @RequestParam(required = false) String keyword) {
        List<Producto> productos = productoService.buscar(keyword);
        model.addAttribute("productos", productos);
        model.addAttribute("keyword", keyword);
        return "producto/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "producto/nuevo";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Producto producto = productoService.getProducto(id);
        if (producto == null) return "redirect:/producto/listado";
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "producto/nuevo";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto, Principal principal, RedirectAttributes redirectAttrs) {
        Optional<Usuario> vendedor = usuarioService.findByCorreo(principal.getName());
        vendedor.ifPresent(producto::setVendedor);
        productoService.save(producto);
        redirectAttrs.addFlashAttribute("exito", "Producto guardado correctamente.");
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        productoService.delete(id);
        redirectAttrs.addFlashAttribute("exito", "Producto eliminado.");
        return "redirect:/producto/listado";
    }
}
