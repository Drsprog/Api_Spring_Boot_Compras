package com.drsprog.compras_api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drsprog.compras_api.dto.UsuarioRequest;
import com.drsprog.compras_api.dto.UsuarioResponse;
import com.drsprog.compras_api.entity.Usuario;
import com.drsprog.compras_api.exception.UsuarioNotFoundException;
import com.drsprog.compras_api.repository.UsuarioRepository;
import com.drsprog.compras_api.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //Crea el constructor con los campos finales
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
       Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .contrasena(request.getContrasena())
                .email(request.getEmail())
                .dni(request.getDni())
                .fechaNacimiento(request.getFechaNacimiento())
                .telefono(request.getTelefono())
                .build();

        Usuario guardado = usuarioRepository.save(usuario);

        return UsuarioResponse.builder()
                .id(guardado.getId())
                .nombre(guardado.getNombre())
                .apellido(guardado.getApellido())
                .email(guardado.getEmail())
                .dni(guardado.getDni())
                .fechaNacimiento(guardado.getFechaNacimiento())
                .telefono(guardado.getTelefono())
                .fechaCreacion(guardado.getFechaCreacion())
                .build();
    }


    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = obtenerUsuarioPorId(id);

        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setApellido(usuarioActualizado.getApellido());
        usuarioExistente.setContrasena(usuarioActualizado.getContrasena());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setDni(usuarioActualizado.getDni());
        usuarioExistente.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
        usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
        Usuario usuario = obtenerUsuarioPorId(id);
        usuarioRepository.delete(usuario);
    }

}
