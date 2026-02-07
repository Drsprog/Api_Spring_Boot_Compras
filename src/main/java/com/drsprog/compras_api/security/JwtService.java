package com.drsprog.compras_api.security;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.drsprog.compras_api.config.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import java.security.Key;


@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties jwtProperties;

    // Generar JWT
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // email
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + jwtProperties.getExpiration())
                )
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

     // Extraer email del JWT
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Validar token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    // Verificar expiración
    private boolean isTokenExpired(String token) {
        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // Obtener claims
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Clave de firma
    private Key getSigningKey() {
        System.out.println("JWT SECRET LENGTH = " + jwtProperties.getSecret().length());
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }
}
