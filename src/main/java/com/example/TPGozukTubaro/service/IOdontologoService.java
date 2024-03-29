package com.example.TPGozukTubaro.service;

import com.example.TPGozukTubaro.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    public Odontologo guardar (Odontologo odontologo);

    public Odontologo buscarPorId(Long id);

    public void actualizar(Odontologo odontologo);

    public void eliminar(Long id);

    public List<Odontologo> listarTodos();

    Optional<Odontologo> findByMatricula(String matricula);
    //nuevos del hql
    public List<Odontologo> listOrderByNombre();
    public List<Odontologo> listOrderByApellido();
    public List<Odontologo> listOrderByMatricula();


}
