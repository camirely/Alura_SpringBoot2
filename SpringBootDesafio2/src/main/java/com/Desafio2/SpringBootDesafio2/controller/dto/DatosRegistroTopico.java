package com.Desafio2.SpringBootDesafio2.controller.dto;

import com.Desafio2.SpringBootDesafio2.model.Topico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank(message = "El título no puede estar vacío")
        String titulo,

        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje,

        @NotNull(message = "El ID del usuario es obligatorio")
        Long usuario_id,

        @NotNull(message = "El ID del curso es obligatorio")
        Long curso_id
) {
}
