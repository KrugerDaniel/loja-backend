package com.loja.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.demo.model.Pedido;
import com.loja.demo.repository.PedidoRepository;
import com.loja.demo.services.PedidoServices;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoServices pedidoServices;

    @GetMapping()
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();

        return ResponseEntity.ok().body(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pedido>> findById(@PathVariable Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        return ResponseEntity.ok().body(pedido);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Pedido>> findPedidoByClienteId(@PathVariable Integer id) {
        List<Pedido> pedidos = pedidoServices.findPedidoByClienteId(id);

        return ResponseEntity.ok().body(pedidos);
    }
}
