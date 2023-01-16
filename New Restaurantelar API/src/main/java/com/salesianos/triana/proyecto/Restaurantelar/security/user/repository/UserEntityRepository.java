package com.salesianos.triana.proyecto.Restaurantelar.security.user.repository;

import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findFirstByUsername(String username);

    Optional<UserEntity> findFirstByFullNameIgnoreCase(String fullName);

    Optional<UserEntity> findFirstByEmail(String email);

    List<UserEntity> findByRole(String role);


}
