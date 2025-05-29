package com.application.productos.controller;

import com.application.productos.model.Estado;
import com.application.productos.model.Version;
import com.application.productos.service.VersionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class VersionControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private VersionController versionController;

    @Mock
    private VersionService versionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(versionController).build();
    }

    @Test
    public void testListarVersiones() throws Exception {
        List<Version> versiones = new ArrayList<>();
        LocalDate testDate = LocalDate.of(2025, 5, 29); // Set a specific date for testing
        Version version1 = new Version(1, null, "1.0.0", testDate, Estado.RELEASE, "Initial release");
        Version version2 = new Version(2, null, "1.1.0", testDate, Estado.BETA, "Beta release");
        versiones.add(version1);
        versiones.add(version2);
        when(versionService.obtenerVersionesPorProducto(anyInt())).thenReturn(versiones);
        mockMvc.perform(get("/api/v1/productos/1/versiones"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"numeroVersion\":\"1.0.0\",\"fechaLanzamiento\":\"2025-05-29\",\"estado\":\"RELEASE\",\"notas\":\"Initial release\"}," +
                        "{\"id\":2,\"numeroVersion\":\"1.1.0\",\"fechaLanzamiento\":\"2025-05-29\",\"estado\":\"BETA\",\"notas\":\"Beta release\"}]"));
    }
    @Test
    public void testObtenerVersion() throws Exception {
        LocalDate testDate = LocalDate.of(2025, 5, 29); // Set a specific date for testing
        Version version = new Version(1, null, "1.0.0", testDate, Estado.RELEASE, "Initial release");
        when(versionService.obtenerPorId(1)).thenReturn(version);
        mockMvc.perform(get("/api/v1/productos/1/versiones/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"numeroVersion\":\"1.0.0\",\"fechaLanzamiento\":\"2025-05-29\",\"estado\":\"RELEASE\",\"notas\":\"Initial release\"}"));
    }
}
