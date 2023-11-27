package com.loja.demo.services.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.loja.demo.enums.HttpStatusCode;
import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.entity.cliente.Cliente;
import com.loja.demo.repository.cliente.ClienteRepository;
import com.loja.demo.services.GeneralService;

@Service
public class ClienteServices extends GeneralService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<?> findAll() {
        try {
            List<Cliente> clientes = clienteRepository.findAll();

            if (clientes.isEmpty()) {
                throw new ObjectNotFoundException("Não há clientes");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(clientes);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findById(Integer id) {
        try {
            Cliente cliente = this.findCliente(id);

            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(cliente);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findByName(String name) {
        try {
            List<Cliente> clientes = clienteRepository.findByName(name.toLowerCase());

            if (clientes.isEmpty()) {
                throw new ObjectNotFoundException("Não encontrados clientes com o nome: " + name);
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(clientes);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findByEmail(String email) {
        try {
            List<Cliente> clientes = clienteRepository.findByEmail(email.toLowerCase());

            if (clientes.isEmpty()) {
                throw new ObjectNotFoundException("Não encontrado clientes com o e-mail: " + email);
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(clientes);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> insertCliente(Cliente cliente) {
        try {
            cliente.setCd_cliente(null);
            Cliente newCliente = clienteRepository.save(cliente);

            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(newCliente.getCd_cliente()))
                .body(newCliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> deleteCliente(Integer id) {
        try {
            this.findCliente(id);
            
            clienteRepository.deleteById(id);
            return ResponseEntity.status(HttpStatusCode.NO_CONTENT.getCode()).build();
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> updateCliente(Integer id, Cliente clienteUpdated) {
        try {
            Cliente cliente = this.findCliente(id);
                
            cliente.setNm_cliente(clienteUpdated.getNm_cliente());
            cliente.setDs_email(clienteUpdated.getDs_email());
            cliente.setNr_telefone(clienteUpdated.getNr_telefone());
            cliente.setVl_limite_credito(clienteUpdated.getVl_limite_credito());

            cliente = clienteRepository.save(cliente);
            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(cliente);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> updateClienteName(Integer id, String name) {
        try {
            Cliente cliente = this.findCliente(id);
                
            cliente.setNm_cliente(name);

            cliente = clienteRepository.save(cliente);
            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(cliente);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> updateClienteTelefone(Integer id, String telefone) {
        try {
            Cliente cliente = this.findCliente(id);

            cliente.setNr_telefone(telefone);

            cliente = clienteRepository.save(cliente);
            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(cliente);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> updateClienteEmail(Integer id, String email) {
        try {
            Cliente cliente = this.findCliente(id);

            cliente.setDs_email(email);

            cliente = clienteRepository.save(cliente);
            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(cliente);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> updateClienteLimite(Integer id, Double limite) {
        try {
            Cliente cliente = this.findCliente(id);

            cliente.setVl_limite_credito(limite);

            cliente = clienteRepository.save(cliente);
            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(cliente);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public Cliente findCliente(Integer id) {
        return clienteRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Cliente " + id + " não encontrado")
            );
    }
}
