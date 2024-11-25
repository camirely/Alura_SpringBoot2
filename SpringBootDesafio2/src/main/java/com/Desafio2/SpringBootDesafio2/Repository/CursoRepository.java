package com.Desafio2.SpringBootDesafio2.Repository;

import com.Desafio2.SpringBootDesafio2.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso,Long> {
}
