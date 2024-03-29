package com.example.TPGozukTubaro.service;

import com.example.TPGozukTubaro.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    public Paciente guardar (Paciente paciente);

    public Paciente buscarPorId(Long id);

    public void actualizar(Paciente paciente);

    public void eliminar(Long id);

    public List<Paciente> listarTodos();
}
