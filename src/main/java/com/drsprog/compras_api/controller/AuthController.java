package com.drsprog.compras_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drsprog.compras_api.dto.AuthResponse;
import com.drsprog.compras_api.dto.LoginRequest;
import com.drsprog.compras_api.security.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        System.out.println("ENTRÓ AL LOGIN");

        // Autenticación
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getContrasena()
                )
        );

        // Cargar detalles del usuario
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        
        // Generar token
        String token = jwtService.generateToken(userDetails);

        // Responder con el token
        return ResponseEntity.ok(new AuthResponse(token));
    }
    
}
