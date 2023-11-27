package com.loja.demo.repository.itempedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loja.demo.model.entity.itempedido.ItemPedido;
import com.loja.demo.model.entity.itempedido.ItemPedidoID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoID> {

    @Query("SELECT item " +
           "FROM ItemPedido item " +
           "WHERE item.id.produto.cd_produto = :idProduto " +
           "ORDER BY item.id.produto.cd_produto")
    List<ItemPedido> findByProduto(@Param(value = "idProduto") Integer idProduto);

    @Query("SELECT item " +
           "FROM ItemPedido item " +
           "WHERE item.id.pedido.nr_pedido = :idPedido " +
           "ORDER BY item.id.pedido.nr_pedido")
    List<ItemPedido> findByPedido(@Param(value = "idPedido") Integer idPedido);

    @Query(
        "SELECT item " +
        "FROM ItemPedido item " +
        "WHERE item.id.pedido.nr_pedido = :idPedido " +
        "  AND item.id.produto.cd_produto = :idProduto " +
        "ORDER BY item.id.pedido.nr_pedido, item.id.produto.cd_produto"
    )
    ItemPedido findByItemPedidoId(@Param(value = "idPedido") Integer idPedido,
                                  @Param(value = "idProduto") Integer idProduto);
}
