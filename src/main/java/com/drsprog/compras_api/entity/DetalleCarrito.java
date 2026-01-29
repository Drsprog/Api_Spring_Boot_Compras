package com.drsprog.compras_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/*
 * Entidad que representa los detalles de un carrito de compras.
 */
@Entity
@Table(
    name = "DETALLE_CARRITO",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ID_CARRITO", "ID_ARTICULO"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DETALLE_CARRITO")
    private Long id;

    /**
     * Carrito al que pertenece este detalle.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CARRITO", nullable = false)
    private Carrito carrito;

    /**
     * Artículo agregado al carrito.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ARTICULO", nullable = false)
    private Articulo articulo;

    @Column(name = "CANTIDAD", nullable = false)
    @Builder.Default
    private Integer cantidad = 1;

    /**
     * Precio del artículo al momento de agregarlo al carrito.
     */
    @Column(name = "PRECIO_UNITARIO", nullable = false)
    private BigDecimal precioUnitario;
}
