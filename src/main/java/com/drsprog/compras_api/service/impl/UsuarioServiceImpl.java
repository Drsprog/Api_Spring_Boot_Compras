package com.drsprog.compras_api.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.drsprog.compras_api.entity.Usuario;
import com.drsprog.compras_api.exception.UsuarioNotFoundException;
import com.drsprog.compras_api.repository.UsuarioRepository;
import com.drsprog.compras_api.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
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
