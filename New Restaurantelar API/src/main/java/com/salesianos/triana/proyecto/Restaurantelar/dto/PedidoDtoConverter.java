package com.salesianos.triana.proyecto.Restaurantelar.dto;

import com.salesianos.triana.proyecto.Restaurantelar.model.Pedido;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoDtoConverter {

    public PedidoDto convertPedidoDto(Pedido pedido, String clientName, List<String> platos, double payment, int numTable){

        return PedidoDto.builder()
                .id(pedido.getId())
                .fullNameClient(clientName)
                .date(pedido.getDate())
                .type(pedido.getType())
                .dishRated(pedido.isDishRated())
                .completed(pedido.isCompleted())
                .cancelled(pedido.isCancelled())
                .usernameWaiter(pedido.getDeliveryMan().getName())
                .namePlato(platos)
                .paymentAmount(payment)
                .numTable(numTable)
                .build();
    }

}
