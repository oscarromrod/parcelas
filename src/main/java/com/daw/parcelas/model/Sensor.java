package com.daw.parcelas.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "sensors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{sensor.name.obligatorio}")
    @Size(max = 60, message = "{sensor.name.longitud}")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "{sensor.model.obligatorio}")
    @Size(max = 60, message = "{sensor.model.longitud}")
    @Column(nullable = false)
    private String model;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{sensor.tipo.obligatorio}")
    @Column(nullable = false, name = "tipo_sensor")
    private TipoSensor tipo;

    @NotBlank(message = "{sensor.unidadMedida.obligatoria}")
    @Size(max = 60, message = "{sensor.unidadMedida.longitud}")
    @Column(nullable = false, name = "unidad_medida")
    private String unidadMedida;

    @NotBlank(message = "{sensor.estado.obligatorio}")
    @Size(max = 60, message = "{sensor.estado.longitud}")
    @Column(nullable = false)
    private String estado;

    @NotNull(message = "{sensor.latitud.obligatoria}")
    @DecimalMin(value = "-90.0", message = "{sensor.latitud.rango}")
    @DecimalMax(value = "90.0", message = "{sensor.latitud.rango}")
    @Column(nullable = false)
    private Double latitud;

    @NotNull(message = "{sensor.longitud.obligatoria}")
    @DecimalMin(value = "-180.0", message = "{sensor.longitud.rango}")
    @DecimalMax(value = "180.0", message = "{sensor.longitud.rango}")
    @Column(nullable = false)
    private Double longitud;
}
