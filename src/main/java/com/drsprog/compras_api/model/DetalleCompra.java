package com.drsprog.compras_api.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Entidad que representa el detalle de una compra.
 * Contiene los artículos adquiridos y su información al momento de la compra.
 */
@Entity
@Table(
    name = "DETALLE_COMPRA",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ID_COMPRA", "ID_ARTICULO"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DETALLE_COMPRA")
    private Long id;

    /**
     * Compra a la que pertenece este detalle.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_COMPRA", nullable = false)
    private Compra compra;

    /**
     * Artículo adquirido en la compra.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ARTICULO", nullable = false)
    private Articulo articulo;

    /**
     * Cantidad del artículo adquirido.
     */
    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;

    /**
     * Precio unitario del artículo al momento de la compra.
     */
    @Column(name = "PRECIO_UNITARIO", nullable = false)
    private BigDecimal precioUnitario;
}