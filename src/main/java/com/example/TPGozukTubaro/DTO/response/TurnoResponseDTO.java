package com.example.TPGozukTubaro.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoResponseDTO {
    private Long id;
    private Long odontologoid;
    private Long pacienteid;
    private LocalDateTime fecha;
}