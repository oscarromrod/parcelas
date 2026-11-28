package com.daw.parcelas.controller;



import com.daw.parcelas.model.Sensor;
import com.daw.parcelas.model.TipoSensor;
import com.daw.parcelas.services.SensorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    // Conserva en el modelo los resultados y filtros del listado.
    @GetMapping
    public String getAllSensors(@RequestParam (required = false) String q,
                                @RequestParam (required = false) String tipo, Model model) {
        if (q == null) q = "";
        if (tipo == null) tipo = "TODOS";

        var sensors = sensorService.getAllSensorsByNameAndTipo(q, tipo);
        model.addAttribute("sensors", sensors);
        var tipos = TipoSensor.values();
        model.addAttribute("tipos", tipos);
        model.addAttribute("q", q);
        model.addAttribute("tipo", tipo);
        return "sensores/lista";
    }

    // Muestra únicamente sensores cuyo tipo es temperatura.
    @GetMapping("/temperatura")
    public String getAllSensorsTemperatura(Model model) {
        var sensors = sensorService.getAllSensorsTemperatura();
        model.addAttribute("sensors", sensors);
        model.addAttribute("titulo", "Sensores de Temperatura");
        return "sensores/lista";
    }

    // Carga el sensor solicitado para mostrar su ficha.
    @GetMapping("/{id}")
    public String getSensorById(@PathVariable Long id, Model model) {
        var sensor = sensorService.getSensorById(id);
        model.addAttribute("sensor", sensor);
        return "sensores/detalle";
    }

    // Proporciona un objeto vacío y los tipos disponibles para el alta.
    @GetMapping("/new")
    public String newSensor(Model model) {
        var tipos = TipoSensor.values();
        model.addAttribute("tipos", tipos);
        model.addAttribute("sensor", new Sensor());
        return "sensores/formulario";
    }

    // Carga el sensor y las opciones de tipo para reutilizar el formulario en la edición.
    @GetMapping("/update/{id}")
    public String updateSensor(@PathVariable Long id, Model model) {
        var sensor = sensorService.getSensorById(id);
        var tipos = TipoSensor.values();
        model.addAttribute("tipos", tipos);
        model.addAttribute("sensor", sensor);
        return "sensores/formulario";
    }

    // Ante errores de validación vuelve al formulario con los tipos disponibles.
    @PostMapping("/save")
    public String saveSensor(@Valid @ModelAttribute("sensor") Sensor sensor, BindingResult resultado, Model model) {
        if (resultado.hasErrors()) {
            model.addAttribute("tipos", TipoSensor.values());
            return "sensores/formulario";
        }
        sensorService.saveSensor(sensor);
        return "redirect:/sensors";
    }

    // Elimina el sensor solicitado y vuelve al listado.
    @PostMapping("/delete/{id}")
    public String deleteSensorById(@PathVariable Long id) {
        sensorService.deleteSensorById(id);
        return "redirect:/sensors";
    }


}
