package com.salesianos.triana.proyecto.Restaurantelar.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDto {

    private Long id;
    private String fullNameClient;
    private LocalDateTime date;
    private int type, numTable;
    private boolean dishRated;
    private boolean completed;
    private boolean cancelled;
    private String usernameWaiter;
    private List<String> namePlato;
    private double paymentAmount;

}
