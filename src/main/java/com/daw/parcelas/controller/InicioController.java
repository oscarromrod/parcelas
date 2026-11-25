package com.daw.parcelas.controller;

import com.daw.parcelas.model.Calidad;
import com.daw.parcelas.services.ParcelaService;
import com.daw.parcelas.services.ProduccionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    private final ParcelaService parcelaService;
    private final ProduccionService produccionService;

    public InicioController(ParcelaService parcelaService, ProduccionService produccionService) {
        this.parcelaService = parcelaService;
        this.produccionService = produccionService;
    }

    // Muestra la página de inicio con la información proporcionada por el servicio de parcelas
    @GetMapping("/")
    public String inicio(Model model) {
        // Recopilar información
        model.addAttribute("superficieTotal", parcelaService.superficieTotal());
        model.addAttribute("cantidadParcelas", parcelaService.contar());
        model.addAttribute("kilosTotales", produccionService.kilosTotales());
        model.addAttribute("partidasExtra", produccionService.contarPorCalidad(Calidad.EXTRA));
        model.addAttribute("ultimasRecolecciones", produccionService.ultimas());
        return "dashboard";
    }

}
