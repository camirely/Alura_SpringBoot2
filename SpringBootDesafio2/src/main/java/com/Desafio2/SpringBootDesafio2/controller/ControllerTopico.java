package com.Desafio2.SpringBootDesafio2.controller;

import com.Desafio2.SpringBootDesafio2.controller.dto.DatosLisatadoTopicos;
import com.Desafio2.SpringBootDesafio2.controller.dto.DatosRespuestaTopico;
import com.Desafio2.SpringBootDesafio2.controller.dto.DatosRegistroTopico;
import com.Desafio2.SpringBootDesafio2.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topicos")
public class ControllerTopico {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrar(@RequestBody @Valid DatosRegistroTopico datos) {
        var respuesta = topicoService.crearTopico(datos);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosLisatadoTopicos>> listadoTopicos(@PageableDefault(size = 10) Pageable pageable) {
        Page<DatosLisatadoTopicos> respuesta = topicoService.getTopicosOrdenadosPorFecha(pageable);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<DatosLisatadoTopicos>> buscarTopicos(@RequestParam String nombreCurso, @RequestParam Integer anio,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<DatosLisatadoTopicos> respuesta = topicoService.buscarTopicosPorCursoYAnio(nombreCurso, anio, pageable);
            return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosLisatadoTopicos> retornaDatosTopico(@PathVariable Long id) {
        DatosLisatadoTopicos respuestaTopico = topicoService.retornaDatosTopico(id);

        DatosLisatadoTopicos respuesta = topicoService.retornaDatosTopico(id);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> edit(@PathVariable Long id, @RequestBody DatosRegistroTopico datos){
        DatosRespuestaTopico respuesta = topicoService.editTopico(id, datos);

        return ResponseEntity.ok(respuesta);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        DatosLisatadoTopicos respuesta = topicoService.deleteTopico(id);

        return ResponseEntity.ok().build();
    }


}


