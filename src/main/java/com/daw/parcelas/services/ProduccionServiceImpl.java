package com.daw.parcelas.services;

import com.daw.parcelas.exceptions.RecursoNoEncontradoException;
import com.daw.parcelas.model.Calidad;
import com.daw.parcelas.model.Producto;
import com.daw.parcelas.model.Produccion;
import com.daw.parcelas.repository.ProduccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduccionServiceImpl implements ProduccionService {

    private final ProduccionRepository produccionRepository;

    public ProduccionServiceImpl(ProduccionRepository produccionRepository) {
        this.produccionRepository = produccionRepository;
    }

    @Override
    public List<Produccion> buscarPorProductoYCalidad(
            Producto producto, Calidad calidad) {

        if (producto != null && calidad != null) {
            return produccionRepository
                    .findAllByProductoAndCalidadOrderByFechaRecoleccionDesc(producto, calidad);
        }
        if (producto != null) {
            return produccionRepository
                    .findAllByProductoOrderByFechaRecoleccionDesc(producto);
        }
        if (calidad != null) {
            return produccionRepository
                    .findAllByCalidadOrderByFechaRecoleccionDesc(calidad);
        }
        return produccionRepository.findAllByOrderByFechaRecoleccionDesc();
    }

    @Override
    public Produccion buscarPorId(Long id) {
        return produccionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Producción con id " + id + " no encontrada"));
    }

    @Override
    public Produccion guardar(Produccion produccion) {
        return produccionRepository.save(produccion);
    }

    @Override
    public void eliminar(Long id) {
        produccionRepository.deleteById(id);
    }

    // Método para calcular el total de kilos producidos
    @Override
    public double kilosTotales() {
        return produccionRepository.findAll()
                .stream()
                .mapToDouble(Produccion::getCantidadKg)
                .sum();
    }

    // Método para contar la cantidad de producciones por calidad
    @Override
    public long contarPorCalidad(Calidad calidad) {
        return produccionRepository.countByCalidad(calidad);
    }

    // Método para obtener las últimas 5 producciones registradas
    @Override
    public List<Produccion> ultimas() {
        return produccionRepository.findTop5ByOrderByFechaRecoleccionDesc();
    }
}