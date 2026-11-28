package com.daw.parcelas.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "parcelas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{parcela.nombre.obligatorio}")
    @Size(max = 60, message = "{parcela.nombre.longitud}")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "{parcela.ubicacion.obligatoria}")
    @Size(max = 80, message = "{parcela.ubicacion.longitud}")
    @Column(nullable = false)
    private String ubicacion;

    @NotNull(message = "{parcela.superficie.obligatoria}")
    @DecimalMin(value = "0.01", message = "{parcela.superficie.rango}")
    @DecimalMax(value = "5000", message = "{parcela.superficie.rango}")
    private Double superficie;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{parcela.tipo.obligatorio}")
    private TipoCultivo tipoCultivo;
}
