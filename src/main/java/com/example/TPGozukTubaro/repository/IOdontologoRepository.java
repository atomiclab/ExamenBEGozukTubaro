package com.example.TPGozukTubaro.repository;

import com.example.TPGozukTubaro.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {
    Optional<Odontologo> findByMatricula(String matricula);
    @Query ("FROM Odontologo o ORDER BY o.matricula ASC")
    List<Odontologo> listOrderByMatricula();

    @Query("FROM Odontologo o ORDER BY o.nombre ASC")
    List<Odontologo> listOrderByNombre();

    @Query("FROM Odontologo o ORDER BY o.apellido ASC")
    List<Odontologo> listOrderByApellido();

}
