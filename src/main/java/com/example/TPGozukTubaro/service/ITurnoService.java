package com.example.TPGozukTubaro.service;

import com.example.TPGozukTubaro.DTO.request.TurnoRequestDTO;
import com.example.TPGozukTubaro.entity.Turno;

import java.util.List;

public interface ITurnoService {


    public TurnoRequestDTO guardar (TurnoRequestDTO turno);

    //¿TurnoResponseDTO guardar(TurnoRequestDTO turno);
    //public Turno guardar (Turno turno);

    public Turno buscarPorId(Long id);

    //public void actualizar(Turno turno);
    public void actualizar(TurnoRequestDTO turnoDTO);

    public void eliminar(Long id);

    public List<Turno> listarTodos();
}