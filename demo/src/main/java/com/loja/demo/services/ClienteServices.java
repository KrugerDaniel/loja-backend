package com.loja.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.Cliente;
import com.loja.demo.repository.ClienteRepository;

@Service
public class ClienteServices {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();

        if (clientes.isEmpty()) {
            throw new ObjectNotFoundException("Não há clientes");
        }

        return clientes;
    }

    public Optional<Cliente> findById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            throw new ObjectNotFoundException("Cliente " + id + " não encontrado!");
        }

        return cliente;
    }

    public List<Cliente> findByName(String name) {
        List<Cliente> clientes = clienteRepository.findByName(name.toLowerCase());

        if (clientes.isEmpty()) {
            throw new ObjectNotFoundException("Não encontrados clientes com o nome: " + name);
        }
        
        return clientes;
    }

    public List<Cliente> findByEmail(String email) {
        List<Cliente> clientes = clienteRepository.findByEmail(email.toLowerCase());

        if (clientes.isEmpty()) {
            throw new ObjectNotFoundException("Não encontrado clientes com o e-mail: " + email);
        }
        
        return clientes;
    }

    public Cliente insertCliente(Cliente cliente) {
        cliente.setCd_cliente(null);
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Integer id) {
        clienteRepository.findById(id).orElseThrow(
            () -> new ObjectNotFoundException("Cliente " + id + " não encontrado!")
        );
        
        clienteRepository.deleteById(id);
    }

    public Cliente updateCliente(Integer id, Cliente clienteUpdated) {
        Cliente cliente = this.findCliente(id);
            
        cliente.setNm_cliente(clienteUpdated.getNm_cliente());
        cliente.setDs_email(clienteUpdated.getDs_email());
        cliente.setNr_telefone(clienteUpdated.getNr_telefone());
        cliente.setVl_limite_credito(clienteUpdated.getVl_limite_credito());

        return clienteRepository.save(cliente);
    }

    public Cliente updateClienteName(Integer id, String name) {
        Cliente cliente = this.findCliente(id);
            
        cliente.setNm_cliente(name);

        return clienteRepository.save(cliente);
    }

    public Cliente updateClienteTelefone(Integer id, String telefone) {
        Cliente cliente = this.findCliente(id);

        cliente.setNr_telefone(telefone);

        return clienteRepository.save(cliente);
    }

    public Cliente updateClienteEmail(Integer id, String email) {
        Cliente cliente = this.findCliente(id);

        cliente.setDs_email(email);

        return clienteRepository.save(cliente);
    }

    public Cliente updateClienteLimite(Integer id, Double limite) {
        Cliente cliente = this.findCliente(id);

        cliente.setVl_limite_credito(limite);

        return clienteRepository.save(cliente);
    }

    private Cliente findCliente(Integer id) {
        return clienteRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Cliente " + id + " não encontrado")
            );
    }
}
