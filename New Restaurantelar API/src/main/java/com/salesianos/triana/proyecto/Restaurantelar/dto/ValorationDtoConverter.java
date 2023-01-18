package com.salesianos.triana.proyecto.Restaurantelar.dto;

import com.salesianos.triana.proyecto.Restaurantelar.model.DishValoration;
import org.springframework.stereotype.Component;

@Component
public class ValorationDtoConverter {

    public ValorationDto valorationToValorationDto(DishValoration valoration){

        return ValorationDto.builder()
                .id(valoration.getId())
                .nombrePlato(valoration.getPlato().getName())
                .nombreUser(valoration.getUser().getFullName())
                .comments(valoration.getComments())
                .rating(valoration.getRating())
                .validated(valoration.isValidate())
                .build();

    }

}
