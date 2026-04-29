package com.gameshop.controller;

import com.gameshop.domain.Categoria;
import com.gameshop.domain.Gameshop;
import com.gameshop.service.ScraperService;
import com.gameshop.service.CategoriaService; 
import com.gameshop.service.GameshopService;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ScraperController {

    @Autowired
    private ScraperService scraperService;

    @Autowired
    private GameshopService gameshopService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/scraping")
    @ResponseBody // Para ver el resultado directamente en el navegador como texto
    public String ejecutarScraping() {
        try {
            // 1. Buscamos la categoría "Repuestos" en la DB (la que acabas de insertar)
            // Debes tener un método en tu service para buscar por nombre
            List<Categoria> categorias = categoriaService.getCategorias(true);
            Categoria catRepuestos = categorias.stream()
                .filter(c -> c.getNombre().equals("Repuestos"))
                .findFirst()
                .orElse(null);

            if (catRepuestos == null) return "Error: Categoría Repuestos no encontrada.";

            // 2. Ejecutamos el scraping (usando una URL de ejemplo de piezas retro)
            String urlRepuestos = "https://tienda-ejemplo-retro.com/repuestos"; 
            List<Gameshop> nuevosProductos = scraperService.extraerProductosRetro(urlRepuestos, catRepuestos);

            // 3. Guardamos en la base de datos local
            for (Gameshop producto : nuevosProductos) {
                gameshopService.save(producto);
            }

            return "¡Éxito! Se agregaron " + nuevosProductos.size() + " repuestos a la base de datos.";
            
        } catch (Exception e) {
            return "Error durante el proceso: " + e.getMessage();
        }
    }
}