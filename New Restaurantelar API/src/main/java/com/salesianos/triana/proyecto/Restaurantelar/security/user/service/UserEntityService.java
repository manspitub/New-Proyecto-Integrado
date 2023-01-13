package com.salesianos.triana.proyecto.Restaurantelar.security.user.service;

import com.salesianos.triana.proyecto.Restaurantelar.model.Worker;
import com.salesianos.triana.proyecto.Restaurantelar.repositories.WorkerRepository;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.dto.CreateUserClientDto;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.dto.CreateUserWorkerDto;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.repository.UserEntityRepository;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserEntityService implements UserDetailsService {

    @Autowired
    private final UserEntityRepository repository;

    private final WorkerRepository workerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {

        if (input.contains("@")) {
            return this.repository.findFirstByEmail(input)
                    .orElseThrow(() -> new UsernameNotFoundException(input + " no encontrado"));
        }

        return this.repository.findFirstByUsername(input)
                .orElseThrow(() -> new UsernameNotFoundException(input + " no encontrado"));
    }

    public UserEntity saveClient(CreateUserClientDto newUser, UserRole role){
        UserEntity user = UserEntity.builder()
                .password(passwordEncoder.encode(newUser.getPassword()))
                .avatar(newUser.getAvatar())
                .email(newUser.getEmail())
                .points(100)
                .username(newUser.getUsername())
                .fullName(newUser.getFullName())
                .role(role)
                .build();

        return repository.save(user);

    } // excepcion si existe usuario

    public Worker saveWorker(CreateUserWorkerDto newUser, UserRole role){

        UserEntity user = UserEntity.builder()
                .avatar(newUser.getAvatar())
                .email(newUser.getEmail())
                .username(newUser.getUsername())
                .fullName(newUser.getFullName())
                .role(role)
                .build();

        repository.save(user);

        Worker worker = Worker.builder()
                .code(passwordEncoder.encode(newUser.getCode()))
                .user(user)
                .timeWorking(newUser.getTimeWorking())
                .offWork(newUser.isOffWork())
                .build();

        return workerRepository.save(worker);

    } //excepcion si el ya hay un worker con el mismo código registrado



    public Optional<UserEntity> findById(UUID id) {
        return repository.findById(id);
    }

}
