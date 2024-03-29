package com.example.TPGozukTubaro.repository;

import com.example.TPGozukTubaro.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente,Long> {
}
