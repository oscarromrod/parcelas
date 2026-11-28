package com.daw.parcelas.services;

import com.daw.parcelas.exceptions.RecursoNoEncontradoException;
import com.daw.parcelas.model.Sensor;
import com.daw.parcelas.model.TipoSensor;
import com.daw.parcelas.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    private static final Logger logger = LoggerFactory.getLogger(SensorServiceImpl.class);

    private final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    // Devuelve todos los sensores sin aplicar filtros.
    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    // Combina la búsqueda parcial por nombre con el filtro opcional de tipo.
    @Override
    public List<Sensor> getAllSensorsByNameAndTipo(String name, String tipo) {
        if (tipo.equals("TODOS")) {
            return sensorRepository.findAllByNameContainingIgnoreCase(name);
        } else {
            return sensorRepository.findAllByNameContainingIgnoreCaseAndTipoEquals(name, TipoSensor.valueOf(tipo));
        }
    }

    // Devuelve el sensor solicitado o indica que no existe.
    @Override
    public Sensor getSensorById(Long id) {
        return sensorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Sensor con id " + id + " no encontrado"));
    }
    // Registra la consulta y obtiene los sensores de temperatura.
    @Override
    public List<Sensor> getAllSensorsTemperatura() {
        logger.debug("Obteniendo todos los sensores de temperatura");
        return sensorRepository.findAllByTipoEquals(TipoSensor.TEMPERATURA);
    }

    // Persiste los datos del sensor recibido.
    @Override
    public void saveSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    // Elimina el sensor indicado por su identificador.
    @Override
    public void deleteSensorById(Long id) {
        sensorRepository.deleteById(id);
    }

}
