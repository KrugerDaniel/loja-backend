package com.loja.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.Pedido;
import com.loja.demo.repository.PedidoRepository;

@Service
public class PedidoServices {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findPedidoByClienteId(Integer id) {
        List<Pedido> pedidos = pedidoRepository.findByClienteId(id);
        
        if (pedidos.isEmpty()) {
            throw new ObjectNotFoundException("Pedido " + id + " não encontrado!");
        };
        
        return pedidos;
    }
}
