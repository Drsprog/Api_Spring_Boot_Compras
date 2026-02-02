package com.drsprog.compras_api.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgregarProductoRequest {

    private Long articuloId;
    private Integer cantidad;

}
