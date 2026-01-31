package com.drsprog.compras_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drsprog.compras_api.DTOs.AgregarProductoRequest;
import com.drsprog.compras_api.DTOs.CarritoResponse;
import com.drsprog.compras_api.service.CarritoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/carritos")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    // Ver carrito de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarritoResponse> obtenerCarrito(@PathVariable Long usuarioId) {
        CarritoResponse response = carritoService.obtenerCarritoPorUsuario(usuarioId);
        return ResponseEntity.ok(response);
    }

    // Agregar producto al carrito
    @PostMapping("/usuario/{usuarioId}/productos")
    public ResponseEntity<CarritoResponse> agregarProducto(
            @PathVariable Long usuarioId,
            @RequestBody AgregarProductoRequest request) {

        CarritoResponse response = carritoService.agregarProducto(usuarioId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Quitar producto del carrito
    @DeleteMapping("/usuario/{usuarioId}/productos/{productoId}")
    public ResponseEntity<Void> quitarProducto(
            @PathVariable Long usuarioId,
            @PathVariable Long productoId) {

        carritoService.quitarProducto(usuarioId, productoId);
        return ResponseEntity.noContent().build();
    }

    // Vaciar carrito
    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long usuarioId) {
        carritoService.vaciarCarrito(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
