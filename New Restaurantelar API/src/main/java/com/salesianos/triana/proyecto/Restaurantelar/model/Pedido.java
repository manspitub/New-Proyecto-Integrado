package com.salesianos.triana.proyecto.Restaurantelar.model;

import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserEntity client;

    private LocalDateTime date;

    private int type; // 0 = For here 1 = Reservation
    private boolean dishRated;
    private boolean completed;
    private boolean cancelled;

    @OneToOne
    private Worker deliveryMan;
    private int numTable;
    private double totalAmount;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE) //Al traernos un pedido nos traeremos a su vez la asociación pedidoPlato
    private List<PedidoPlato> pedidoPlatos = new ArrayList<>();

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Reservation reservation;


    public List<String> namePlatosPedido(){
        List<String> platosPedidos= new ArrayList<>();


        for (PedidoPlato pedidoPlato: this.pedidoPlatos){
            platosPedidos.add(pedidoPlato.getPlato().getName()+": "+pedidoPlato.getPlato().getPrice()+"\n");
        }
        return platosPedidos;
    }

    public void addUserAndWorker(UserEntity user, Worker worker){
        client = user;
        deliveryMan = worker;
        if (user.getPedidos() == null) {
            user.setPedidos(new ArrayList<>());
            user.getPedidos().add(this);
        } else {
            user.getPedidos().add(this);
        }

        if (worker.getUser().getPedidos() == null) {
            worker.getUser().setPedidos(new ArrayList<>());
            worker.getUser().getPedidos().add(this);
        } else {
            worker.getUser().getPedidos().add(this);
        }
    }



}
