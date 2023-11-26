package com.loja.demo.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loja.demo.model.entity.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("SELECT produto FROM Produto produto WHERE produto.nm_produto = :nmProduto")
    List<Produto> findByName(@Param(value = "nmProduto") String nmProduto);
}
