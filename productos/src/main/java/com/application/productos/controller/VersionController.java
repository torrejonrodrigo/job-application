package com.application.productos.controller;

import com.application.productos.model.Version;
import com.application.productos.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/productos/{productoId}/versiones")
public class VersionController {
    @Autowired
    private VersionService versionService;

    @GetMapping
    public ResponseEntity<List<Version>> listarVersiones(@PathVariable UUID productoId) {
        return ResponseEntity.ok(versionService.obtenerVersionesPorProducto(productoId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Version> obtenerVersion(@PathVariable UUID id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(versionService.obtenerPorId(id));
    }

}