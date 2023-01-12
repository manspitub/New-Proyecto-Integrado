package com.salesianos.triana.proyecto.Restaurantelar.security.user.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID id;
    private String username, avatar, email, role;



}
