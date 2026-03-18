package com.fidestore.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data 
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long idUsuario;
    
    @Column(name="nombre") 
    private String username;   

    @Column(name="contrasena") 
    private String password;   

    private String correo;
    
    @Transient
    private String rutaImagen; 

    public Usuario() {}

    // Getters para que Thymeleaf encuentre los datos
    public Long getIdUsuario() { return idUsuario; }
    public String getUsername() { return username; }
    public String getNombre() { return username; }
}