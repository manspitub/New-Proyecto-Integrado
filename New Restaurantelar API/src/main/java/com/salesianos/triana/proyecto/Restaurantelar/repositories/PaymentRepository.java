package com.salesianos.triana.proyecto.Restaurantelar.repositories;

import com.salesianos.triana.proyecto.Restaurantelar.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
