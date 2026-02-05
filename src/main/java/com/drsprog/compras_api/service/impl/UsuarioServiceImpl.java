package com.drsprog.compras_api.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drsprog.compras_api.dto.ActualizarUsuarioRequest;
import com.drsprog.compras_api.dto.CambiarContrasenaRequest;
import com.drsprog.compras_api.dto.UsuarioRequest;
import com.drsprog.compras_api.dto.UsuarioResponse;
import com.drsprog.compras_api.entity.Usuario;
import com.drsprog.compras_api.exception.ContrasenaIncorrectaException;
import com.drsprog.compras_api.exception.UsuarioNotFoundException;
import com.drsprog.compras_api.repository.UsuarioRepository;
import com.drsprog.compras_api.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Crea el constructor con los campos finales
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        Usuario usuarioExistente = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .contrasena(passwordEncoder.encode(request.getContrasena()))
                .email(request.getEmail())
                .dni(request.getDni())
                .fechaNacimiento(request.getFechaNacimiento())
                .telefono(request.getTelefono())
                .build();

        Usuario guardado = usuarioRepository.save(usuarioExistente);

        return UsuarioResponse.builder()
                .id(guardado.getId())
                .nombre(guardado.getNombre())
                .apellido(guardado.getApellido())
                .email(guardado.getEmail())
                .dni(guardado.getDni())
                .fechaNacimiento(guardado.getFechaNacimiento())
                .telefono(guardado.getTelefono())
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
    public void eliminarUsuario(Long id) {
        Usuario usuarioExistente = obtenerUsuarioPorId(id);
        usuarioRepository.delete(usuarioExistente);
    }

    @Override
    @Transactional
    public UsuarioResponse actualizarDatosUsuario(Long id, ActualizarUsuarioRequest request) {

        Usuario usuarioExistente = obtenerUsuarioPorId(id);

        if (request.getNombre() != null)
            usuarioExistente.setNombre(request.getNombre());

        if (request.getApellido() != null)
            usuarioExistente.setApellido(request.getApellido());

        if (request.getEmail() != null)
            usuarioExistente.setEmail(request.getEmail());

        if (request.getTelefono() != null)
            usuarioExistente.setTelefono(request.getTelefono());

        if (request.getFechaNacimiento() != null)
            usuarioExistente.setFechaNacimiento(request.getFechaNacimiento());

        Usuario actualizado = usuarioRepository.save(usuarioExistente);
        return UsuarioResponse.builder()
                .id(actualizado.getId())
                .nombre(actualizado.getNombre())
                .apellido(actualizado.getApellido())
                .email(actualizado.getEmail())
                .telefono(actualizado.getTelefono())
                .fechaNacimiento(actualizado.getFechaNacimiento())
                .build();

    }

    @Override
    @Transactional
    public void cambiarContrasena(Long id, CambiarContrasenaRequest request) {
        Usuario usuario = obtenerUsuarioPorId(id);

        if (!passwordEncoder.matches(
                request.getContrasenaActual(),
                usuario.getContrasena())) {
            throw new ContrasenaIncorrectaException();
        }

        usuario.setContrasena(
                passwordEncoder.encode(request.getNuevaContrasena()));
    }
}
