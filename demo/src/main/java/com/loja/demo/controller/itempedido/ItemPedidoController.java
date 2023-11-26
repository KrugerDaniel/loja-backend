package com.loja.demo.controller.itempedido;

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
import com.loja.demo.services.ItemPedidoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/item-pedido")
public class ItemPedidoController extends GeneralController {
    
    @Autowired
    private ItemPedidoServices itemPedidoServices;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return itemPedidoServices.findAll();
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<?> findByProduto(@PathVariable Integer id) {
        return itemPedidoServices.findByProduto(id);
    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity<?> findByPedido(@PathVariable Integer id) {
        return itemPedidoServices.findByPedido(id);
    }

    @GetMapping("/pedido/{idPedido}/produto/{idProduto}")
    public ResponseEntity<?> findByItemPedidoId(@PathVariable Integer idPedido, @PathVariable Integer idProduto) {
        return itemPedidoServices.findByItemPedidoId(idPedido, idProduto);
    }

    @PostMapping()
    public ResponseEntity<?> insertItemPedido(@Valid @RequestBody Map<String, Object> item) {
        return itemPedidoServices.insertItemPedido(item);
    }

    @DeleteMapping("/pedido/{idPedido}/produto/{idProduto}")
    public ResponseEntity<?> deleteItemPedido(@PathVariable Integer idPedido, @PathVariable Integer idProduto) {
        return itemPedidoServices.deleteItemPedido(idPedido, idProduto);
    }

    @PutMapping("/pedido/{idPedido}/produto/{idProduto}")
    public ResponseEntity<?> updateItemPedido(@PathVariable Integer idPedido, @PathVariable Integer idProduto,
                                              @Valid @RequestBody Map<String, Object> item) {
        return itemPedidoServices.updateItemPedido(idPedido, idProduto, item);
    }
}
