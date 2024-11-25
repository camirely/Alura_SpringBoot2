package com.Desafio2.SpringBootDesafio2.Repository;

import com.Desafio2.SpringBootDesafio2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioReposity extends JpaRepository<Usuario,Long> {
    UserDetails findByEmail(String email);
}
