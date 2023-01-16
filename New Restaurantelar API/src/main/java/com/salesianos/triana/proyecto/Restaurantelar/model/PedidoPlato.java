package com.salesianos.triana.proyecto.Restaurantelar.model;

import lombok.*;

import javax.persistence.*;

/**
 * Clase de asociación entre pedido y plato
 */

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoPlato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Plato plato;

    private int sum; // Esta variable nos servirá para tener el conteo de los platos más vendidos



}
