package com.salesianos.triana.proyecto.Restaurantelar.security.jwt;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtUserClientReponse {

    private String username, avatar, email, role, fullName;
    private double points;
    private boolean verified;
    private String token;
}
