package com.salesianos.triana.proyecto.Restaurantelar.model;

import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UserEntity worker;

    private double amount;



}
