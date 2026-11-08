package com.daw.parcelas.controller;

import com.daw.parcelas.model.Parcela;
import com.daw.parcelas.model.TipoCultivo;
import com.daw.parcelas.services.ParcelaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parcelas")
public class ParcelaController {

    private final ParcelaService parcelaService;

    //Constructor
    public ParcelaController(ParcelaService parcelaService) {
        this.parcelaService = parcelaService;
    }

    //Lista de todas las parcelas
    @GetMapping
    public String listar(Model model) {
        var parcelas = parcelaService.listarTodas();
        model.addAttribute("parcelas", parcelas);
        return "parcelas/lista";
    }

    //Muestra el detalle de una parcela por su id
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        var parcela = parcelaService.buscarPorId(id);
        model.addAttribute("parcela", parcela);
        return "parcelas/detalle";
    }

    //Muestra el formulario para crear una nueva parcela
    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("parcela", new Parcela());
        model.addAttribute("tipos", TipoCultivo.values());
        return "parcelas/formulario";
    }

    //Guarda la parcela recibida desde el formulario y redirige a la lista de parcelas
    @PostMapping
    public String guardar(@ModelAttribute("parcela") Parcela parcela) {
        parcelaService.guardar(parcela);
        return "redirect:/parcelas";
    }

    //Elimina la parcela con el id recibido y redirige a la lista de parcelas
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        parcelaService.eliminar(id);
        return "redirect:/parcelas";
    }
}