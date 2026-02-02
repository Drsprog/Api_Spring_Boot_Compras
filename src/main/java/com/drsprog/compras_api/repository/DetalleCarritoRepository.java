package com.drsprog.compras_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drsprog.compras_api.entity.DetalleCarrito;

public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Long> {
    Optional<DetalleCarrito> findByCarritoIdAndArticuloId(Long carritoId, Long articuloId);
    
}
