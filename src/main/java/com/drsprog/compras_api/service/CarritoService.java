package com.drsprog.compras_api.service;

import com.drsprog.compras_api.dto.AgregarProductoRequest;
import com.drsprog.compras_api.dto.CarritoResponse;

public interface CarritoService {

    CarritoResponse obtenerCarritoPorUsuario(Long usuarioId);

    CarritoResponse agregarProducto(Long usuarioId, AgregarProductoRequest request);

    void quitarProducto(Long usuarioId, Long productoId);

    void vaciarCarrito(Long usuarioId);
}
