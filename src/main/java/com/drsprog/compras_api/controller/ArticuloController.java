package com.drsprog.compras_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drsprog.compras_api.entity.Articulo;
import com.drsprog.compras_api.repository.ArticuloRepository;
import com.drsprog.compras_api.service.ArticuloService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/articulos")
@RequiredArgsConstructor
public class ArticuloController {
    private final ArticuloRepository articuloRepository;
    private final ArticuloService articuloService;
    
    // Crear artículo por ID
    @PostMapping
    public ResponseEntity<Articulo> crearArticulo(@RequestBody Articulo articulo) {
        Articulo guardado = articuloRepository.save(articulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

     // Listar artículos
    @GetMapping
    public ResponseEntity<List<Articulo>> listarArticulos() {
        return ResponseEntity.ok(articuloRepository.findAll());
    }

     // Obtener artículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Articulo> obtenerArticulo(@PathVariable Long id) {
        Articulo articulo = articuloService.obteneArticuloPorId(id);
        return ResponseEntity.ok(articulo);
    }
    

}
