package com.daw.parcelas.services;

import com.daw.parcelas.model.Parcela;
import java.util.List;

public interface ParcelaService {

    List<Parcela> listarTodas();
    List<Parcela> buscarPorNombreYTipo(String nombre, String tipoCultivo);
    Parcela buscarPorId(Long id);
    Parcela guardar(Parcela parcela);
    void eliminar(Long id);
    double superficieTotal();
    long contar();

}
