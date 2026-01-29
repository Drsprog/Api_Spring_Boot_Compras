package com.drsprog.compras_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drsprog.compras_api.entity.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    
    Optional<Carrito> findByUsuarioId(Long usuarioId);
}
