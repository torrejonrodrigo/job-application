package com.application.productos.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Size(min = 3, max = 60)
    @Column(unique = true)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    public String getNombre() {
        return nombre;
    }
}