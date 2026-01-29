package com.drsprog.compras_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa el carrito de compras de un usuario.
 * Es temporal y solo existe mientras el usuario no finalice la compra.
 */

@Entity
@Table(name = "CARRITO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARRITO")
    private Long id;


    /**
     * Un usuario tiene un solo carrito activo.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false, unique = true)
    private Usuario usuario;

    /**
     * Detalles del carrito con los artículos agregados.
     */
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<DetalleCarrito> detalles = new ArrayList<>();

    @Column(name = "ACTIVO", nullable = false)
    @Builder.Default
    private Boolean activo = true;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
