package com.loja.demo.controller.cliente;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.demo.controller.GeneralController;
import com.loja.demo.model.entity.cliente.Cliente;
import com.loja.demo.services.cliente.ClienteServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController extends GeneralController {

    @Autowired
    private ClienteServices clienteServices;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return clienteServices.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return clienteServices.findById(id);
    }

    @PostMapping()
    public ResponseEntity<?> insertCliente(@Valid @RequestBody Cliente cliente) {
        return clienteServices.insertCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Integer id) {
        return clienteServices.deleteCliente(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Integer id, @Valid @RequestBody Map<String, Object> cliente) {
        return clienteServices.updateCliente(id, cliente);
    }
}
