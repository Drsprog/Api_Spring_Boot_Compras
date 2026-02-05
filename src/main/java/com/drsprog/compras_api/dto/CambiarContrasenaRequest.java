package com.drsprog.compras_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CambiarContrasenaRequest {
    private String contrasenaActual;
    private String nuevaContrasena;
}
