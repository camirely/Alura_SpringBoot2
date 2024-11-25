package com.Desafio2.SpringBootDesafio2.service.Validaciones;

import com.Desafio2.SpringBootDesafio2.Repository.CursoRepository;
import com.Desafio2.SpringBootDesafio2.Repository.UsuarioRepository;
import com.Desafio2.SpringBootDesafio2.controller.dto.DatosRegistroTopico;
import com.Desafio2.SpringBootDesafio2.controller.dto.DatosUsuarioCurso;
import com.Desafio2.SpringBootDesafio2.model.Curso;
import com.Desafio2.SpringBootDesafio2.model.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ValidadorBuscarUsuarioMensaje {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public DatosUsuarioCurso BuscarUsuarioAndMensaje(DatosRegistroTopico datos){

        // Buscar el usuario y curso por sus ID
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(datos.usuario_id());
        Optional<Curso> cursoOptional = cursoRepository.findById(datos.curso_id());

        if (usuarioOptional.isEmpty()) {
            throw new ValidationException("Usuario no encontrado.");
        }
        if (cursoOptional.isEmpty()) {
            throw new ValidationException("Curso no encontrado.");
        }

        Usuario usuario = usuarioOptional.get();
        Curso curso = cursoOptional.get();

        return new DatosUsuarioCurso(usuario,curso);

    }
}
