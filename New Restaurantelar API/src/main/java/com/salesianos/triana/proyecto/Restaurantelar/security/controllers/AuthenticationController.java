package com.salesianos.triana.proyecto.Restaurantelar.security.controllers;

import com.salesianos.triana.proyecto.Restaurantelar.model.Worker;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.dto.*;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.role.UserRole;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private  final UserEntityService userEntityService;
    private final UserDtoConverter userDtoConverter;

    @PostMapping("/client")
    public ResponseEntity<UserDto> signUpClient(@RequestBody CreateUserClientDto newUSer){

        UserEntity userRegistered = userEntityService.saveClient(newUSer, UserRole.USER);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoConverter.convertUserEntityToUserClientDto(userRegistered));
    }

    @PostMapping("/worker")
    public ResponseEntity<WorkerDto> signUpWorker(@RequestBody CreateUserWorkerDto newWorker){

        Worker workerRegistered = userEntityService.saveWorker(newWorker, UserRole.WORKER);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoConverter.convertWorkerToWorkerDto(workerRegistered));
    }



}
