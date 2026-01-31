package com.drsprog.compras_api.controller;

import com.drsprog.compras_api.entity.Compra;
import com.drsprog.compras_api.service.CompraService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {
    private final CompraService compraService;

    /**
     * Endpoint para realizar la compra del carrito de un usuario
     * POST /compras/{usuarioId}
     */
    @PostMapping("/{usuarioId}")
    public ResponseEntity<Compra> realizarCompra(@PathVariable Long usuarioId) {
        try {
            Compra compra = compraService.realizarCompra(usuarioId);
            return ResponseEntity.ok(compra);
        } catch (RuntimeException e) {
            // Manejo simple de errores
            return ResponseEntity.badRequest().body(null);
        }
    }

}
