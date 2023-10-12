package com.loja.demo.controller;

import java.util.List;
import java.util.Optional;

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

import com.loja.demo.model.Cliente;
import com.loja.demo.repository.ClienteRepository;
import com.loja.demo.services.ClienteServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController extends GeneralController {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteServices clienteServices;

    @GetMapping()
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> clientes = clienteServices.findAll();

        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> findById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteServices.findById(id);

        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Cliente>> findByName(@PathVariable String name) {
        List<Cliente> clientes = clienteServices.findByName(name);

        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/e-mail/{email}")
    public ResponseEntity<List<Cliente>> findByEmail(@PathVariable String email) {
        List<Cliente> clientes = clienteRepository.findByEmail(email);

        return ResponseEntity.ok().body(clientes);
    }

    @PostMapping()
    public ResponseEntity<Cliente> insertCliente(@Valid @RequestBody Cliente cliente) {
        Cliente newCliente = clienteServices.insertCliente(cliente);
            
        return ResponseEntity.created(this.getUri(newCliente.getCd_cliente())).body(newCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        clienteServices.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @Valid @RequestBody Cliente cliente) {
        Cliente clienteUpdated = clienteServices.updateCliente(id, cliente);
            
        return ResponseEntity.created(this.getUri(id)).body(clienteUpdated);
    }

    @PutMapping("/{id}/name/{name}")
    public ResponseEntity<Cliente> updateClienteName(@PathVariable Integer id, @PathVariable String name) {
        Cliente clienteUpdated = clienteServices.updateClienteName(id, name);

        return ResponseEntity.created(this.getUri(id)).body(clienteUpdated);
    }

    @PutMapping("/{id}/telefone/{telefone}")
    public ResponseEntity<Cliente> updateClienteTelefone(@PathVariable Integer id, @PathVariable String telefone) {
        Cliente clienteUpdated = clienteServices.updateClienteTelefone(id, telefone);

        return ResponseEntity.created(this.getUri(id)).body(clienteUpdated);
    }

    @PutMapping("/{id}/e-mail/{email}")
    public ResponseEntity<Cliente> updateClienteEmail(@PathVariable Integer id, @PathVariable String email) {
        Cliente clienteUpdated = clienteServices.updateClienteEmail(id, email);

        return ResponseEntity.created(this.getUri(id)).body(clienteUpdated);
    }

    @PutMapping("/{id}/limite-credito/{limite}")
    public ResponseEntity<Cliente> updateClienteLimite(@PathVariable Integer id, @PathVariable Double limite) {
        Cliente clienteUpdated = clienteServices.updateClienteLimite(id, limite);

        return ResponseEntity.created(this.getUri(id)).body(clienteUpdated);
    }
}
