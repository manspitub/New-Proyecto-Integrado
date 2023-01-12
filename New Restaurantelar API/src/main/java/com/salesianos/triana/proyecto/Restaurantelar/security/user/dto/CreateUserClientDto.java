package com.salesianos.triana.proyecto.Restaurantelar.security.user.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserClientDto {

    @NotNull
    @NotEmpty
    private String username;

    @NotEmpty
    @NotNull
    private String fullName;

    @Email
    @NotEmpty
    private String email;

    @Builder.Default
    private String avatar = ""; //Se pone una imagen por defecto para los usuarios que no elijan imagen

    private double points;

    private String password; //Hacer validacion contraseña segura
    private String password2;


}
