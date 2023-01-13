package com.salesianos.triana.proyecto.Restaurantelar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishValoration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Plato plato;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
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
