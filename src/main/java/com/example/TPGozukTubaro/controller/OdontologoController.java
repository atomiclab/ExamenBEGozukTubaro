package com.example.TPGozukTubaro.controller;

import com.example.TPGozukTubaro.entity.Odontologo;
import com.example.TPGozukTubaro.service.IOdontologoService;
import com.example.TPGozukTubaro.service.implementation.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }
    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos() {
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Odontologo odontologo) {
        ResponseEntity<String> response;
        Odontologo odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBuscado != null) {
            odontologoService.actualizar(odontologo);
            response = ResponseEntity.ok("Odontologo actualizado, id: " + odontologo.getId());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odontologo no encontrado");
        }
        return response;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        ResponseEntity<String> response;
        Odontologo odontologoBuscado = odontologoService.buscarPorId(id);
        if (odontologoBuscado != null) {
            odontologoService.eliminar(id);
            response = ResponseEntity.ok("Odontologo eliminado, id: " + id);
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odontogolo a eliminar no encontrado");
        }
        return response;
    }
    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Odontologo> findByMatricula(@PathVariable String matricula) throws Exception {
        Optional<Odontologo> odontologoOptional = odontologoService.findByMatricula(matricula);

        if (odontologoOptional.isPresent()) {
            return ResponseEntity.ok(odontologoOptional.get());
        } else {
            throw new Exception("No se encontro el odontologo con matrícula " + matricula);
        }
    }

}
