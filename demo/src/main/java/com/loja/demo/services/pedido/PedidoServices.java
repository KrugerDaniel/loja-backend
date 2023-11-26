package com.loja.demo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.loja.demo.enums.HttpStatusCode;
import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.entity.cliente.Cliente;
import com.loja.demo.model.entity.pedido.Pedido;
import com.loja.demo.repository.pedido.PedidoRepository;

@Service
public class PedidoServices extends GeneralService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteServices clienteServices;

    private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

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

    public ResponseEntity<?> findPedidoByDate(Map<String, String> date) {
        try {
            Date data = format.parse(date.get("dt_emissao"));

            List<Pedido> pedidos = pedidoRepository.findPedidoByDate(data);

            if (pedidos.isEmpty()) {
                throw new ObjectNotFoundException("Pedido com data " + date + " não encontrado");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(pedidos);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (ParseException pe) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).body("Tipo de data inválida");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> insertPedido(Map<String, Object> pedido) {
        try {
            Pedido newPedido = new Pedido();
            pedido.forEach((campo, valor) -> {
                switch (campo) {
                    case "dt_emissao":
                        try {
                            Date data = format.parse((String) valor);
                            newPedido.setDt_emissao(data);
                            break;
                        } catch (ParseException e) {
                            throw new IllegalArgumentException("Formato de data inválido");
                        }
                    case "vl_total":
                        newPedido.setVl_total((Double) valor);
                        break;
                    case "cliente":
                        Integer idCliente = (Integer) valor;
                        Cliente cliente = clienteServices.findCliente(idCliente);
                        newPedido.setCliente(cliente);
                        break;
                }
            });

            pedidoRepository.save(newPedido);

            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(newPedido.getNr_pedido()))
                .body(newPedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).body(e.getMessage());
        }
    }

    public ResponseEntity<?> deletePedido(Integer id) {
        try {
            this.findPedido(id);
            
            pedidoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatusCode.NO_CONTENT.getCode()).build();
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> updatePedido(Integer id, Map<String, Object> pedidoUpdated) {
        try {
            Pedido pedido = this.findPedido(id);

            pedidoUpdated.forEach((campo, valor) -> {
                switch (campo) {
                    case "dt_emissao":
                        try {
                            Date data = format.parse((String) pedidoUpdated.get("dt_emissao"));
                            pedido.setDt_emissao(data);
                            break;
                        } catch (ParseException e) {
                            throw new IllegalArgumentException("Formato de data inválido");
                        }
                    case "vl_total":
                        pedido.setVl_total((Double) pedidoUpdated.get("vl_total"));
                        break;
                    case "cliente":
                        Integer idCliente = (Integer) pedidoUpdated.get("cliente");
                        Cliente cliente = clienteServices.findCliente(idCliente);
                        pedido.setCliente(cliente);
                        break;
                    default:
                        break;
                }
            });

            pedidoRepository.save(pedido);

            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(pedido);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).body(e.getMessage());
        }
    }

    public Pedido findPedido(Integer id) {
        return pedidoRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Pedido " + id + " não encontrado!")
            );
    }
}
