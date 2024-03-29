package com.example.TPGozukTubaro.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class TurnoRequestDTO {
    private Long odontologoId;
    private Long pacienteId;
    private LocalDateTime fechaHora;
    private Long id;
}
