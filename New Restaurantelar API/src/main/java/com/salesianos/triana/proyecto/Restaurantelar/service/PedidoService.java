package com.salesianos.triana.proyecto.Restaurantelar.service;

import com.salesianos.triana.proyecto.Restaurantelar.dto.CreatePedidoDto;
import com.salesianos.triana.proyecto.Restaurantelar.model.Payment;
import com.salesianos.triana.proyecto.Restaurantelar.model.Pedido;
import com.salesianos.triana.proyecto.Restaurantelar.model.Plato;
import com.salesianos.triana.proyecto.Restaurantelar.model.Reservation;
import com.salesianos.triana.proyecto.Restaurantelar.repositories.PaymentRepository;
import com.salesianos.triana.proyecto.Restaurantelar.repositories.PedidoRepository;
import com.salesianos.triana.proyecto.Restaurantelar.repositories.PlatoRepository;
import com.salesianos.triana.proyecto.Restaurantelar.repositories.ReservationRepository;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.repository.UserEntityRepository;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UserEntityRepository userEntityRepository;
    private final PlatoRepository platoRepository;
    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    public Pedido order(CreatePedidoDto pedidoDto){

        UserEntity user = new UserEntity();
        List<Plato> platosPedido = new ArrayList<>();
        List<Payment> payments = new ArrayList<>();
        Plato plato = new Plato();
        double sum = 0;

        for (int i = 0; i <pedidoDto.getIdsPlato().size(); i++) {
            platosPedido.add(i, platoRepository.findById(pedidoDto.getIdsPlato().get(i)).get());
        }

        for (Plato value : platosPedido) {
            sum+= value.getPrice();
        }

        if(userEntityRepository.findFirstByFullNameIgnoreCase(pedidoDto.getFullNameClient()).isPresent()){
            user = userEntityRepository.findFirstByUsername(pedidoDto.getFullNameClient()).get();
        } else{

            Payment newPayment = Payment.builder()
                    .payer(user)
                    .type(pedidoDto.getTypePayment())
                    .amount(sum)
                    .build();

            payments.add(newPayment);

            paymentRepository.save(newPayment);


             user = UserEntity.builder()
                    .username(pedidoDto.getFullNameClient().replace("\\s","")) //Por defecto el usuario se crea eliminando los whitespaces
                    .points(10)
                    .role(UserRole.USER)
                    .payments(payments)
                     .verified(false)
                     .build();
             userEntityRepository.save(user); //El usuario podrá loguearse pidiendo que le asignen una contraseña
        }



        Pedido newPedido = Pedido.builder()
                .type(pedidoDto.getType())
                .client(user)
                .type(pedidoDto.getType())
                .date(LocalDateTime.now())
                .build();

        pedidoRepository.save(newPedido);

        if (pedidoDto.getType() ==1){
            Reservation reservation = Reservation.builder()
                    .client(user)
                    .pedido(newPedido)
                    .date(pedidoDto.getDateReservation())
                    .numMesa(pedidoDto.getNumMesa())
                    .build();
            reservationRepository.save(reservation);
        }

        return pedidoRepository.save(newPedido);
    }

    public Page<Pedido> findAll(Pageable pageable){
        return pedidoRepository.findAll(pageable);
    }

    public Page<Pedido> findAllInProgress(Pageable pageable){
        return pedidoRepository.findAllInProgress(pageable);
    }

    public void setDishRated(long orderId) {
        Optional<Pedido> pedidoFound= pedidoRepository.findById(orderId);

        if (pedidoFound.isPresent()) {
            pedidoFound.get().setDishRated(true);
            pedidoRepository.save(pedidoFound.get());
        }
    }

    public void setCompleted(long orderId) {
        Optional<Pedido> pedidoFound= pedidoRepository.findById(orderId);

        if (pedidoFound.isPresent()) {
            pedidoFound.get().setCompleted(true);
            pedidoRepository.save(pedidoFound.get());
        }
    }




}
