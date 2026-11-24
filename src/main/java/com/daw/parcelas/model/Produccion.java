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

    @NotNull(message = "El producto es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Producto producto;

    @NotNull(message = "La cantidad es obligatioria")
    @DecimalMin(value = "0", inclusive = false, message = "La cantidad debe ser mayor que cero")
    @DecimalMax(value = "1000000", message = "La cantidad no puede superar 1.000.000 kg")
    @Column(name = "cantidad_kg", nullable = false)
    private Double cantidadKg;

    @NotNull(message = "La fecha de recolección es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha_recoleccion", nullable = false)
    private LocalDate fechaRecoleccion;

    @NotNull(message = "La calidad es obligatoria")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Calidad calidad;
}
