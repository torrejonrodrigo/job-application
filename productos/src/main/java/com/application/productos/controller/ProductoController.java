package com.application.productos.controller;

import com.application.productos.model.Producto;
import com.application.productos.service.ProductoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Product API")
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
    public ResponseEntity<Producto> obtenerProducto(@PathVariable int id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

}
