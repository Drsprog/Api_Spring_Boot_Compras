package com.drsprog.compras_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drsprog.compras_api.entity.DetalleCompra;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long> {

}
