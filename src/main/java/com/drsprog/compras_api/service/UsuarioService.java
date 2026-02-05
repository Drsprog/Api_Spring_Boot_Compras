package com.drsprog.compras_api.service;

import java.util.List;

import com.drsprog.compras_api.dto.ActualizarUsuarioRequest;
import com.drsprog.compras_api.dto.CambiarContrasenaRequest;
import com.drsprog.compras_api.dto.UsuarioRequest;
import com.drsprog.compras_api.dto.UsuarioResponse;
import com.drsprog.compras_api.entity.Usuario;

public interface UsuarioService {

     // Crear un nuevo usuario
    UsuarioResponse crearUsuario(UsuarioRequest usuario);

    // Obtener usuario por ID
    Usuario obtenerUsuarioPorId(Long id);

    // Listar todos los usuarios
    List<Usuario> listarUsuarios();

    // Actualizar usuario
    UsuarioResponse actualizarDatosUsuario(Long id, ActualizarUsuarioRequest request);

    // Eliminar usuario
    void eliminarUsuario(Long id);

    void cambiarContrasena(Long id, CambiarContrasenaRequest request);
}
