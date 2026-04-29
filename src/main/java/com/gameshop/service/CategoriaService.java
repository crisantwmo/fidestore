package com.gameshop.service;

import com.gameshop.domain.Categoria;
import com.gameshop.repository.CategoriaRepository; 
import java.util.ArrayList; 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class CategoriaService { 

    @Autowired
    private CategoriaRepository categoriaRepository; 

    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activos) {
        List<Categoria> lista = new ArrayList<>(categoriaRepository.findAll());
        
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        // Usamos el ID para buscar en el repositorio
        return categoriaRepository.findById(categoria.getIdCategoria()).orElse(null);
    }

    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Transactional
    public void delete(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }
}