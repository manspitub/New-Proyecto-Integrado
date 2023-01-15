package com.salesianos.triana.proyecto.Restaurantelar.dto;

import com.salesianos.triana.proyecto.Restaurantelar.model.Plato;
import org.springframework.stereotype.Component;

@Component
public class PlatoDtoConverter {

    public PlatoDto platoToPlatoDto(Plato plato){

        return PlatoDto.builder()
                .id(plato.getId())
                .name(plato.getName())
                .description(plato.getDescription())
                .distinct(plato.isDintinct())
                .imageUrl(plato.getImageUrl())
                .price(plato.getPrice())
                .nameCookMan(plato.getCookMan().getFullName())
                .build();
    }

}
