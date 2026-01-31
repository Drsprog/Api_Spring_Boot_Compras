package com.drsprog.compras_api.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drsprog.compras_api.entity.Articulo;
import com.drsprog.compras_api.entity.Carrito;
import com.drsprog.compras_api.entity.Compra;
import com.drsprog.compras_api.entity.DetalleCarrito;
import com.drsprog.compras_api.entity.DetalleCompra;
import com.drsprog.compras_api.entity.Usuario;
import com.drsprog.compras_api.repository.ArticuloRepository;
import com.drsprog.compras_api.repository.CarritoRepository;
import com.drsprog.compras_api.repository.CompraRepository;
import com.drsprog.compras_api.repository.UsuarioRepository;
import com.drsprog.compras_api.service.CompraService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //Crea el constructor con los campos finales
public class CompraServiceImpl implements CompraService {

    private final UsuarioRepository usuarioRepository;
    private final CarritoRepository carritoRepository;
    private final CompraRepository compraRepository;
    private final ArticuloRepository articuloRepository;

    @Override
    @Transactional
    public Compra realizarCompra(Long usuarioId) {

        // Obtener usuario
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener carrito del usuario
        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("No existe carrito para este usuario"));

        if (carrito.getDetalles().isEmpty()) {
            throw new RuntimeException("Carrito vacío");
        }

        // 3️⃣ Crear nueva compra usando builder
        Compra compra = Compra.builder()
                .usuario(usuario)
                .fechaCompra(LocalDateTime.now())
                .total(BigDecimal.ZERO)
                .build();

        BigDecimal totalCompra = BigDecimal.ZERO;

        // 4️⃣ Copiar detalles del carrito a la compra
        for (DetalleCarrito detalleCarrito : carrito.getDetalles()) {
            Articulo articulo = detalleCarrito.getArticulo();

            if (!articulo.getActivo()) {
                throw new RuntimeException("Artículo inactivo: " + articulo.getNombre());
            }

            if (articulo.getStock() < detalleCarrito.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + articulo.getNombre());
            }

            // Descontar stock
            articulo.setStock(articulo.getStock() - detalleCarrito.getCantidad());
            articuloRepository.save(articulo);

            // Crear detalle de compra usando builder
            DetalleCompra detalleCompra = DetalleCompra.builder()
                    .articulo(articulo)
                    .cantidad(detalleCarrito.getCantidad())
                    .precioUnitario(detalleCarrito.getPrecioUnitario())
                    .build();

            compra.agregarDetalle(detalleCompra);

            totalCompra = totalCompra
                    .add(detalleCarrito.getPrecioUnitario().multiply(BigDecimal.valueOf(detalleCarrito.getCantidad())));
        }

        compra.setTotal(totalCompra);

        // Guardar la compra
        compraRepository.save(compra);

        // Limpiar carrito
        carrito.getDetalles().clear();
        carritoRepository.save(carrito);

        return compra;
    }
}
