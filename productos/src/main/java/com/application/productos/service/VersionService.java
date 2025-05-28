package com.application.productos.service;

import com.application.productos.model.Version;
import com.application.productos.repositories.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VersionService {
    @Autowired
    private VersionRepository versionRepository;

    public List<Version> obtenerVersionesPorProducto(int productoId) {
        return versionRepository.findByProductoId(productoId);
    }

    public Version obtenerPorId(int id) throws ChangeSetPersister.NotFoundException {
        return versionRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
}