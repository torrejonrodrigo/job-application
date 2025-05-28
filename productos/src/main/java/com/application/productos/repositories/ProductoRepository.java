package com.application.productos.repositories;

import java.util.UUID;
import com.application.productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID> {
    boolean existsByNombre(String nombre);
}