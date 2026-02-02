package com.drsprog.compras_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drsprog.compras_api.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
Optional<Usuario> findByEmail(String email);
}
