package com.application.productos.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Size;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    @Size(min = 3, max = 60)
    @Column(unique = true)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

}