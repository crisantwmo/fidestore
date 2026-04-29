package com.gameshop.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "videojuego")
public class Gameshop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_videojuego")
    private Long idVideojuego;

    private String titulo;
    private String desarrollador;
    private String genero;
    private double precio;

    @Column(length = 1024)
    private String rutaImagen;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    public Gameshop() {
    }

    // Constructor útil para cuando creas juegos desde el Scraper
    public Gameshop(String titulo, String desarrollador, double precio, String rutaImagen, Categoria categoria) {
        this.titulo = titulo;
        this.desarrollador = desarrollador;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
        this.categoria = categoria;
    }

    public Long getIdVideojuego() {
        return idVideojuego;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setIdVideojuego(Long idVideojuego) {
        this.idVideojuego = idVideojuego;
    }
}
