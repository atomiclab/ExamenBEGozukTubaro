package com.example.TPGozukTubaro.controller;


import com.example.TPGozukTubaro.entity.Paciente;
import com.example.TPGozukTubaro.service.IPacienteService;
import com.example.TPGozukTubaro.service.implementation.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private static final Logger LOGGER = Logger.getLogger(TurnoController.class);
    private IPacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        LOGGER.info("Se busco al paciente por ID" + id);
        return ResponseEntity.ok(pacienteService.buscarPorId(id));

    }
    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        LOGGER.info("Guardando el paciente" + paciente.getApellido());
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Paciente paciente) {
        ResponseEntity<String> response;
        Paciente pacienteBuscado = pacienteService.buscarPorId(paciente.getId());

        if (pacienteBuscado != null) {
            pacienteService.actualizar(paciente);
            response = ResponseEntity.ok("Se actualizó el paciente con id " + pacienteBuscado.getId());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el paciente a actualizar");
        }
        return response;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        ResponseEntity<String> response;
        Paciente pacienteBuscado = pacienteService.buscarPorId(id);

        if (pacienteBuscado != null) {
            pacienteService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.OK).body("Eliminado correctamente");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el paciente a eliminar con el ID: "+id) ;
        }
        return response;
    }
}
