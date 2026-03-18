package com.fidestore.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
<<<<<<< HEAD

    private String nombre;
    private String correo;
    private String contrasena;
    private String rol;
    private Boolean activo = true;

    public Usuario() {}

    public Usuario(String nombre, String correo, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
}
=======
    
    @Column(name="nombre") 
    private String username;   

    @Column(name="contrasena") 
    private String password;   

    private String correo;
    
    @Transient
    private String rutaImagen; 

    public Usuario() {}

    public Long getIdUsuario() { return idUsuario; }
    public String getUsername() { return username; }
    public String getNombre() { return username; }
}
>>>>>>> 1cec6c828a55946f962a8aeb349aefdcf03a1ea2
