package com.salesianos.triana.proyecto.Restaurantelar.repositories;

import com.salesianos.triana.proyecto.Restaurantelar.model.PedidoPlato;
import com.salesianos.triana.proyecto.Restaurantelar.model.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoPlatoRepository extends JpaRepository<PedidoPlato, Long> {

    PedidoPlato findByPlato(Plato plato);

}
