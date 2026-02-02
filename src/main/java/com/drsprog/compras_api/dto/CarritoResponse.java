package com.drsprog.compras_api.dto;

import java.math.BigDecimal;
import java.util.List;

import com.drsprog.compras_api.entity.Carrito;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarritoResponse {
 private Long carritoId;
    private Long usuarioId;
    private List<ItemCarritoResponse> items;
    private BigDecimal total;

    public static CarritoResponse fromEntity(Carrito carrito) {

        List<ItemCarritoResponse> items = carrito.getDetalles().stream()
                .map(detalle -> ItemCarritoResponse.builder()
                        .articuloId(detalle.getArticulo().getId())
                        .nombre(detalle.getArticulo().getNombre())
                        .cantidad(detalle.getCantidad())
                        .precioUnitario(detalle.getPrecioUnitario())
                        .subtotal(detalle.getPrecioUnitario()
                                .multiply(BigDecimal.valueOf(detalle.getCantidad())))
                        .build()
                )
                .toList();

        BigDecimal total = items.stream()
                .map(ItemCarritoResponse::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CarritoResponse.builder()
                .carritoId(carrito.getId())
                .usuarioId(carrito.getUsuario().getId())
                .items(items)
                .total(total)
                .build();
    }
}
