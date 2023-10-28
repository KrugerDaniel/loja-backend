package com.loja.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.loja.demo.enums.HttpStatusCode;
import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.Pedido;
import com.loja.demo.repository.PedidoRepository;

@Service
public class PedidoServices {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public ResponseEntity<?> findAll() {
        try {
            List<Pedido> pedidos = pedidoRepository.findAll();

            if (pedidos.isEmpty()) {
                throw new ObjectNotFoundException("Não há clientes");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(pedidos);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findById(Integer id) {
        try {
            Pedido pedido = this.findPedido(id);

            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(pedido);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findPedidoByClienteId(Integer id) {
        try {
            List<Pedido> pedidos = pedidoRepository.findByClienteId(id);
            
            if (pedidos.isEmpty()) {
                throw new ObjectNotFoundException("Não há clientes");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(pedidos);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findPedidoByDate(Date date) {
        try {
            List<Pedido> pedidos = pedidoRepository.findPedidoByDate(date);

            if (pedidos.isEmpty()) {
                throw new ObjectNotFoundException("Pedido com data " + date + " não encontrado");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(pedidos);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    private Pedido findPedido(Integer id) {
        return pedidoRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Pedido " + id + " não encontrado!")
            );
    }
}
