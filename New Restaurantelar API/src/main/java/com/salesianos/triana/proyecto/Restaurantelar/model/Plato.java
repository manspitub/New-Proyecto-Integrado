package com.salesianos.triana.proyecto.Restaurantelar.model;

import com.salesianos.triana.proyecto.Restaurantelar.security.user.UserEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean dintinct;
    private String imageUrl;
    private double price;
    private double averageRating;

    @ManyToOne
    private UserEntity cookMan;

    @OneToMany(mappedBy = "plato", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<DishValoration> valorations = new ArrayList<>();

    @OneToMany(mappedBy = "plato", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Pedido> pedidos;

    public DishValoration getOneRating(Long id){
        DishValoration dishValoration = new DishValoration();
        for(int i=0; i<valorations.size(); i++){
            if(valorations.get(i).getId() == id){
                dishValoration = valorations.get(i);
            }
        }
        return dishValoration;    }

    public void addToValorations(DishValoration newValoration){
        valorations.add(newValoration);
    }

    public void updateValoration(DishValoration newValoration, Long valorationId){
        for (int i=0; i<valorations.size(); i++){
            if (valorations.get(i).getId() == valorationId){
                valorations.set(i, newValoration);
            }
        }
    }
    public double getAverageValoration(){
        double sum = 0;
        if (valorations.size() == 0){
            return 0;
        }
        for (int i=0; i<valorations.size(); i++){
            sum+= valorations.get(i).getRating();
        }
        return sum/valorations.size();
    }
    public void deleteValoration(Long valorationId){
        for (int i=0; i<valorations.size(); ++i){
            if (valorations.get(i).getId() == valorationId){
                valorations.remove(i);
            }
        }
    }







}
