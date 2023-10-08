package com.loja.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loja.demo.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {}