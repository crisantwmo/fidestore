package com.fidestore.controller;

import com.fidestore.domain.Categoria;
import com.fidestore.domain.Producto;
import com.fidestore.service.CategoriaService;
import com.fidestore.service.ProductoService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String guardar(@RequestParam(required = false) Long idProducto,
                          @RequestParam String nombre,
                          @RequestParam String descripcion,
                          @RequestParam BigDecimal precio,
                          @RequestParam Integer stock,
                          @RequestParam(required = false) String urlImagen,
                          @RequestParam(required = false) Long categoriaId,
                          RedirectAttributes redirectAttrs) {

        Producto producto = idProducto != null
                ? productoService.getProducto(idProducto)
                : new Producto();

        if (producto == null) producto = new Producto();

        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setUrlImagen(urlImagen);
        producto.setActivo(true);

        if (categoriaId != null) {
            Categoria cat = categoriaService.getCategoria(categoriaId);
            producto.setCategoria(cat);
        } else {
            producto.setCategoria(null);
        }

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
