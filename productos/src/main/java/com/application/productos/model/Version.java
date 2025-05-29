package com.application.productos.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity(name = "`version`")
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @NotBlank
    @Pattern(regexp = "^(\\d+\\.\\d+\\.\\d+)$") // SemVer pattern
    private String numeroVersion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaLanzamiento;

    @Enumerated(EnumType.STRING)
    private Estado estado;
    private String notas;

    public Version(int id, Producto producto, String numeroVersion, LocalDate fechaLanzamiento, Estado estado, String notas) {
        this.id = id;
        this.producto = producto;
        this.numeroVersion = numeroVersion;
        this.fechaLanzamiento = fechaLanzamiento;
        this.estado = estado;
        this.notas = notas;
    }

    public Version() {
        // Default no-args constructor required by JPA
    }
}


