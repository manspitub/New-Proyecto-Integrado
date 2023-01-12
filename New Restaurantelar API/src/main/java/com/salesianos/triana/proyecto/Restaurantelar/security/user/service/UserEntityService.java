package com.salesianos.triana.proyecto.Restaurantelar.security.user.service;

import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.dto.CreateUserClientDto;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.repository.UserEntityRepository;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.role.UserRole;
import lombok.RequiredArgsConstructor;
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

    private final UserEntityRepository repository;
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

    public Optional<UserEntity> findById(UUID id) {
        return repository.findById(id);
    }

}
