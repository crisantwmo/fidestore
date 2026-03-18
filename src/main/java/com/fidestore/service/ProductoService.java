package com.fidestore.service;

import com.fidestore.domain.Producto;
import com.fidestore.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Producto> getProductos() {
        return productoRepository.findByActivoTrue();
    }

    @Transactional(readOnly = true)
    public List<Producto> buscar(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return productoRepository.findByActivoTrue();
        }
        return productoRepository.buscarPorNombre(keyword);
    }

    @Transactional(readOnly = true)
    public Producto getProducto(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    @Transactional
    public void delete(Long id) {
        Producto p = productoRepository.findById(id).orElse(null);
        if (p != null) {
            p.setActivo(false);
            productoRepository.save(p);
        }
    }
}
