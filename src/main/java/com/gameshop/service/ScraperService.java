package com.gameshop.service;

import com.gameshop.domain.Gameshop;
import com.gameshop.domain.Categoria;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {

    public List<Gameshop> extraerProductosRetro(String url, Categoria categoria) {
        List<Gameshop> productos = new ArrayList<>();
        try {
            // 1. Conectamos a la web
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(6000)
                    .get();

            // 2. Seleccionamos el contenedor de los productos (cambia el selector según la web)
            Elements items = doc.select(".product-layout"); 

            for (Element item : items) {
                Gameshop juego = new Gameshop();
                
                // 3. Extraemos los datos
                String nombre = item.select(".name").text();
                String precioTexto = item.select(".price").text().replaceAll("[^0-9.]", "");
                String imgUrl = item.select("img").attr("src");

                // 4. Seteamos la entidad
                juego.setTitulo(nombre);
                juego.setPrecio(precioTexto.isEmpty() ? 0.0 : Double.parseDouble(precioTexto));
                juego.setRutaImagen(imgUrl);
                juego.setCategoria(categoria); // Le asignamos la categoría que pasamos por parámetro
                juego.setDesarrollador("Generic Retro");
                juego.setGenero("Vintage");

                productos.add(juego);
            }
        } catch (IOException e) {
            System.err.println("Error al realizar el scraping: " + e.getMessage());
        }
        return productos;
    }
}