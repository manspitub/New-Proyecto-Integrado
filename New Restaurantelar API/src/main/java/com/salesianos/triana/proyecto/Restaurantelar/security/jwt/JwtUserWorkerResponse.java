package com.salesianos.triana.proyecto.Restaurantelar.security.jwt;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtUserWorkerResponse {

    private String username, avatar, email, role, fullName;
    private boolean offWork;
    private LocalDateTime timeWorking;
    private String token;

}
