package com.example.TPGozukTubaro.service.implementation;

import com.example.TPGozukTubaro.DTO.request.TurnoRequestDTO;
import com.example.TPGozukTubaro.entity.Domicilio;
import com.example.TPGozukTubaro.entity.Odontologo;
import com.example.TPGozukTubaro.entity.Paciente;
import com.example.TPGozukTubaro.entity.Turno;
import com.example.TPGozukTubaro.service.IOdontologoService;
import com.example.TPGozukTubaro.service.IPacienteService;
import com.example.TPGozukTubaro.service.ITurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private IOdontologoService odontologoService;
    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private ITurnoService turnoService;

    @Test
    void guardarTurno(){

        Odontologo odontologoTest = new Odontologo("NicolasG","Testeo","1234TEST");
        odontologoService.guardar(odontologoTest);

        Domicilio domicilioTest = new Domicilio("asd",123,"asd","asd");
        Paciente pacienteTest = new Paciente("AlexiaG", "TEST", "12345678", LocalDate.now(), domicilioTest);

        pacienteService.guardar(pacienteTest);

        TurnoRequestDTO turno = new TurnoRequestDTO();
        turno.setFechaHora(LocalDateTime.now());
        turno.setOdontologoId(odontologoTest.getId());
        turno.setPacienteId(pacienteTest.getId());

        turnoService.guardar(turno);

        Turno turnoConfirmado = turnoService.buscarPorId(turno.getId());
        assertEquals(turnoConfirmado.getOdontologo().getId(),odontologoTest.getId());
    }

    @Test
    void actualizarTurno(){
        Odontologo odontologoTest = new Odontologo("NicolasG","Testeo","1234TEST");
        odontologoService.guardar(odontologoTest);
        Odontologo odontologoTestNuevo = new Odontologo("NicolasG","Testeo","1234TEST");
        odontologoService.guardar(odontologoTestNuevo);

        Domicilio domicilioTest = new Domicilio("asd",123,"asd","asd");
        Paciente pacienteTest = new Paciente("AlexiaG", "TEST", "12345678", LocalDate.now(), domicilioTest);

        pacienteService.guardar(pacienteTest);

        TurnoRequestDTO turno = new TurnoRequestDTO();
        turno.setFechaHora(LocalDateTime.now());
        turno.setOdontologoId(odontologoTest.getId());
        turno.setPacienteId(pacienteTest.getId());

        turnoService.guardar(turno);

        turno.setOdontologoId(odontologoTestNuevo.getId());
        turnoService.actualizar(turno);

        assertEquals(turno.getOdontologoId(),odontologoTestNuevo.getId());
    }

    @Test
    void eliminarTurno(){

        Odontologo odontologoTest = new Odontologo("NicolasG","Testeo","1234TEST");
        odontologoService.guardar(odontologoTest);

        Domicilio domicilioTest = new Domicilio("asd",123,"asd","asd");
        Paciente pacienteTest = new Paciente("AlexiaG", "TEST", "12345678", LocalDate.now(), domicilioTest);

        pacienteService.guardar(pacienteTest);

        TurnoRequestDTO turno = new TurnoRequestDTO();
        turno.setFechaHora(LocalDateTime.now());
        turno.setOdontologoId(odontologoTest.getId());
        turno.setPacienteId(pacienteTest.getId());

        turnoService.guardar(turno);

        turnoService.eliminar(turno.getId());

        Turno turnoEliminado = turnoService.buscarPorId(turno.getId());

        assertTrue(turnoEliminado == null);

    }

    @Test
    void listarTodos(){

        Odontologo odontologoTest = new Odontologo("NicolasG","Testeo","1234TEST");
        odontologoService.guardar(odontologoTest);

        Domicilio domicilioTest = new Domicilio("asd",123,"asd","asd");
        Paciente pacienteTest = new Paciente("AlexiaG", "TEST", "12345678", LocalDate.now(), domicilioTest);

        pacienteService.guardar(pacienteTest);

        TurnoRequestDTO turno = new TurnoRequestDTO();
        turno.setFechaHora(LocalDateTime.now());
        turno.setOdontologoId(odontologoTest.getId());
        turno.setPacienteId(pacienteTest.getId());

        turnoService.guardar(turno);

        List<Turno> turnoList = turnoService.listarTodos();
        assertTrue(turnoList.size() > 0);

    }
}