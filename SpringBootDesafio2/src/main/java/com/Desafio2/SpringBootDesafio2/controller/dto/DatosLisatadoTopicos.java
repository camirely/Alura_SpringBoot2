package com.Desafio2.SpringBootDesafio2.controller.dto;

import com.Desafio2.SpringBootDesafio2.model.Topico;

import java.time.LocalDateTime;

public record DatosLisatadoTopicos(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        boolean status,
        String usuario,
        String curso
) {
    public DatosLisatadoTopicos(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.isStatus(),
                topico.getUsuario().getNombre(),
                topico.getCurso().getNombre());
    }
}
