package com.salesianos.triana.proyecto.Restaurantelar.controller;

import com.salesianos.triana.proyecto.Restaurantelar.dto.CreatePedidoDto;
import com.salesianos.triana.proyecto.Restaurantelar.dto.PedidoDto;
import com.salesianos.triana.proyecto.Restaurantelar.dto.PedidoDtoConverter;
import com.salesianos.triana.proyecto.Restaurantelar.model.Pedido;
import com.salesianos.triana.proyecto.Restaurantelar.repositories.PedidoRepository;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.role.UserRole;
import com.salesianos.triana.proyecto.Restaurantelar.service.PedidoService;
import com.salesianos.triana.proyecto.Restaurantelar.utils.PaginationLinks;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/pedido")
public class PedidoController {

    private final PedidoDtoConverter dtoConverter;
    private final PaginationLinks paginationLinks;
    private final PedidoService pedidoService;
    private final PedidoRepository pedidoRepository;


    @PostMapping
    public ResponseEntity<PedidoDto> makeOrder(CreatePedidoDto pedidoDto){
        PedidoDto pedido = dtoConverter.convertPedidoDto(pedidoService.order(pedidoDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<PedidoDto>> getPedidos(@PageableDefault(size = 10)Pageable pageable, HttpServletRequest request, @AuthenticationPrincipal UserEntity currentUSer){

        Page<PedidoDto> pedidoDto = pedidoService.findAllInProgress(pageable).map(dtoConverter::convertPedidoDto);
        Page<PedidoDto> pedidoDtoClient = pedidoRepository.findByClient_Id(currentUSer.getId()).map(dtoConverter::convertPedidoDto);


        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());


        if (currentUSer.getRole().equals(UserRole.ADMIN) || currentUSer.getRole().equals(UserRole.WORKER)){
            return ResponseEntity.ok().header("link",
                            paginationLinks.createLinkHeader(pedidoDto, uriBuilder))
                    .body(pedidoDto);        }
        else {
            return ResponseEntity.ok().header("link",
                            paginationLinks.createLinkHeader(pedidoDtoClient, uriBuilder))
                    .body(pedidoDtoClient);
        }

    }

    private ResponseEntity<PedidoDto> getPedidoId(@PathVariable Long pedidoId, @AuthenticationPrincipal UserEntity currentUser){
        Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
        if (pedido.isPresent()){
            Pedido pedidoFound = pedido.get();
            boolean isMine = pedidoFound.getClient().getId() == currentUser.getId();
            boolean isWorker = currentUser.getRole().equals(UserRole.WORKER);
            boolean isAdmin = currentUser.getRole().equals(UserRole.ADMIN);
            if (isMine || isAdmin || isWorker){
                return ResponseEntity.status(HttpStatus.OK).body(dtoConverter.convertPedidoDto(pedidoFound));
            } else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
    }





}
