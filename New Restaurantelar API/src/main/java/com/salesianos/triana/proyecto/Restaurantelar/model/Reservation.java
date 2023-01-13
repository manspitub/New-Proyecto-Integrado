package com.salesianos.triana.proyecto.Restaurantelar.model;

import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @OneToOne
    private Pedido pedido;

    @ManyToOne
    private UserEntity client;

    @ManyToOne
    private RestaurantTable table;
}
