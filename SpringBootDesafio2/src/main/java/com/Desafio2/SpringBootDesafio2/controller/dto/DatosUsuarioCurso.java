package com.Desafio2.SpringBootDesafio2.controller.dto;

import com.Desafio2.SpringBootDesafio2.model.Curso;
import com.Desafio2.SpringBootDesafio2.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosUsuarioCurso(
        @NotNull(message = "El Usuario no puede estar vacio")
        Usuario usuario,

        @NotNull(message = "El Curso no puede estar vacio")
        Curso curso

) {
}
