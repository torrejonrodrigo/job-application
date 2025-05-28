package com.application.productos.model;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;


@Data
@Entity(name = "`version`")
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @NotBlank
    @Pattern(regexp = "^(\\d+\\.\\d+\\.\\d+)$") // SemVer pattern
    private String numeroVersion;

    private LocalDate fechaLanzamiento;

    @Enumerated(EnumType.STRING)
    private Estado estado;
    private String notas;
}

enum Estado {
    ALFA, BETA, RELEASE
}