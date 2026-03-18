package com.fidestore.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    private BigDecimal precio;

    private Integer stock;

    @Column(name = "url_imagen")
    private String urlImagen;

    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Usuario vendedor;
}
