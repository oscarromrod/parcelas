package com.daw.parcelas.controller;

import com.daw.parcelas.model.Parcela;
import com.daw.parcelas.model.TipoCultivo;
import com.daw.parcelas.services.ParcelaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parcelas")
public class ParcelaController {

    private final ParcelaService parcelaService;

    //Constructor
    public ParcelaController(ParcelaService parcelaService) {
        this.parcelaService = parcelaService;
    }

    //Lista de parcelas, con opción de filtrar por nombre y tipo de cultivo.
    //Muestra la superficie total de todas las parcelas
    @GetMapping
    public String listar(@RequestParam (required = false) String q,
                         @RequestParam (required = false) String tipo,
                         Model model) {
        if (q == null) q = "";
        if (tipo == null) tipo = "TODOS";

        var parcelas = parcelaService.buscarPorNombreYTipo(q, tipo);

        model.addAttribute("parcelas", parcelas);
        model.addAttribute("superficieTotal", parcelaService.superficieTotal());
        model.addAttribute("tipos", TipoCultivo.values());
        model.addAttribute("q", q);
        model.addAttribute("tipo", tipo);
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

    //Muestra el formulario para editar una parcela existente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        var parcela = parcelaService.buscarPorId(id);
        model.addAttribute("parcela", parcela);
        model.addAttribute("tipos", TipoCultivo.values());
        return "parcelas/formulario";
    }

    //Guarda la parcela recibida desde el formulario y redirige a la lista de parcelas
    @PostMapping
    public String guardar(@Valid @ModelAttribute ("parcela") Parcela parcela, BindingResult resultado, Model model) {
        // Si la validación falla, se vuelve al formulario para conservar los datos y mostrar los errores.
        if (resultado.hasErrors()){
            model.addAttribute("tipos", TipoCultivo.values());
            return "parcelas/formulario";
        }
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