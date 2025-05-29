package com.application.productos.controller;

import com.application.productos.model.Producto;
import com.application.productos.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class ProductoControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ProductoController productoController;

    @Mock
    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
    }

    @Test
    public void testListarProductos() throws Exception {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Producto 1", "Descripción 1"));
        productos.add(new Producto(2, "Producto 2", "Descripción 2"));

        when(productoService.obtenerTodos(anyInt(), anyInt())).thenReturn(productos);

        mockMvc.perform(get("/api/v1/productos?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"nombre\":\"Producto 1\",\"descripcion\":\"Descripción 1\"},{\"id\":2,\"nombre\":\"Producto 2\",\"descripcion\":\"Descripción 2\"}]"));
    }

    @Test
    public void testCrearProducto() throws Exception {
        Producto producto = new Producto(1, "Nuevo Producto", "Descripción del nuevo producto");
        when(productoService.crearProducto(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/v1/productos")
                        .contentType("application/json")
                        .content("{\"nombre\":\"Nuevo Producto\",\"descripcion\":\"Descripción del nuevo producto\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"nombre\":\"Nuevo Producto\",\"descripcion\":\"Descripción del nuevo producto\"}"));
    }

    @Test
    public void testObtenerProducto() throws Exception {
        Producto producto = new Producto(1, "Producto 1", "Descripción 1");
        when(productoService.obtenerPorId(1)).thenReturn(producto);

        mockMvc.perform(get("/api/v1/productos/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"nombre\":\"Producto 1\",\"descripcion\":\"Descripción 1\"}"));
    }
}
