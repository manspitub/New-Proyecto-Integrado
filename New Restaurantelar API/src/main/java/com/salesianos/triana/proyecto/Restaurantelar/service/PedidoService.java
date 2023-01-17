package com.salesianos.triana.proyecto.Restaurantelar.service;

import com.salesianos.triana.proyecto.Restaurantelar.dto.CreatePedidoDto;
import com.salesianos.triana.proyecto.Restaurantelar.model.*;
import com.salesianos.triana.proyecto.Restaurantelar.repositories.*;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.repository.UserEntityRepository;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.role.UserRole;
import com.salesianos.triana.proyecto.Restaurantelar.security.user.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UserEntityRepository userEntityRepository;
    private final WorkerRepository workerRepository;
    private final PlatoRepository platoRepository;
    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final PedidoPlatoRepository pedidoPlatoRepository;

    public Page<Pedido> findALl(Pageable pageable){
        return pedidoRepository.findAll(pageable);
    }



    public double getPriceAmount(CreatePedidoDto pedidoDto){
        List<Plato> platosPedido = new ArrayList<>();
        double sum = 0;
        for (int i = 0; i <pedidoDto.getIdsPlato().size(); i++) {
            platosPedido.add(i, platoRepository.findById(pedidoDto.getIdsPlato().get(i)).get());
        }
        for (Plato value : platosPedido) {
            sum+= value.getPrice();
        }
        return sum;
    }

    public List<String> namePlatosPedidos(CreatePedidoDto pedidoDto){
        List<Plato> platosPedidos = new ArrayList<>();
        List<String> platosName = new ArrayList<>();

        for (int i = 0; i <pedidoDto.getIdsPlato().size(); i++) {
            platosPedidos.add(i, platoRepository.findById(pedidoDto.getIdsPlato().get(i)).get());
        }
        for (Plato plato: platosPedidos){
            platosName.add("Nombre Plato:"+ plato.getName());
            platosName.add("Id Plato:"+ plato.getId());
        }
        return platosName;
    }

    public Pedido order(CreatePedidoDto pedidoDto){

        UserEntity user;
        List<Payment> payments = new ArrayList<>();
        Worker worker;
        worker = workerRepository.findById(pedidoDto.getIdWaiter()).get();
        List<Plato> platosPedido = new ArrayList<>();

        for (int i = 0; i <pedidoDto.getIdsPlato().size(); i++) {
            platosPedido.add(i, platoRepository.findById(pedidoDto.getIdsPlato().get(i)).get());
        }



        if(userEntityRepository.findFirstByFullNameIgnoreCase(pedidoDto.getFullNameClient()).isPresent()){
            user = userEntityRepository.findFirstByUsername(pedidoDto.getFullNameClient()).get();
        } else{




             user = UserEntity.builder()
                     .fullName(pedidoDto.getFullNameClient())
                    .username(pedidoDto.getFullNameClient().replace("\\s","")) //Por defecto el usuario se crea eliminando los whitespaces
                    .points(10)
                    .role(UserRole.USER)
                     .verified(false)
                     .build();
             userEntityRepository.save(user); //El usuario podrá loguearse pidiendo que le asignen una contraseña


        }

        Payment newPayment = Payment.builder()
                .payer(user)
                .type(pedidoDto.getTypePayment())
                .amount(getPriceAmount(pedidoDto))
                .build();

        payments.add(newPayment);

        paymentRepository.save(newPayment);



        Pedido newPedido = Pedido.builder()
                .type(pedidoDto.getType())
                .deliveryMan(worker)
                .client(user)
                .date(LocalDateTime.now())
                .numTable(pedidoDto.getNumMesa())
                .totalAmount(getPriceAmount(pedidoDto))
                .build();

        System.out.println(newPedido.getClient().toString());


        if (pedidoDto.getType() ==1){
            Reservation reservation = Reservation.builder()
                    .client(user)
                    .pedido(newPedido)
                    .date(pedidoDto.getDateReservation())
                    .numMesa(pedidoDto.getNumMesa())
                    .build();
            reservationRepository.save(reservation);
        }

        List<PedidoPlato> dishOrders = platosPedido.stream()
                .map(obj -> new PedidoPlato(platoRepository.findById(obj.getId()).get(), +1))
                        .collect(Collectors.toList());

        dishOrders.stream().forEach(dishOrder -> dishOrder.setPedido(newPedido));
        newPedido.setPedidoPlatos(dishOrders);

        return pedidoRepository.save(newPedido);

    }

    public Page<Pedido> findAll(Pageable pageable){
        return pedidoRepository.findAll(pageable);
    }

    public Page<Pedido> findAllInProgress(Pageable pageable){
        return pedidoRepository.findAllInProgress(pageable);
    }

    public void setDishRated(Long orderId) {
        Optional<Pedido> pedidoFound= pedidoRepository.findById(orderId);

        if (pedidoFound.isPresent()) {
            pedidoFound.get().setDishRated(true);
            pedidoRepository.save(pedidoFound.get());
        }
    }

    public void setCompleted(Long orderId) {
        Optional<Pedido> pedidoFound= pedidoRepository.findById(orderId);

        if (pedidoFound.isPresent()) {
            pedidoFound.get().setCompleted(true);
            pedidoRepository.save(pedidoFound.get());
        }
    }

    public void deletePedido(Long orderId){
        pedidoRepository.deleteById(orderId);
    }

    public Pedido edit(Long id, CreatePedidoDto pedidoDto){
        Optional<Pedido> pedidoFound = pedidoRepository.findById(id);
        Optional<Worker> workerFound = workerRepository.findById(pedidoDto.getIdWaiter());


        if (pedidoFound.isPresent()){
            Pedido pedido = pedidoFound.get();

            UserEntity user = new UserEntity();
            List<Plato> platosPedido = new ArrayList<>();
            List<Payment> payments = new ArrayList<>();
            Worker worker;
            worker = workerRepository.findById(pedidoDto.getIdWaiter()).get();
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



             pedido = Pedido.builder()
                    .type(pedidoDto.getType())
                    .client(user)
                    .type(pedidoDto.getType())
                    .date(LocalDateTime.now())
                    .deliveryMan(worker)
                    .build();

            pedidoRepository.save(pedido);

            if (pedidoDto.getType() ==1){
                Reservation reservation = Reservation.builder()
                        .client(user)
                        .pedido(pedido)
                        .date(pedidoDto.getDateReservation())
                        .numMesa(pedidoDto.getNumMesa())
                        .build();
                reservationRepository.save(reservation);
            }
            return pedidoRepository.save(pedido);
        } else throw null;

    }




}
