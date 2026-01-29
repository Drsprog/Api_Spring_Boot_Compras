package com.drsprog.compras_api.service;

import com.drsprog.compras_api.entity.Compra;

public interface CompraService {

    /**
     * Realiza la compra del carrito activo del usuario
     * @param usuarioId id del usuario
     * @return Compra realizada con detalles
     * @throws RuntimeException si carrito vacío o stock insuficiente
     */
    Compra realizarCompra(Long usuarioId);   

}
