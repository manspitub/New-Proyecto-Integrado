package com.salesianos.triana.proyecto.Restaurantelar.repositories;

import com.salesianos.triana.proyecto.Restaurantelar.model.Plato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Long> {

    @Query(value = """
            SELECT *
            FROM PLATO p
            WHERE p.distinct = True
            """, nativeQuery = true)
    Page<Plato> findAllDistinct(Pageable pageable);

}
