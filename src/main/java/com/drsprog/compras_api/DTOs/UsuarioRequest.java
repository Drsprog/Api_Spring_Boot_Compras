package com.drsprog.compras_api.DTOs;

import java.time.LocalDate;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {
    private String nombre;
    private String apellido;
    private String contrasena;
    private String email;
    private String dni;
    private LocalDate fechaNacimiento;
    private String telefono;
}
