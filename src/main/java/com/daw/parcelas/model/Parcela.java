package com.daw.parcelas.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La ubicación no puede estar vacía")
    @Column(nullable = false)
    private String ubicacion;

    @NotNull(message = "La superficie no puede estar vacía")
    private Double superficie;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El tipo de cultivo no puede estar vacío")
    private TipoCultivo tipoCultivo;
}
