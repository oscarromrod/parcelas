package com.daw.parcelas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity(name = "producciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Produccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{produccion.producto.obligatorio}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Producto producto;

    @NotNull(message = "{produccion.cantidad.obligatoria}")
    @DecimalMin(value = "0", inclusive = false, message = "{produccion.cantidad.positiva}")
    @DecimalMax(value = "1000000", message = "{produccion.cantidad.maxima}")
    @Column(name = "cantidad_kg", nullable = false)
    private Double cantidadKg;

    @NotNull(message = "{produccion.fecha.obligatoria}")
    @PastOrPresent(message = "{produccion.fecha.futura}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha_recoleccion", nullable = false)
    private LocalDate fechaRecoleccion;

    @NotNull(message = "{produccion.calidad.obligatoria}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Calidad calidad;
}
