package com.salesianos.triana.proyecto.Restaurantelar.security.user.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDtoUser {

    private String emailOrUsername;
    private String password;

}