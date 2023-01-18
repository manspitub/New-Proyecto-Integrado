package com.salesianos.triana.proyecto.Restaurantelar.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValorationDto {

    private Long id;
    private String nombrePlato;
    private String nombreUser;
    private double rating;
    private String comments;
    private boolean validated;

}
