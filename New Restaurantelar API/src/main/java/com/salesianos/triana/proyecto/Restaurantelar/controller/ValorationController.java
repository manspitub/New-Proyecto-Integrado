package com.salesianos.triana.proyecto.Restaurantelar.controller;

import com.salesianos.triana.proyecto.Restaurantelar.dto.CreateValorationDto;
import com.salesianos.triana.proyecto.Restaurantelar.dto.ValorationDto;
import com.salesianos.triana.proyecto.Restaurantelar.dto.ValorationDtoConverter;
import com.salesianos.triana.proyecto.Restaurantelar.model.DishValoration;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import com.salesianos.triana.proyecto.Restaurantelar.service.DishValorationService;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/valoration")
@RequiredArgsConstructor
public class ValorationController {

    private final ValorationDtoConverter dtoConverter;
    private final DishValorationService dishValorationService;
    private final PaginationLinks paginationLinks;

    @GetMapping("/{clientId}")
    @PreAuthorize("hasAnyRole('ADMIN','WORKER')")
    public ResponseEntity<List<ValorationDto>> getAllUserValorations(@PageableDefault(size = 10)Pageable pageable, HttpServletRequest request, @PathVariable UUID clientId){
        List<ValorationDto> valorationDto = dishValorationService.getRatingsByClient(clientId).stream().map(dtoConverter::valorationToValorationDto).toList();
        return ResponseEntity.status(HttpStatus.OK).body(valorationDto);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ValorationDto>> getMyUserValorations(@PageableDefault(size = 10)Pageable pageable, HttpServletRequest request, @AuthenticationPrincipal UserEntity userEntity){
        List<ValorationDto> valorationDto = dishValorationService.getRatingsByClient(userEntity.getId()).stream().map(dtoConverter::valorationToValorationDto).toList();
        if (valorationDto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(valorationDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','WORKER')")
    @GetMapping
    public ResponseEntity<Page<ValorationDto>> getAllValorations(@PageableDefault(size=10, page=0)Pageable pageable, HttpServletRequest request) {

        Page<ValorationDto> valorationDto = dishValorationService.findAll(pageable).map(dtoConverter::valorationToValorationDto);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

        return ResponseEntity.ok().header("link",
                        paginationLinks.createLinkHeader(valorationDto, uriBuilder))
                .body(valorationDto);

    }

    @PreAuthorize("hasAnyRole('ADMIN','WORKER')")
    @GetMapping("/validated")
    public ResponseEntity<Page<ValorationDto>> getAllValorationsValidated(@PageableDefault(size=10, page=0)Pageable pageable, HttpServletRequest request) {

        Page<ValorationDto> valorationDto = dishValorationService.findAllValidated(pageable).map(dtoConverter::valorationToValorationDto);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

        return ResponseEntity.ok().header("link",
                        paginationLinks.createLinkHeader(valorationDto, uriBuilder))
                .body(valorationDto);

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<ValorationDto> createValoration(@RequestBody CreateValorationDto valorationDto, @AuthenticationPrincipal UserEntity user) {

        ValorationDto valoration = dtoConverter.valorationToValorationDto(dishValorationService.valorate(valorationDto, user));

        return ResponseEntity.status(HttpStatus.CREATED).body(valoration);

    }

    @PostMapping("/validated/{valorationId}")
    @PreAuthorize("hasAnyRole('ROLE_WORKER','ROLE_ADMIN')")
    public void validate(@PathVariable Long valorationId){
        dishValorationService.setValidated(valorationId);
    }



}
