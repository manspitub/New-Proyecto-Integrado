package com.salesianos.triana.proyecto.Restaurantelar.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePlatoDto {

    private String name;
    private String description;
    private boolean distinct;
    private String imageUrl;
    private double price;



}
