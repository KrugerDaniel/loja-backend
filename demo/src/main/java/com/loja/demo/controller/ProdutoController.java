package com.loja.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.demo.services.ProdutoServices;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    
    @Autowired
    private ProdutoServices produtoServices;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return produtoServices.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return produtoServices.findById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        return produtoServices.findByName(name);
    }

    @PostMapping()
    public ResponseEntity<?> insertProduto(@RequestBody Map<String, Object> produto) {
        return produtoServices.insertProduto(produto);
    }
}
