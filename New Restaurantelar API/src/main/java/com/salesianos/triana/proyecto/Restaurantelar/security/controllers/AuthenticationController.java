package com.salesianos.triana.proyecto.Restaurantelar.security.controllers;

import com.salesianos.triana.proyecto.Restaurantelar.model.Worker;
import com.salesianos.triana.proyecto.Restaurantelar.security.jwt.JwtProvider;
import com.salesianos.triana.proyecto.Restaurantelar.security.jwt.JwtUserClientReponse;
import com.salesianos.triana.proyecto.Restaurantelar.security.jwt.JwtUserWorkerResponse;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.auth.LoginDtoUser;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.auth.LoginDtoWorker;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.dto.*;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.role.UserRole;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserEntityService userEntityService;
    private final UserDtoConverter userDtoConverter;

    @PostMapping("/client")
    public ResponseEntity<UserDto> signUpClient(@RequestBody CreateUserClientDto newUSer){

        UserEntity userRegistered = userEntityService.saveClient(newUSer, UserRole.USER);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoConverter.convertUserEntityToUserClientDto(userRegistered));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/worker")
    public ResponseEntity<WorkerDto> signUpWorker(@RequestBody CreateUserWorkerDto newWorker, @AuthenticationPrincipal UserEntity user){

        Worker workerRegistered = userEntityService.saveWorker(newWorker, UserRole.WORKER);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoConverter.convertWorkerToWorkerDto(workerRegistered));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<WorkerDto> signUpADMIN(@RequestBody CreateUserWorkerDto newWorker, @AuthenticationPrincipal UserEntity user){

        Worker workerRegistered = userEntityService.saveWorker(newWorker, UserRole.ADMIN);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoConverter.convertWorkerToWorkerDto(workerRegistered));
    }


    @PostMapping("login/client")
    public ResponseEntity<?> loginUser(@RequestBody LoginDtoUser loginDtoUser){

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDtoUser.getEmailOrUsername(),
                                loginDtoUser.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserEntity user = (UserEntity) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertUserClientToJwtUserResponse(user, jwt));
    }

    private JwtUserClientReponse convertUserClientToJwtUserResponse(UserEntity user, String jwt){
        return JwtUserClientReponse.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .points(user.getPoints())
                .verified(user.isVerified())
                .role(user.getRole().name())
                .token(jwt)
                .build();
    }

    @PostMapping("login/worker")
    public ResponseEntity<?> loginWorker(@RequestBody LoginDtoWorker loginDtoWorker){



        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userEntityService.findWorkerByCode(loginDtoWorker.getCode()),
                                loginDtoWorker.getCode()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserEntity user = (UserEntity) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertUserWorkerToJwtUserWorkerResponse(user, jwt));
    }

    private JwtUserWorkerResponse convertUserWorkerToJwtUserWorkerResponse(UserEntity user, String jwt){
        return JwtUserWorkerResponse.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .role(user.getRole().name())
                .offWork(user.getWorker().isOffWork())
                .timeWorking(LocalDateTime.now()) //Señala la fecha y hora en la que comenzó a trabajar
                .token(jwt)
                .build();
    }

    @PostMapping("login/admin")
    public ResponseEntity<?> loginADMIN(@RequestBody LoginDtoWorker loginDtoWorker){

        if (userEntityService.findWorkerByCode(loginDtoWorker.getCode()).getRole().equals(UserRole.ADMIN)){
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userEntityService.findWorkerByCode(loginDtoWorker.getCode()),
                                    loginDtoWorker.getCode()
                            )
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            UserEntity user = (UserEntity) authentication.getPrincipal();

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(convertUserWorkerToJwtUserWorkerResponse(user, jwt));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


    }



    @GetMapping("/me")
    public ResponseEntity<?> getMe(@AuthenticationPrincipal UserEntity user) {

        if (user.getRole().equals(UserRole.USER)){
            return ResponseEntity.status(HttpStatus.OK).body(userDtoConverter.convertUserEntityToUserClientDto(user));
        }

        if (user.getRole().equals(UserRole.WORKER) || user.getRole().equals(UserRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.OK).body(userDtoConverter.convertWorkerToWorkerDto(user.getWorker()));
        }

        return ResponseEntity.ok().build();

    }



















}
