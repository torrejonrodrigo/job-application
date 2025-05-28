package com.application.productos.controller;

import com.application.productos.model.Version;
import com.application.productos.service.VersionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productos/{productoId}/versiones")
@Tag(name = "Version API")
public class VersionController {
    @Autowired
    private VersionService versionService;

    @GetMapping
    public ResponseEntity<List<Version>> listarVersiones(@PathVariable int productoId) {
        return ResponseEntity.ok(versionService.obtenerVersionesPorProducto(productoId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Version> obtenerVersion(@PathVariable int id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(versionService.obtenerPorId(id));
    }

}