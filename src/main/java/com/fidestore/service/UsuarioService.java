package com.fidestore.service;

import com.fidestore.domain.Usuario;
import com.fidestore.domain.Rol; 
import com.fidestore.repository.UsuarioRepository;
import com.fidestore.repository.RolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository; 

    @Autowired
    private RolRepository rolRepository; 

    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll(); 
    }

    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioRepository.findById(usuario.getIdUsuario()).orElse(null);
    }

    @Transactional
    public void save(Usuario usuario, boolean crearRolUser) {
        // 1. Guardamos el usuario
        usuario = usuarioRepository.save(usuario);
        
        // 2. Si viene del registro (crearRolUser es true), le damos rol de cliente
        if (crearRolUser) {
            Rol rol = new Rol();
            rol.setNombre("ROLE_USER");
            rol.setIdUsuario(usuario.getIdUsuario());
            rolRepository.save(rol);
        }
    }

    @Transactional
    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}