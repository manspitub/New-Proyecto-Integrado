package com.salesianos.triana.proyecto.Restaurantelar.model;

import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishValoration {

    private Long id;

    @ManyToOne
    private Plato plato;

    private UserEntity user;

    @Max(5)
    private double rating;

    private String comments;

    public DishValoration(Plato plato, UserEntity user, double rating) {
        this.plato = plato;
        this.user = user;
        this.rating = rating;
    }
}
