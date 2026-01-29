package com.drsprog.compras_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drsprog.compras_api.entity.Compra;


public interface CompraRepository extends JpaRepository<Compra, Long> {

}
