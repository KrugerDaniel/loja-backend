package com.loja.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loja.demo.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {}
