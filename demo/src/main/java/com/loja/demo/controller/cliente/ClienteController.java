package com.loja.demo.controller.cliente;

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

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        return clienteServices.findByName(name);
    }

    @GetMapping("/e-mail/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        return clienteServices.findByEmail(email);
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
    public ResponseEntity<?> updateCliente(@PathVariable Integer id, @Valid @RequestBody Cliente cliente) {
        return clienteServices.updateCliente(id, cliente);
    }

    @PutMapping("/{id}/name/{name}")
    public ResponseEntity<?> updateClienteName(@PathVariable Integer id, @PathVariable String name) {
        return clienteServices.updateClienteName(id, name);
    }

    @PutMapping("/{id}/telefone/{telefone}")
    public ResponseEntity<?> updateClienteTelefone(@PathVariable Integer id, @PathVariable String telefone) {
        return clienteServices.updateClienteTelefone(id, telefone);
    }

    @PutMapping("/{id}/e-mail/{email}")
    public ResponseEntity<?> updateClienteEmail(@PathVariable Integer id, @PathVariable String email) {
        return clienteServices.updateClienteEmail(id, email);
    }

    @PutMapping("/{id}/limite-credito/{limite}")
    public ResponseEntity<?> updateClienteLimite(@PathVariable Integer id, @PathVariable Double limite) {
        return clienteServices.updateClienteLimite(id, limite);
    }
}
