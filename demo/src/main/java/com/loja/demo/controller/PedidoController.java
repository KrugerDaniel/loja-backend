package com.loja.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loja.demo.services.PedidoServices;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoServices pedidoServices;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return pedidoServices.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return pedidoServices.findById(id);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> findPedidoByClienteId(@PathVariable Integer id) {
        return pedidoServices.findPedidoByClienteId(id);
    }

    @GetMapping("/date")
    public ResponseEntity<?> findPedidoByDate(@RequestBody Map<String, String> date) {
        return pedidoServices.findPedidoByDate(date);
    }

    @PostMapping()
    public ResponseEntity<?> insertPedido(@RequestBody Map<String, Object> pedido) {
        return pedidoServices.insertPedido(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable Integer id) {
        return pedidoServices.deletePedido(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePedido(@PathVariable Integer id, @Valid @RequestBody Map<String, Object> pedido) {
        return pedidoServices.updatePedido(id, pedido);
    }
}
