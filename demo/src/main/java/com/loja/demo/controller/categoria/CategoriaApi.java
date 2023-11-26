package com.loja.demo.controller.categoria;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.loja.demo.model.entity.categoria.Categoria;

import jakarta.validation.Valid;

public interface CategoriaApi {

    @GetMapping()
    public ResponseEntity<?> findAll();

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id);

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findCategoriaByName(@PathVariable String name);

    @PostMapping()
    public ResponseEntity<?> insertCategoria(@Valid @RequestBody Categoria categoria);

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria (@PathVariable Integer id);

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoria(@PathVariable Integer id, @Valid @RequestBody Categoria categoria);
}