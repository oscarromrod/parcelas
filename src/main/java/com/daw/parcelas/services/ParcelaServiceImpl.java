package com.daw.parcelas.services;

import com.daw.parcelas.model.Parcela;
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

    //Buscar parcela por id. Lanza una excepción si no se encuentra la parcela
    @Override
    public Parcela buscarPorId(Long id) {
        return parcelaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe la parcela con id " + id));
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
}