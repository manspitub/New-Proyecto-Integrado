package com.salesianos.triana.proyecto.Restaurantelar.dto;

import com.salesianos.triana.proyecto.Restaurantelar.model.Plato;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoPlatoDto {

    private Plato plato;
    private int quantity;

}
