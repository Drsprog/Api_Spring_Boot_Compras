package com.drsprog.compras_api.service;

import java.util.List;

import com.drsprog.compras_api.entity.Usuario;

public interface UsuarioService {

     // Crear un nuevo usuario
    Usuario crearUsuario(Usuario usuario);

    // Obtener usuario por ID
    Usuario obtenerUsuarioPorId(Long id);

    // Listar todos los usuarios
    List<Usuario> listarUsuarios();

    // Actualizar usuario
    Usuario actualizarUsuario(Long id, Usuario usuarioActualizado);

    // Eliminar usuario
    void eliminarUsuario(Long id);
}
