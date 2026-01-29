package com.drsprog.compras_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drsprog.compras_api.entity.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

}
