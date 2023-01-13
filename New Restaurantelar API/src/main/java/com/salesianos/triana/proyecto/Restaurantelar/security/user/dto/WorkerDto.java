package com.salesianos.triana.proyecto.Restaurantelar.security.user.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {

    private UUID id;
    private String username, avatar, email, role, fullName;
    private boolean offWork;
    private LocalDateTime timeWorking;
}
