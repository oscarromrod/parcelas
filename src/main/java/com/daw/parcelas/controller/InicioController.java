package com.daw.parcelas.controller;

import com.daw.parcelas.services.ParcelaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    private final ParcelaService parcelaService;

    public InicioController(ParcelaService parcelaService) {
        this.parcelaService = parcelaService;
    }

    // Muestra la página de inicio con la información proporcionada por el servicio de parcelas
    @GetMapping("/")
    public String inicio(Model model) {
        // Recopilar información
        model.addAttribute("superficieTotal", parcelaService.superficieTotal());
        return "dashboard";
    }

}
