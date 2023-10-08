package com.loja.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loja.demo.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {}