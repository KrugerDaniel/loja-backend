package com.loja.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loja.demo.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("SELECT pedido FROM Pedido pedido WHERE pedido.cliente.cd_cliente = :idCliente")
    List<Pedido> findByClienteId(@Param(value = "idCliente") Integer idCliente);
}