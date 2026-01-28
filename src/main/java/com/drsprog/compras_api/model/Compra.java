package com.drsprog.compras_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa una compra realizada por un usuario.
 */

@Entity
@Table(name = "COMPRA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMPRA")
    private Long id;

    /**
     * Usuario que realizó la compra.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    /**
     * Fecha y hora en que se realizó la compra.
     */
    @Column(name = "FECHA_COMPRA", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime fechaCompra = LocalDateTime.now();

    /**
     * Detalles de la compra con los artículos adquiridos.
     */
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<DetalleCompra> detalles = new ArrayList<>();

    /**
     * Total de la compra.
     */
    @Column(name = "TOTAL", nullable = false)
    private BigDecimal total;

}
