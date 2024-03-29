package com.example.TPGozukTubaro.controller;


import com.example.TPGozukTubaro.DTO.request.TurnoRequestDTO;
import com.example.TPGozukTubaro.DTO.response.TurnoResponseDTO;
import com.example.TPGozukTubaro.entity.Turno;
import com.example.TPGozukTubaro.service.IOdontologoService;
import com.example.TPGozukTubaro.service.IPacienteService;
import com.example.TPGozukTubaro.service.ITurnoService;
import com.example.TPGozukTubaro.service.implementation.OdontologoService;
import com.example.TPGozukTubaro.service.implementation.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private static final Logger LOGGER = Logger.getLogger(TurnoController.class);
    private ITurnoService turnoService;
    @Autowired
    public TurnoController(TurnoService turnoService){
        this.turnoService = turnoService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.buscarPorId(id));
    }
    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @PutMapping
    public ResponseEntity actualizar(@RequestBody TurnoRequestDTO turno) {
        ResponseEntity response;
        try{
            turnoService.actualizar(turno);
            response = ResponseEntity.ok("Se actualizo el turno con id " + turno.getId());
        }catch (Exception e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el paciente a actualizar");
        }
        return response;
    }
/*@PutMapping
public ResponseEntity actualizar(@RequestBody TurnoRequestDTO turno) {
    turnoService.actualizar(turno);
    return ResponseEntity.ok("Se actualizó el turno con id " + turno.getId());
}*/

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        ResponseEntity response;
        Turno turnobuscado = turnoService.buscarPorId(id);
        if (turnobuscado != null) {
            turnoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.OK).body("Eliminado correctamente");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el turno a eliminar");
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<TurnoRequestDTO> guardar(@RequestBody TurnoRequestDTO turno) {
        return ResponseEntity.ok(turnoService.guardar(turno));
    }
}
