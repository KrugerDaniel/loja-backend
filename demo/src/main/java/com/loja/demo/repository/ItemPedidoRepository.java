package com.loja.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loja.demo.model.ItemPedido;
import com.loja.demo.model.ItemPedidoID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoID> {}
