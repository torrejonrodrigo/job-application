package com.application.productos.service;

import com.application.productos.model.Estado;
import com.application.productos.model.Version;
import com.application.productos.repositories.VersionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class VersionServiceTest {

    @InjectMocks
    private VersionService versionService;

    @Mock
    private VersionRepository versionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerVersionesPorProducto() {
        // Arrange
        Version version1 = new Version(1, null, "1.0.0", LocalDate.of(2025, 5, 29), Estado.RELEASE, "Initial release");
        Version version2 = new Version(2, null, "1.1.0", LocalDate.of(2025, 6, 30), Estado.BETA, "Beta release");
        when(versionRepository.findByProductoId(1)).thenReturn(Arrays.asList(version1, version2));

        // Act
        List<Version> versiones = versionService.obtenerVersionesPorProducto(1);

        // Assert
        assertEquals(2, versiones.size());
        assertEquals("1.0.0", versiones.get(0).getNumeroVersion());
        assertEquals("1.1.0", versiones.get(1).getNumeroVersion());
    }

    @Test
    void testObtenerPorId() throws ChangeSetPersister.NotFoundException {
        // Arrange
        Version version = new Version(1, null, "1.0.0", LocalDate.of(2025, 5, 29), Estado.RELEASE, "Initial release");
        when(versionRepository.findById(1)).thenReturn(Optional.of(version));

        // Act
        Version foundVersion = versionService.obtenerPorId(1);

        // Assert
        assertNotNull(foundVersion);
        assertEquals("1.0.0", foundVersion.getNumeroVersion());
    }

    @Test
    void testObtenerPorId_ThrowsNotFoundException_WhenVersionDoesNotExist() {
        // Arrange
        when(versionRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ChangeSetPersister.NotFoundException.class, () -> {
            versionService.obtenerPorId(1);
        });
    }
}
