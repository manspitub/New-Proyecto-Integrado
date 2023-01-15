package com.salesianos.triana.proyecto.Restaurantelar.controller;

import com.salesianos.triana.proyecto.Restaurantelar.dto.CreatePlatoDto;
import com.salesianos.triana.proyecto.Restaurantelar.dto.PlatoDto;
import com.salesianos.triana.proyecto.Restaurantelar.dto.PlatoDtoConverter;
import com.salesianos.triana.proyecto.Restaurantelar.model.Plato;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import com.salesianos.triana.proyecto.Restaurantelar.service.PlatoService;
import com.salesianos.triana.proyecto.Restaurantelar.utils.PaginationLinks;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plato")
public class PlatoController {

    private final PlatoDtoConverter platoDtoConverter;
    private final PlatoService platoService;
    private final PaginationLinks paginationLinks;

    @PreAuthorize("hasAnyRole('ROLE_WORKER','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<PlatoDto> createPlato(@RequestBody CreatePlatoDto platoDto, @AuthenticationPrincipal UserEntity currentWorker){

        PlatoDto platoCreated = platoDtoConverter.platoToPlatoDto(platoService.save(platoDto, currentWorker));
        return ResponseEntity.status(HttpStatus.CREATED).body(platoCreated);
    }

    @GetMapping
    public ResponseEntity<Page<PlatoDto>> getAllPlatos(@PageableDefault(size = 10)Pageable pageable, HttpServletRequest request){
        Page<PlatoDto> platoDto = platoService.findAll(pageable).map(platoDtoConverter::platoToPlatoDto);

        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

        return ResponseEntity.ok().header("link",paginationLinks.createLinkHeader(platoDto, uri))
                .body(platoDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatoDto> getPlato(@PathVariable Long id){
        Plato plato = platoService.findOne(id);
        return ResponseEntity.ok(platoDtoConverter.platoToPlatoDto(plato));
    }

    @PreAuthorize("hasAnyRole('ROLE_WORKER','ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PlatoDto> updatePlato(@RequestBody CreatePlatoDto platoDto, @PathVariable Long id){
        Plato plato = platoService.edit(platoDto, id);
        return ResponseEntity.ok(platoDtoConverter.platoToPlatoDto(plato)) ;
    }

    @PreAuthorize("hasAnyRole('ROLE_WORKER','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlato(@PathVariable Long id){
        platoService.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Metodo para traer los platos más pedidos


}
