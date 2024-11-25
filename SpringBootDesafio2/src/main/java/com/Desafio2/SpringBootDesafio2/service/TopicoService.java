package com.Desafio2.SpringBootDesafio2.service;
import com.Desafio2.SpringBootDesafio2.Repository.CursoRepository;
import com.Desafio2.SpringBootDesafio2.Repository.TopicoRepository;
import com.Desafio2.SpringBootDesafio2.Repository.UsuarioRepository;
import com.Desafio2.SpringBootDesafio2.controller.dto.DatosLisatadoTopicos;
import com.Desafio2.SpringBootDesafio2.controller.dto.DatosRespuestaTopico;
import com.Desafio2.SpringBootDesafio2.controller.dto.DatosRegistroTopico;
import com.Desafio2.SpringBootDesafio2.controller.dto.DatosUsuarioCurso;
import com.Desafio2.SpringBootDesafio2.model.Curso;
import com.Desafio2.SpringBootDesafio2.model.Topico;
import com.Desafio2.SpringBootDesafio2.model.Usuario;
import com.Desafio2.SpringBootDesafio2.service.Validaciones.ValidadorDeConsultas;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private List<ValidadorDeConsultas> validadores;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public DatosRespuestaTopico crearTopico(DatosRegistroTopico datos) {

        Usuario usuario=  buscarUsuarioAndCurso(datos).usuario();
        Curso curso= buscarUsuarioAndCurso(datos).curso();

        // Ejecutar todos los validadores
        validadores.forEach(v -> v.validar(datos));

        Topico topico = new Topico();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setStatus(true);
        topico.setUsuario(usuario);
        topico.setCurso(curso);

        Topico rs = topicoRepository.save(topico);
        return new DatosRespuestaTopico(rs);
    }

    public Page<DatosLisatadoTopicos> getTopicosOrdenadosPorFecha(Pageable pageable) {
        Page<Topico> topicos = topicoRepository.findAllByStatusTrueOrderByFechaCreacionAsc(pageable);
        return topicos.map(DatosLisatadoTopicos::new);
    }

    public Page<DatosLisatadoTopicos> buscarTopicosPorCursoYAnio(String nombreCurso, Integer anio, Pageable pageable) {
        Page<Topico> topicos = topicoRepository.findByCursoNombreAndAnio(nombreCurso, anio, pageable);
        return topicos.map(DatosLisatadoTopicos::new);
    }


    public DatosLisatadoTopicos retornaDatosTopico(Long id) {
        var topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            return new DatosLisatadoTopicos(topico.get());
        } else {
            throw new ValidationException("no existe este topico");
        }
    }

    public DatosRespuestaTopico editTopico(Long id, DatosRegistroTopico datos) {

       Usuario usuario=  buscarUsuarioAndCurso(datos).usuario();
       Curso curso= buscarUsuarioAndCurso(datos).curso();

        // Ejecutar todos los validadores
        validadores.forEach(v -> v.validar(datos));

        // Buscar el tópico por ID
        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            Topico obj = topico.get();
            obj.setTitulo(datos.titulo());
            obj.setMensaje(datos.mensaje());
            obj.setStatus(true);
            obj.setUsuario(usuario);
            obj.setCurso(curso);

            Topico actualizado = topicoRepository.save(obj);
            return new DatosRespuestaTopico(actualizado);

        } else {
            throw new ValidationException("Tópico no encontrado.");
        }
    }

    public DatosLisatadoTopicos deleteTopico(Long id) {

        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            var topicoDelete = topicoRepository.getReferenceById(id);
            topicoDelete.setStatus(false);
            return new DatosLisatadoTopicos(topicoDelete);
        } else {
            throw new ValidationException("Tópico no encontrado.");
        }

    }

    public DatosUsuarioCurso buscarUsuarioAndCurso(DatosRegistroTopico datos){

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

