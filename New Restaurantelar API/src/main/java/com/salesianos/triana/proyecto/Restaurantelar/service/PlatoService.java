package com.salesianos.triana.proyecto.Restaurantelar.service;

import com.salesianos.triana.proyecto.Restaurantelar.dto.CreatePlatoDto;
import com.salesianos.triana.proyecto.Restaurantelar.model.Plato;
import com.salesianos.triana.proyecto.Restaurantelar.repositories.PlatoRepository;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlatoService {

    private final PlatoRepository repository;

    public Plato save(CreatePlatoDto plato, UserEntity user){


        Plato newPlato = Plato.builder()
                .name(plato.getName())
                .description(plato.getDescription())
                .dintinct(plato.isDistinct())
                .imageUrl(plato.getImageUrl())
                .price(plato.getPrice())
                .build();
        newPlato.setCookMan(user);

        return repository.save(newPlato);
    }

    public Plato findOne(Long id){
        Optional<Plato> findPlato = repository.findById(id);

        if (findPlato.isPresent()){
            return repository.findById(id).get();
        }else throw null; //excepcion si no se encuentra
    }

    public Page<Plato> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Plato> findAllDistinct(Pageable pageable) {
        return repository.findAllDistinct(pageable);
    }

    public Plato edit(CreatePlatoDto plato, Long id, UserEntity user){
        Optional<Plato> findPlato = repository.findById(id);

        if (findPlato.isPresent()){
            Plato foundPlato = findPlato.get();

            foundPlato = Plato.builder()
                    .id(id)
                    .name(plato.getName())
                    .description(plato.getDescription())
                    .dintinct(plato.isDistinct())
                    .price(plato.getPrice())
                    .imageUrl(plato.getImageUrl())
                    .build();
            foundPlato.setCookMan(user);

            return repository.save(foundPlato);
        } else throw null; // excepcion si no se encuentra
    }

    public void remove (Long id){
        repository.deleteById(id);
    }

}
