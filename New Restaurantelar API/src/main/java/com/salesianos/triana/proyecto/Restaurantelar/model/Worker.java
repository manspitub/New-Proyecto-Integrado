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
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UserEntity user;

    private String code;

    private String name;
    private boolean offWork;
    private LocalDateTime timeWorking;

}
