package com.Desafio2.SpringBootDesafio2.service.Validaciones;

import com.Desafio2.SpringBootDesafio2.Repository.TopicoRepository;
import com.Desafio2.SpringBootDesafio2.controller.dto.DatosRegistroTopico;
import com.Desafio2.SpringBootDesafio2.model.Topico;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidadorExisteTituloMensaje implements ValidadorDeConsultas{

    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(DatosRegistroTopico datos){
        // Verificar si ya existe un tópico con el mismo título y mensaje
        Optional<Topico> topicoExistente = topicoRepository.findByTituloAndMensaje(
                datos.titulo(), datos.mensaje()
        );

        if (topicoExistente.isPresent()) {
            throw new ValidationException("Ya existe un tópico con el mismo título y mensaje.");
        }
    }
}
