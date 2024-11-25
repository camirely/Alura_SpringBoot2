package com.Desafio2.SpringBootDesafio2.controller.dto;

import com.Desafio2.SpringBootDesafio2.model.Topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico (
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        boolean status,
        Long usuarioId,
        Long cursoId) {
    public DatosRespuestaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.isStatus(),
                topico.getUsuario().getId(),
                topico.getCurso().getId()
        );

    }
}