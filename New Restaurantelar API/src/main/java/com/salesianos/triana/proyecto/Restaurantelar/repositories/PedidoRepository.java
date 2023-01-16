package com.salesianos.triana.proyecto.Restaurantelar.repositories;

import com.salesianos.triana.proyecto.Restaurantelar.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = """
            SELECT *
            FROM PEDIDO p
            WHERE p.cancelled = false AND p.completed = false
            """, nativeQuery = true)
    Page<Pedido> findAllInProgress(Pageable pageable);

    Page<Pedido> findByClient_Id(Long id);
}
