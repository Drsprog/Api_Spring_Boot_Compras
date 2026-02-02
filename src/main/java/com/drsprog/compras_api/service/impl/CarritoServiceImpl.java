package com.drsprog.compras_api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drsprog.compras_api.dto.AgregarProductoRequest;
import com.drsprog.compras_api.dto.CarritoResponse;
import com.drsprog.compras_api.entity.Articulo;
import com.drsprog.compras_api.entity.Carrito;
import com.drsprog.compras_api.entity.DetalleCarrito;
import com.drsprog.compras_api.entity.Usuario;
import com.drsprog.compras_api.repository.ArticuloRepository;
import com.drsprog.compras_api.repository.CarritoRepository;
import com.drsprog.compras_api.repository.UsuarioRepository;
import com.drsprog.compras_api.service.CarritoService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CarritoServiceImpl implements CarritoService {
    private final UsuarioRepository usuarioRepository;
    private final CarritoRepository carritoRepository;
    private final ArticuloRepository articuloRepository;

    @Override
    public CarritoResponse obtenerCarritoPorUsuario(Long usuarioId) {
        Carrito carrito = obtenerOCrearCarrito(usuarioId);
        return CarritoResponse.fromEntity(carrito);
    }

    @Override
    public CarritoResponse agregarProducto(Long usuarioId, AgregarProductoRequest request) {
        Carrito carrito = obtenerOCrearCarrito(usuarioId);

        Articulo articulo = articuloRepository.findById(request.getArticuloId())
                .orElseThrow(() -> new EntityNotFoundException("Artículo no encontrado"));

        if (!articulo.getActivo()) {
            throw new RuntimeException("El artículo está inactivo");
        }

        DetalleCarrito detalle = carrito.getDetalles().stream()
                .filter(d -> d.getArticulo().getId().equals(articulo.getId()))
                .findFirst()
                .orElse(null);

        if (detalle != null) {
            detalle.setCantidad(detalle.getCantidad() + request.getCantidad());
        } else {
            DetalleCarrito nuevoDetalle = DetalleCarrito.builder()
                    .carrito(carrito)
                    .articulo(articulo)
                    .cantidad(request.getCantidad())
                    .precioUnitario(articulo.getPrecio())
                    .build();

            carrito.getDetalles().add(nuevoDetalle);
        }

        carritoRepository.save(carrito);
        return CarritoResponse.fromEntity(carrito);
    }

    @Override
    public void quitarProducto(Long usuarioId, Long articuloId) {

        Carrito carrito = obtenerOCrearCarrito(usuarioId);

        carrito.getDetalles().removeIf(detalle -> detalle.getArticulo().getId().equals(articuloId));

        carritoRepository.save(carrito);
    }

    @Override
    public void vaciarCarrito(Long usuarioId) {
        Carrito carrito = obtenerOCrearCarrito(usuarioId);
        carrito.getDetalles().clear();
        carritoRepository.save(carrito);
    }

    /*
     * =========================
     * MÉTODO AUXILIAR CENTRAL
     * =========================
     */

    private Carrito obtenerOCrearCarrito(Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        return carritoRepository.findByUsuarioId(usuarioId)
                .orElseGet(() -> {
                    Carrito carrito = Carrito.builder()
                            .usuario(usuario)
                            .build();
                    return carritoRepository.save(carrito);
                });
    }
}
