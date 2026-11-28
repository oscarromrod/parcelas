package com.daw.parcelas.controller;

import com.daw.parcelas.model.Calidad;
import com.daw.parcelas.model.Producto;
import com.daw.parcelas.model.Produccion;
import com.daw.parcelas.services.ProduccionService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/producciones")
public class ProduccionController {

    private final ProduccionService produccionService;

    public ProduccionController(ProduccionService produccionService) {
        this.produccionService = produccionService;
    }

    // Publica las opciones del enum en las vistas
    @ModelAttribute("productos")
    public Producto[] productos() {
        return Producto.values();
    }

    // Publica las opciones de calidad en las vistas
    @ModelAttribute("calidades")
    public Calidad[] calidades() {
        return Calidad.values();
    }

    // Aplica los filtros seleccionados y prepara los totales de los resultados mostrados.
    @GetMapping
    public String listar(
            @RequestParam(required = false) Producto producto,
            @RequestParam(required = false) Calidad calidad,
            Model model) {

        List<Produccion> producciones = produccionService.buscarPorProductoYCalidad(producto, calidad);

        double totalKilos = producciones.stream()
                .mapToDouble(Produccion::getCantidadKg)
                .sum();

        model.addAttribute("producciones", producciones);
        model.addAttribute("numeroPartidas", producciones.size());
        model.addAttribute("totalKilos", totalKilos);
        model.addAttribute("productoSeleccionado", producto);
        model.addAttribute("calidadSeleccionada", calidad);

        return "producciones/lista";
    }

    // Busca la producción solicitada
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("produccion", produccionService.buscarPorId(id));
        return "producciones/detalle";
    }

    // Inicializa la fecha para que el formulario de alta proponga el día actual.
    @GetMapping("/nueva")
    public String nueva(Model model) {
        Produccion produccion = new Produccion();
        produccion.setFechaRecoleccion(LocalDate.now());

        model.addAttribute("produccion", produccion);
        return "producciones/formulario";
    }

    // Carga la producción existente para reutilizar el formulario en la edición.
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("produccion", produccionService.buscarPorId(id));
        return "producciones/formulario";
    }

    // Si hay errores, vuelve al formulario para conservar los valores
    @PostMapping
    public String guardar(
            @Valid @ModelAttribute("produccion") Produccion produccion,
            BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "producciones/formulario";
        }

        produccionService.guardar(produccion);
        return "redirect:/producciones";
    }

    // Elimina mediante POST
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        produccionService.eliminar(id);
        return "redirect:/producciones";
    }
}