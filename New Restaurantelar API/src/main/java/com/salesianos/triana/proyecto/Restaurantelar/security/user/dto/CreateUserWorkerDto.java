package com.salesianos.triana.proyecto.Restaurantelar.security.user.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserWorkerDto {

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

    private String  code; //Hacer validacion contraseña segura
    private String code2;

    private boolean offWork;
    private LocalDateTime timeWorking;

    private double salaryAmount;


}
