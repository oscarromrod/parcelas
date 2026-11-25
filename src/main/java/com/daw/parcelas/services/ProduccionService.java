package com.daw.parcelas.services;

import com.daw.parcelas.model.Calidad;
import com.daw.parcelas.model.Producto;
import com.daw.parcelas.model.Produccion;

import java.util.List;

public interface ProduccionService {
    List<Produccion> buscarPorProductoYCalidad(Producto producto, Calidad calidad);
    Produccion buscarPorId(Long id);
    Produccion guardar(Produccion produccion);
    void eliminar(Long id);
    double kilosTotales();
    long contarPorCalidad(Calidad calidad);
    List<Produccion> ultimas();
}