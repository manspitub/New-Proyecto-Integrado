package com.salesianos.triana.proyecto.Restaurantelar.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Table {

    private Long id;

    private Long numberId;

}
