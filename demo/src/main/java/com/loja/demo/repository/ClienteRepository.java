package com.loja.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loja.demo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT cliente FROM Cliente cliente WHERE nm_cliente LIKE %:nmCliente% ORDER BY cd_cliente")
    List<Cliente> findByName(@Param(value = "nmCliente") String nmCliente);

    @Query("SELECT cliente FROM Cliente cliente WHERE nm_cliente LIKE %:dsEmail% ORDER BY cd_cliente")
    List<Cliente> findByEmail(@Param(value = "dsEmail") String dsEmail);
}
