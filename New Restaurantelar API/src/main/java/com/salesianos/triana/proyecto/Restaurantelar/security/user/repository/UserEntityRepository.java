package com.salesianos.triana.proyecto.Restaurantelar.security.user.repository;

import com.salesianos.triana.proyecto.Restaurantelar.model.Worker;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.dto.UserDto;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.role.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findFirstByUsername(String username);

    Optional<UserEntity> findFirstByFullNameIgnoreCase(String fullName);

    Optional<UserEntity> findFirstByEmail(String email);

    Page<UserEntity> findByRole(UserRole userRole, Pageable pageable);


  /*  @Query(value = """
            SELECT *
            FROM WORKER f
            WHERE f.user.role LIKE 'WORKER'
            """, nativeQuery = true)
    Page<Worker> findAllWorkers(Pageable pageable);

    @Query(value = """
            SELECT *
            FROM WORKER f
            WHERE f.user.role LIKE 'ADMIN'
            """, nativeQuery = true)
    Page<Worker> findAllAdmins(Pageable pageable);
*/


}
