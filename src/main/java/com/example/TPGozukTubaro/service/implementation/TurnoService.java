package com.example.TPGozukTubaro.service.implementation;


import com.example.TPGozukTubaro.DTO.request.TurnoRequestDTO;
import com.example.TPGozukTubaro.entity.Odontologo;
import com.example.TPGozukTubaro.entity.Paciente;
import com.example.TPGozukTubaro.entity.Turno;
import com.example.TPGozukTubaro.exceptions.ResourceNotFoundException;
import com.example.TPGozukTubaro.repository.IOdontologoRepository;
import com.example.TPGozukTubaro.repository.IPacienteRepository;
import com.example.TPGozukTubaro.repository.ITurnoRepository;
import com.example.TPGozukTubaro.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private static final Logger LOGGER = Logger.getLogger(TurnoService.class);
    @Autowired
    private ITurnoRepository turnoRepository;
    @Autowired
    private IOdontologoRepository odontologoRepository;
    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public TurnoRequestDTO guardar(TurnoRequestDTO turnoRequestDTO) {
    LOGGER.info("Guardando un turno");
    Turno turno = new Turno();

        Optional.ofNullable(turnoRequestDTO.getOdontologoId()).ifPresent(odontologoId -> {
            Odontologo odontologo = odontologoRepository.findById(odontologoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Odontólogo no encontrado con ID " + odontologoId));
            turno.setOdontologo(odontologo);
        });


        Optional.ofNullable(turnoRequestDTO.getPacienteId()).ifPresent(pacienteId -> {
            Paciente paciente = pacienteRepository.findById(pacienteId)
                    .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID " + pacienteId));
            turno.setPaciente(paciente);
        });


        //por ultimo lo termino de componer poniendole la hora
    turno.setFechaHora(turnoRequestDTO.getFechaHora());
    //y lo guardamos
    turnoRepository.save(turno);
    //le damos al DTO el ID del generado save arriba
    turnoRequestDTO.setId(turno.getId());
    LOGGER.info("Turno guardado con exito!");
    return turnoRequestDTO;
}

    //  @Override metodo viejo
   // public Turno guardar(Turno turno) {
   //     return turnoRepository.save(turno);
   // }

    @Override
    public Turno buscarPorId(Long id) {
        Turno turno = null;
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if(turnoOptional.isPresent()) {
            turno = turnoOptional.get();
        }
        return turno;
    }

    @Override
    public void actualizar(TurnoRequestDTO turnoRequestDTO) {
        LOGGER.info("Actualizando al turno");
        Turno turno = objectMapper.convertValue(turnoRequestDTO, Turno.class);

        Turno tExistente = turnoRepository.findById(turnoRequestDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con ID "+turnoRequestDTO.getId()));
        tExistente.setFechaHora(turnoRequestDTO.getFechaHora());

        Optional.ofNullable(turnoRequestDTO.getOdontologoId()).ifPresent(odongoloboId -> {
            Odontologo odontologo = odontologoRepository.findById(turnoRequestDTO.getOdontologoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID " + turnoRequestDTO.getPacienteId()));
            tExistente.setOdontologo(odontologo);
        });


        Optional.ofNullable(turnoRequestDTO.getPacienteId()).ifPresent(pacienteId -> {
            Paciente paciente = pacienteRepository.findById(turnoRequestDTO.getPacienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID " + turnoRequestDTO.getPacienteId()));
            tExistente.setPaciente(paciente);
        });





        turnoRepository.save(tExistente);
        LOGGER.info("Turno actualizado");
    }

    // @Override metodo viejo
   // public void actualizar(TurnoRequestDTO turnoRequestDTO) {
   //     turnoRepository.save(turnoRequestDTO);
   // }


  /*  @Override
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    } Metodo viejo sin exception*/

    @Override
    public void eliminar (Long id){
        LOGGER.info("Eliminando turno ID: "+id);
        Optional<Turno> turnoABorrar = turnoRepository.findById(id);
        if (turnoABorrar.isPresent()){
            turnoRepository.deleteById(id);
            LOGGER.info("Borrado con exito!");
        }else{
            String message = "No se borro el turno porque no fue encontrado ID:" + id;
            LOGGER.error(message);
            throw new ResourceNotFoundException(message);
        }
    }

    @Override
    public List<Turno> listarTodos() {
        LOGGER.info("Listando los turnos");
        return turnoRepository.findAll();
    }
}
