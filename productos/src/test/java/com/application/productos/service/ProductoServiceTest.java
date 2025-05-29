package com.application.productos.service;

import com.application.productos.model.Producto;
import com.application.productos.repositories.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodos() {
        // Arrange
        Producto producto1 = new Producto(1, "Producto 1", "Descripción 1");
        Producto producto2 = new Producto(2, "Producto 2", "Descripción 2");
        when(productoRepository.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(producto1, producto2)));

        // Act
        List<Producto> productos = productoService.obtenerTodos(0, 10);

        // Assert
        assertEquals(2, productos.size());
        assertEquals("Producto 1", productos.get(0).getNombre());
        assertEquals("Producto 2", productos.get(1).getNombre());
    }

    @Test
    void testCrearProducto() throws Exception {
        // Arrange
        Producto producto = new Producto(1, "Producto 1", "Descripción 1");
        when(productoRepository.existsByNombre(producto.getNombre())).thenReturn(false);
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        // Act
        Producto createdProducto = productoService.crearProducto(producto);

        // Assert
        assertNotNull(createdProducto);
        assertEquals("Producto 1", createdProducto.getNombre());
        verify(productoRepository).save(producto);
    }

    @Test
    void testCrearProducto_ThrowsException_WhenProductoExists() {
        // Arrange
        Producto producto = new Producto(1, "Producto 1", "Descripción 1");
        when(productoRepository.existsByNombre(producto.getNombre())).thenReturn(true);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            productoService.crearProducto(producto);
        });

        assertEquals("El producto ya existe", exception.getMessage());
        verify(productoRepository, never()).save(any(Producto.class));
    }

    @Test
    void testObtenerPorId() throws ChangeSetPersister.NotFoundException {
        // Arrange
        Producto producto = new Producto(1, "Producto 1", "Descripción 1");
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        // Act
        Producto foundProducto = productoService.obtenerPorId(1);

        // Assert
        assertNotNull(foundProducto);
        assertEquals("Producto 1", foundProducto.getNombre());
    }

    @Test
    void testObtenerPorId_ThrowsNotFoundException_WhenProductoDoesNotExist() {
        // Arrange
        when(productoRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ChangeSetPersister.NotFoundException.class, () -> {
            productoService.obtenerPorId(1);
        });
    }
}
