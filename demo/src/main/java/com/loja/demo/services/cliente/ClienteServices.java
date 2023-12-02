package com.loja.demo.services.cliente;

import java.util.List;
import java.util.Map;

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

    public ResponseEntity<?> updateCliente(Integer id, Map<String, Object> clienteUpdated) {
        try {
            final Cliente cliente = this.findCliente(id);

            clienteUpdated.forEach((campo, valor) -> {
                switch (campo) {
                    case "nm_cliente":
                        cliente.setNm_cliente((String) valor);
                        break;
                    case "nr_telefone":
                        cliente.setNr_telefone((String) valor);
                        break;
                    case "ds_email":
                        cliente.setDs_email((String) valor);
                        break;
                    case "vl_limite_credito":
                        cliente.setVl_limite_credito((Double) valor);
                        break;
                }
            });

            clienteRepository.save(cliente);
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
