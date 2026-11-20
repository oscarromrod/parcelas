package com.daw.parcelas.services;

import com.daw.parcelas.exceptions.RecursoNoEncontradoException;
import com.daw.parcelas.model.Parcela;
import com.daw.parcelas.model.TipoCultivo;
import com.daw.parcelas.repository.ParcelaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ParcelaServiceImpl implements ParcelaService {

    private final ParcelaRepository parcelaRepository;

    //Constructor
    public ParcelaServiceImpl(ParcelaRepository parcelaRepository) {
        this.parcelaRepository = parcelaRepository;
    }

    //Devuelve todas las parcelas
    @Override
    public List<Parcela> listarTodas() {
        return parcelaRepository.findAll();
    }

    //Buscar parcelas filtrando por nombre y tipo de cultivo
    @Override
    public List<Parcela> buscarPorNombreYTipo(String nombre, String tipoCultivo) {
        if (tipoCultivo.equals("TODOS")) {
            return parcelaRepository.findAllByNombreContainingIgnoreCase(nombre);
        } else {
            return parcelaRepository.findAllByNombreContainingIgnoreCaseAndTipoCultivoEquals(nombre, TipoCultivo.valueOf(tipoCultivo));
        }
    }

    //Buscar parcela por id. Lanza una excepción si no se encuentra la parcela
    @Override
    public Parcela buscarPorId(Long id) {
        return parcelaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Parcela con id  " + id + " no encontrada"));
    }

    //Guardar parcela
    @Override
    public Parcela guardar(Parcela parcela) {
        return parcelaRepository.save(parcela);
    }

    //Eliminar parcela
    @Override
    public void eliminar(Long id) {
        parcelaRepository.deleteById(id);
    }

    //Calcula la superficie total de todas las parcelas
    @Override
    public double superficieTotal() {
        return parcelaRepository.findAll()
                .stream()
                .mapToDouble(Parcela::getSuperficie)
                .sum();
    }

}