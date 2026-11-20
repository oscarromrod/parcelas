package com.daw.parcelas.repository;

import com.daw.parcelas.model.Parcela;
import com.daw.parcelas.model.TipoCultivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {

    public List<Parcela> findAllByTipoCultivoEquals(TipoCultivo tipoCultivo);
    public List<Parcela> findAllByNombreContainingIgnoreCase(String nombre);
    public List<Parcela> findAllByNombreContainingIgnoreCaseAndTipoCultivoEquals(String nombre, TipoCultivo tipoCultivo);

}
