package com.salesianos.triana.proyecto.Restaurantelar.dto;

import com.salesianos.triana.proyecto.Restaurantelar.model.Pedido;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import com.salesianos.triana.proyecto.Restaurantelar.service.PedidoService;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoDtoConverter {

    private final PedidoService service;



    public PedidoDto convertPedidoDto(Pedido pedido){



        return PedidoDto.builder()
                .id(pedido.getId())
                .fullNameClient(pedido.getClient().getFullName())
                .date(pedido.getDate())
                .type(pedido.getType())
                .dishRated(pedido.isDishRated())
                .completed(pedido.isCompleted())
                .cancelled(pedido.isCancelled())
                .usernameWaiter(pedido.getDeliveryMan().getName())
                .namePlato(pedido.namePlatosPedido())
                .paymentAmount(pedido.getTotalAmount())
                .numTable(pedido.getNumTable())
                .build();
    }


}
