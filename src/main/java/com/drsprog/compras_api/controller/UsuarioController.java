package com.drsprog.compras_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.drsprog.compras_api.dto.ActualizarUsuarioRequest;
import com.drsprog.compras_api.dto.UsuarioRequest;
import com.drsprog.compras_api.dto.UsuarioResponse;
import com.drsprog.compras_api.entity.Usuario;
import com.drsprog.compras_api.service.UsuarioService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    // Crear usuario
    @PostMapping
    public ResponseEntity<UsuarioResponse> crearUsuario(@RequestBody UsuarioRequest request) {
        UsuarioResponse response = usuarioService.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    // Actualizar usuario
    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizarDatosUsuario(@PathVariable Long id, @RequestBody ActualizarUsuarioRequest request) {
        UsuarioResponse actualizado = usuarioService.actualizarDatosUsuario(id, request);
        return ResponseEntity.ok(actualizado);
    }

    //  Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<java.util.List<Usuario>> listarUsuarios() {
        java.util.List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
    
}
