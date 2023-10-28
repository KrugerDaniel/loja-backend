package com.loja.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loja.demo.services.PedidoServices;

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
    public ResponseEntity<?> findPedidoByDate(@RequestParam Date date) {
        return pedidoServices.findPedidoByDate(date);
    }
}
