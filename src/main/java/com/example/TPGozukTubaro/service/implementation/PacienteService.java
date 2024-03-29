package com.example.TPGozukTubaro.service.implementation;


import com.example.TPGozukTubaro.entity.Paciente;
import com.example.TPGozukTubaro.exceptions.ResourceNotFoundException;
import com.example.TPGozukTubaro.repository.IPacienteRepository;
import com.example.TPGozukTubaro.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);

    private IPacienteRepository pacienteRepository;
    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository)
    {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        LOGGER.info("Guardar paciente: "+paciente.getApellido());
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente buscarPorId(Long id) {
        LOGGER.info("Buscando paciente ID:" +id);
        Paciente paciente = null;
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if(pacienteOptional.isPresent()) {
           return pacienteOptional.get();
        }else{
        String message ="No se encontro el paciente con ID " + id;
        LOGGER.error(message);
        throw new ResourceNotFoundException(message);
        }
    }

    @Override
    public void actualizar(Paciente paciente) {
        LOGGER.info("Actualizando paciente");
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(paciente.getId());
        if (pacienteBuscado.isPresent())
            {
             pacienteRepository.save(paciente);
             LOGGER.info("Paciente actualizado!");
             }else{
           String message= "No se encontro al paciente";
           LOGGER.error(message);
           throw new ResourceNotFoundException(message);
        }
    }

    @Override
    public void eliminar(Long id) {
        LOGGER.info("Borrando paciente");
        Optional<Paciente> pacienteABorrar = pacienteRepository.findById(id);
        if (pacienteABorrar.isPresent())
        {
            pacienteRepository.deleteById(id);
            LOGGER.info("Paciente borrado!");
        }else{
            String message= "No se encontro al paciente con el ID" + id;
            LOGGER.error(message);
            throw new ResourceNotFoundException(message);
        }

    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }
}

