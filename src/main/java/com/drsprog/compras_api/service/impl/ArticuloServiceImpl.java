package com.drsprog.compras_api.service.impl;

import org.springframework.stereotype.Service;

import com.drsprog.compras_api.entity.Articulo;
import com.drsprog.compras_api.repository.ArticuloRepository;
import com.drsprog.compras_api.service.ArticuloService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticuloServiceImpl implements ArticuloService {
 private final ArticuloRepository articuloRepository;

    @Override
    public Articulo obteneArticuloPorId(Long articuloId) {
        return articuloRepository.findById(articuloId)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado con ID: " + articuloId));
    }
}
