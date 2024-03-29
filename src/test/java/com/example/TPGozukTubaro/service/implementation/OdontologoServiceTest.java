package com.example.TPGozukTubaro.service.implementation;

import com.example.TPGozukTubaro.entity.Odontologo;
import com.example.TPGozukTubaro.exceptions.ResourceNotFoundException;
import com.example.TPGozukTubaro.service.IOdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private IOdontologoService odontologoService;

    @Test
    void guardar() {
        Odontologo odontologoTest = new Odontologo("NicolasG", "Testeo", "1234TEST");
        odontologoService.guardar(odontologoTest);
        Odontologo odontologoTestRecienAgregado = odontologoService.buscarPorId(odontologoTest.getId());

        assertEquals("NicolasG", odontologoTestRecienAgregado.getNombre());
    }

    @Test
    void actualizar() {
        Odontologo odontologoTest = new Odontologo("NicolasA", "Testeo", "1234TEST");
        odontologoService.guardar(odontologoTest);
        Odontologo odontologoTestRecienAgregado = odontologoService.buscarPorId(odontologoTest.getId());

        odontologoTestRecienAgregado.setNombre("Guido");
        odontologoService.actualizar(odontologoTestRecienAgregado);

        Odontologo odontologoTestActualizado = odontologoService.buscarPorId(odontologoTestRecienAgregado.getId());

        assertEquals("Guido", odontologoTestActualizado.getNombre());
    }

    @Test
    void eliminar() {
        Odontologo odontologoTest = new Odontologo("NicolasE", "Testeo", "1234TEST");
        odontologoService.guardar(odontologoTest);
        // Odontologo odontologoTestRecienAgregado = odontologoService.buscarPorId(odontologoTest.getId());

        // odontologoService.guardar(odontologoTest);
        odontologoService.eliminar(odontologoTest.getId());

        //Odontologo odontologoRecienEliminado = odontologoService.buscarPorId(odontologoTest.getId());
        // Verifica que el odontólogo haya sido eliminado correctamente
        // Se tuvo que cambiar el test porque ahora tiramos una exception
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.buscarPorId(odontologoTest.getId()));

        //assertTrue(odontologoRecienEliminado == null);
    }

    @Test
    void listarOdontologos() {
        Odontologo odontologoTest = new Odontologo("NicolasL", "Testeo", "1234TEST");
        odontologoService.guardar(odontologoTest);
        Odontologo odontologoTest2 = new Odontologo("NicolasL2", "Segundo", "1234TEST");
        odontologoService.guardar(odontologoTest2);

        List<Odontologo> odontologos = odontologoService.listarTodos();

        assertTrue(odontologos.size() > 0);
    }

    @Test
    void findByMatricula() {
        Odontologo odontologoTest = new Odontologo("NicolasL", "Testeo", "123444");
        odontologoService.guardar(odontologoTest);

        Optional<Odontologo> odontologoEncontrado = odontologoService.findByMatricula(odontologoTest.getMatricula());
        odontologoEncontrado.ifPresent(odontologo -> assertEquals(odontologoTest.getMatricula(), odontologo.getMatricula()));
        odontologoService.eliminar(odontologoTest.getId());
        //asi la prueba puede correr y luego no tira un error que el odontologo esta duplicado (o mas cantidad)
    }


}