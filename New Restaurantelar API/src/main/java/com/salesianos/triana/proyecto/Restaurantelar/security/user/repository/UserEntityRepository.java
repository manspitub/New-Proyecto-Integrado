package com.salesianos.triana.proyecto.Restaurantelar.security.user.repository;

import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findFirstByUsername(String username);

    Optional<UserEntity> findFirstByEmail(String email);

    List<UserEntity> findByRole(String role);


}
