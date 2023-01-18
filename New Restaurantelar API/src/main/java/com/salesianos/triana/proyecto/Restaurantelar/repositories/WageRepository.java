package com.salesianos.triana.proyecto.Restaurantelar.repositories;

import com.salesianos.triana.proyecto.Restaurantelar.model.Wage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WageRepository extends JpaRepository<Wage, Long> {
}
