package com.salesianos.triana.proyecto.Restaurantelar;

import com.salesianos.triana.proyecto.Restaurantelar.security.user.dto.CreateUserWorkerDto;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.role.UserRole;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class InitData {

    private final UserEntityService userService;


    @PostConstruct
    public void data(){
        CreateUserWorkerDto admin = CreateUserWorkerDto.builder().username("manspitub").email("manspitub@hotmail.com")
                .code("201003").code2("201003").fullName("manuel spinola").build();
        userService.saveWorker(admin, UserRole.ADMIN);
    }

}
