package com.loja.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loja.demo.model.ItemPedido;
import com.loja.demo.model.ItemPedidoID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoID> {

    @Query("SELECT item " +
           "FROM ItemPedito item " +
           "WHERE item.id.pedido.nr_pedido = :idProduto " +
           "ORDER BY item.id.pedido.nr_pedido")
    List<ItemPedido> findByProduto(@Param(value = "idProduto") Integer idProduto);
}
