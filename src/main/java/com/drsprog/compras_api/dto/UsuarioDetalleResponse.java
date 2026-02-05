package com.drsprog.compras_api.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDetalleResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String dni;
    private LocalDate fechaNacimiento;
    private String telefono;
}
