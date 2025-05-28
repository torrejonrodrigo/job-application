package com.application.productos.controller;

import com.application.productos.model.Producto;
import com.application.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(productoService.obtenerTodos(page, size));
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crearProducto(producto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable UUID id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

}
