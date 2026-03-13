package com.fidestore.service;

import com.fidestore.domain.Usuario;
import com.fidestore.repository.UsuarioRepository; 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository; 

    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll(); 
    }

    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioRepository.findById(usuario.getIdUsuario()).orElse(null);
    }

    @Transactional
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}