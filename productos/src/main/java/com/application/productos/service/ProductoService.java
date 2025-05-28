package com.application.productos.service;

import com.application.productos.model.Producto;
import com.application.productos.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodos(int page, int size) {
        // Implementar paginación
        return productoRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Producto crearProducto(@Valid Producto producto) throws Exception {
        if (productoRepository.existsByNombre(producto.getNombre())) {
            throw new Exception("El producto ya existe");
        }
        return productoRepository.save(producto);
    }

    public Producto obtenerPorId(int id) throws ChangeSetPersister.NotFoundException {
        return productoRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

}