package com.loja.demo.controller.produto;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.demo.services.produto.ProdutoServices;

import jakarta.validation.Valid;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable Integer id) {
        return produtoServices.deleteProduto(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduto(@PathVariable Integer id, @Valid @RequestBody Map<String, Object> produto) {
        return produtoServices.updateProduto(id, produto);
    }
}
