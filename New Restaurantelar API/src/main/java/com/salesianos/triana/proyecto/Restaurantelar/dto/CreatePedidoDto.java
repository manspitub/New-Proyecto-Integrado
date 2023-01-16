package com.salesianos.triana.proyecto.Restaurantelar.dto;

import com.salesianos.triana.proyecto.Restaurantelar.model.Plato;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePedidoDto {

    private String fullNameClient;
    private int type;
    private Long idWaiter;
    private List<Long> idsPlato;
    private int typePayment;
    private LocalDateTime dateReservation;
    private int numMesa;

}
