package com.loja.demo.services.itempedido;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.loja.demo.enums.HttpStatusCode;
import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.entity.itempedido.ItemPedido;
import com.loja.demo.model.entity.itempedido.ItemPedidoID;
import com.loja.demo.model.entity.pedido.Pedido;
import com.loja.demo.model.entity.produto.Produto;
import com.loja.demo.repository.itempedido.ItemPedidoRepository;
import com.loja.demo.services.GeneralService;
import com.loja.demo.services.pedido.PedidoServices;
import com.loja.demo.services.produto.ProdutoServices;

import jakarta.transaction.Transactional;

@Service
public class ItemPedidoServices extends GeneralService {
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoServices produtoServices;

    @Autowired
    private PedidoServices pedidoServices;

    public ResponseEntity<?> findAll() {
        try {
            List<ItemPedido> itens = this.itemPedidoRepository.findAll();
            
            if (itens.isEmpty()) {
                throw new ObjectNotFoundException("Não há itens de pedidos");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(itens);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findByProduto(Integer id) {
        try {
            List<ItemPedido> itens = this.itemPedidoRepository.findByProduto(id);

            if (itens.isEmpty()) {
                throw new ObjectNotFoundException("Não há itens com o produto " + id);
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(itens);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findByPedido(Integer id) {
        try {
            List<ItemPedido> itens = this.itemPedidoRepository.findByPedido(id);

            if (itens.isEmpty()) {
                throw new ObjectNotFoundException("Não há itens com o pedido " + id);
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(itens);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findByItemPedidoId(Integer idPedido, Integer idProduto) {
        try {
            ItemPedido item = this.findItemPedido(idPedido, idProduto);

            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(item);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    @Transactional
    public ResponseEntity<?> insertItemPedido(Map<String, Object> item) {
        try {
            if (!item.containsKey("produto") || !item.containsKey("pedido")) {
                throw new IllegalArgumentException("Precisa ser adiconado um pedido e um produto");
            }
            Produto produto = this.produtoServices.findPedido((Integer) item.get("produto"));
            Pedido pedido = this.pedidoServices.findPedido((Integer) item.get("pedido"));
            ItemPedidoID itemPedidoID = new ItemPedidoID(pedido, produto);

            ItemPedido newItem = new ItemPedido();
            Integer quantidade = (Integer) item.get("qt_produto");

            newItem.setId(itemPedidoID);
            newItem.setQt_produto(quantidade);
            newItem.setVl_produto(produto.getVl_produto());
            newItem.setVl_total(quantidade * produto.getVl_produto());

            itemPedidoRepository.save(newItem);

            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(itemPedidoID))
                .body(pedido);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).body(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteItemPedido(Integer idPedido, Integer idProduto) {
        try {
            ItemPedido item = this.findItemPedido(idPedido, idProduto);

            itemPedidoRepository.deleteById(item.getId());
            return ResponseEntity.status(HttpStatusCode.NO_CONTENT.getCode()).build();
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).body(e.getMessage());
        }
    }

    public ResponseEntity<?> updateItemPedido(Integer idPedido, Integer idProduto, Map<String, Object> item) {
        try {
            ItemPedido itemUpdated = this.findItemPedido(idPedido, idProduto);

            item.forEach((campo, valor) -> {
                switch (campo) {
                    case "qt_produto":
                        itemUpdated.setQt_produto((Integer) valor);
                        break;
                    case "vl_produto":
                        itemUpdated.setVl_produto((Double) valor);
                        break;
                    default:
                        break;
                }
            });
            itemUpdated.setVl_total(itemUpdated.getQt_produto() * itemUpdated.getVl_produto());

            itemPedidoRepository.save(itemUpdated);

            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(itemUpdated.getId()))
                .body(itemUpdated);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).body(e.getMessage());
        }
    }

    private ItemPedido findItemPedido(Integer idPedido, Integer idProduto) {
        ItemPedido item = itemPedidoRepository.findByItemPedidoId(idPedido, idProduto);

        if (item != null) {
            return item;
        }
        throw new ObjectNotFoundException("Não foi possível encontrar os itens");
    }

    private HttpHeaders getHeaders(ItemPedidoID id) {
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/pedido/{idPedido}/produto/{idProduto}")
            .buildAndExpand(id.getPedido().getNr_pedido(), id.getProduto().getCd_produto())
            .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return headers;
    }    
}
