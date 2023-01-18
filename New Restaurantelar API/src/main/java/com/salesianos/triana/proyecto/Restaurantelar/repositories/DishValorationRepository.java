package com.salesianos.triana.proyecto.Restaurantelar.repositories;

import com.salesianos.triana.proyecto.Restaurantelar.model.DishValoration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DishValorationRepository extends JpaRepository<DishValoration, Long> {
    List<DishValoration> findByUser_id(UUID clientId);

    @Query(value = """
            SELECT *
            FROM DISHVALORATION dv
            WHERE dv.validate = True
            """, nativeQuery = true)
    Page<DishValoration> findAllValidated(Pageable pageable);
}
