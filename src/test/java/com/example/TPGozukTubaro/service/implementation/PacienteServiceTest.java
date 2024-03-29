package com.example.TPGozukTubaro.service.implementation;

import com.example.TPGozukTubaro.entity.Domicilio;
import com.example.TPGozukTubaro.entity.Odontologo;
import com.example.TPGozukTubaro.entity.Paciente;
import com.example.TPGozukTubaro.exceptions.ResourceNotFoundException;
import com.example.TPGozukTubaro.service.IPacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private IPacienteService pacienteService;
    @Test
    void guardar() {

       Domicilio domicilioTest = new Domicilio("asd",123,"asd","asd");
       Paciente pacienteTest = new Paciente("AlexiaG", "TEST", "12345678", LocalDate.now(), domicilioTest);

       pacienteService.guardar(pacienteTest);

       Paciente pacienteConfirmado = pacienteService.buscarPorId(pacienteTest.getId());

        assertEquals("AlexiaG", pacienteConfirmado.getNombre());
    }

    @Test
    void actualizar() {
        Domicilio domicilioTest = new Domicilio("asd",123,"asd","asd");
        Paciente pacienteTest = new Paciente("AlexiaG", "TEST", "12345678", LocalDate.now(), domicilioTest);

        pacienteService.guardar(pacienteTest);

        Paciente pacienteConfirmado = pacienteService.buscarPorId(pacienteTest.getId());

        pacienteConfirmado.setNombre("Gino");
        pacienteService.actualizar(pacienteConfirmado);

        assertEquals("Gino", pacienteConfirmado.getNombre());
    }

    @Test
    void eliminar() {
        Domicilio domicilioTest = new Domicilio("asd",123,"asd","asd");
        Paciente pacienteTest = new Paciente("AlexiaG", "TEST", "12345678", LocalDate.now(), domicilioTest);

        pacienteService.guardar(pacienteTest);

        pacienteService.eliminar(pacienteTest.getId());

        assertThrows(ResourceNotFoundException.class, () -> {
            pacienteService.buscarPorId(pacienteTest.getId());});


    }

    @Test
    void listarTodos() {
        Domicilio domicilioTest = new Domicilio("asd",123,"asd","asd");
        Domicilio domicilioTest2 = new Domicilio("asd",123,"asd","asd");
        Paciente pacienteTest = new Paciente("AlexiaG", "TEST", "12345678", LocalDate.now(), domicilioTest);
        Paciente pacienteTest2 = new Paciente("AlexiaG", "TEST", "12345678", LocalDate.now(), domicilioTest2);
        pacienteService.guardar(pacienteTest);
        pacienteService.guardar(pacienteTest2);

        List<Paciente> pacientes = pacienteService.listarTodos();

        assertTrue(pacientes.size()>0);
    }
}