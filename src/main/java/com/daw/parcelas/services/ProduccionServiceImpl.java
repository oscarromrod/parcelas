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

    // Combina los filtros disponibles y conserva el orden por fecha descendente.
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

    // Devuelve la producción o lanza la excepción
    @Override
    public Produccion buscarPorId(Long id) {
        return produccionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Producción con id " + id + " no encontrada"));
    }

    // Guarda una producción nueva o actualiza la existente si ya tiene identificador.
    @Override
    public Produccion guardar(Produccion produccion) {
        return produccionRepository.save(produccion);
    }

    // Elimina la producción indicada por su identificador.
    @Override
    public void eliminar(Long id) {
        produccionRepository.deleteById(id);
    }

    // Suma las cantidades registradas para mostrar el total en el panel.
    @Override
    public double kilosTotales() {
        return produccionRepository.findAll()
                .stream()
                .mapToDouble(Produccion::getCantidadKg)
                .sum();
    }

    // Cuenta las producciones de la calidad indicada para el indicador del panel.
    @Override
    public long contarPorCalidad(Calidad calidad) {
        return produccionRepository.countByCalidad(calidad);
    }

    // Obtiene las cinco producciones más recientes mediante una consulta del repositorio.
    @Override
    public List<Produccion> ultimas() {
        return produccionRepository.findTop5ByOrderByFechaRecoleccionDesc();
    }
}