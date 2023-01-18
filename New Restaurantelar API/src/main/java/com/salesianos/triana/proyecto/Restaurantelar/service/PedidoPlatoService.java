package com.salesianos.triana.proyecto.Restaurantelar.service;

import com.salesianos.triana.proyecto.Restaurantelar.model.PedidoPlato;
import com.salesianos.triana.proyecto.Restaurantelar.model.Plato;
import com.salesianos.triana.proyecto.Restaurantelar.repositories.PedidoPlatoRepository;
import com.salesianos.triana.proyecto.Restaurantelar.repositories.PlatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoPlatoService {

    private final PedidoPlatoRepository pedidoPlatoRepository;

    private final PlatoRepository platoRepository;

    public List<PedidoPlato> getAllPedidosPlato(){
        return pedidoPlatoRepository.findAll();
    }

    public List<Plato> topTresPlatosMasPedidos(){
        List<PedidoPlato> pedidoPlatos = getAllPedidosPlato();
        Map<Long, Integer> map = new HashMap<>();

        for (PedidoPlato plato: pedidoPlatos) {
            if (map.containsKey(plato.getPlato().getId())){
                map.put(plato.getPlato().getId(), map.get(plato.getPlato().getId()) +1); //Si ese plato ya ha sido pedido se le suma 1
            }
            else {
                map.put(plato.getPlato().getId(), 1); //Si nunca ha sido pedido se le asigna uno
            }
        }

        List<Long> common = map
                .entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .map(e -> e.getKey())
                .collect(Collectors.toList()); // Ordenamos los ids de los platos por más comunes

        List<Plato> top3Platos = new ArrayList<>();
        for (Long id : common.subList(0, 3)){
            top3Platos.add(platoRepository.findById(id).get());
        }

        return top3Platos; // Devolvemos una lista de los 3 platos más pedidos
    }

}
