package com.salesianos.triana.proyecto.Restaurantelar.security.user.dto;

import com.salesianos.triana.proyecto.Restaurantelar.model.Worker;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoConverter {

    public UserDto convertUserEntityToUserClientDto(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .avatar(user.getAvatar())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .role(user.getRole().name())
                .email(user.getEmail())
                .points(user.getPoints())
                .verified(user.isVerified())
                .address(user.getAddress())
                .build();
    }

    public WorkerDto convertWorkerToWorkerDto(Worker worker){
        return WorkerDto.builder()
                .id(worker.getUser().getId())
                .email(worker.getUser().getEmail())
                .avatar(worker.getUser().getAvatar())
                .username(worker.getUser().getUsername())
                .fullName(worker.getUser().getFullName())
                .role(worker.getUser().getRole().name())
                .offWork(worker.isOffWork())
                .timeWorking(worker.getTimeWorking())
                .code(worker.getCode())
                .idWorker(worker.getId())
                .salaryAmount(worker.getUser().getSalary().getAmount())
                .build();
    }

}
