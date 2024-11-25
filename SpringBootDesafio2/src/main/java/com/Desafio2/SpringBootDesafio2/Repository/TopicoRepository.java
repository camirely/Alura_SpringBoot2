package com.Desafio2.SpringBootDesafio2.Repository;

import com.Desafio2.SpringBootDesafio2.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Optional <Topico> findByTituloAndMensaje(String titulo, String mensaje);

    Page<Topico> findAllByStatusTrueOrderByFechaCreacionAsc(Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE t.curso.nombre LIKE %:nombreCurso% AND YEAR(t.fechaCreacion) = :anio")
    Page<Topico> findByCursoNombreAndAnio(@Param("nombreCurso") String nombreCurso,
                                          @Param("anio") int anio,
                                          Pageable pageable);

    Optional<Topico> findById(Long idTopico);

}
