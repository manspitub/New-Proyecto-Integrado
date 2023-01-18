package com.salesianos.triana.proyecto.Restaurantelar.dto;

import com.salesianos.triana.proyecto.Restaurantelar.model.Plato;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateValorationDto {

    private String criticEmail;
    private Long platoId;
    private double rating;
    private String comments;

}
