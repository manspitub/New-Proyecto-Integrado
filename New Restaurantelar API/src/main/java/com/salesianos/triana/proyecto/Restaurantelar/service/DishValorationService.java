package com.salesianos.triana.proyecto.Restaurantelar.service;

import com.salesianos.triana.proyecto.Restaurantelar.dto.CreateValorationDto;
import com.salesianos.triana.proyecto.Restaurantelar.model.DishValoration;
import com.salesianos.triana.proyecto.Restaurantelar.model.Pedido;
import com.salesianos.triana.proyecto.Restaurantelar.model.Plato;
import com.salesianos.triana.proyecto.Restaurantelar.repositories.DishValorationRepository;
import com.salesianos.triana.proyecto.Restaurantelar.repositories.PlatoRepository;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.repository.UserEntityRepository;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DishValorationService {

    private final PlatoRepository platoRepository;
    private final DishValorationRepository dishValorationRepository;
    private final UserEntityRepository userEntityRepository;


    public Page<DishValoration> findAllValidated(Pageable pageable){
        return dishValorationRepository.findAllValidated(pageable);
    }

    public Page<DishValoration> findAll(Pageable pageable){
        return dishValorationRepository.findAll(pageable);
    }

    public DishValoration findOne(Long id) {
        Optional<DishValoration> wantedValoration = dishValorationRepository.findById(id);

        if (wantedValoration.isPresent()){
            return dishValorationRepository.findById(id).get();
        } else throw null;
    }

    public List<DishValoration> getRatingsByClient(UUID clientId){
        return dishValorationRepository.findByUser_id(clientId);
    }

    public DishValoration valorate(CreateValorationDto valoration, UserEntity user){

        UserEntity critic;

        if (user.getRole().equals(UserRole.USER)){
            critic = user;

        } else {
            critic = userEntityRepository.findFirstByEmail(valoration.getCriticEmail()).get(); //Un WORKER podrá valorar un plato como un user en cuestión
        }

        Plato plato = platoRepository.findById(valoration.getPlatoId()).get();


        DishValoration dishValoration = DishValoration.builder()
                .comments(valoration.getComments())
                .plato(plato)
                .rating(valoration.getRating())
                .user(critic)
                .build();


        userEntityRepository.save(critic);
        return dishValorationRepository.save(dishValoration);


    }

    public void setValidated(Long valorationId) {
        Optional<DishValoration> valorationFound= dishValorationRepository.findById(valorationId);

        if (valorationFound.isPresent()) {
            valorationFound.get().setValidate(true);
            dishValorationRepository.save(valorationFound.get());
        }
    }

}
