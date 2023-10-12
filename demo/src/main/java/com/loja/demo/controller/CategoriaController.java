package com.loja.demo.controller;

import java.util.List;
import java.util.Optional;

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

import com.loja.demo.model.Categoria;
import com.loja.demo.repository.CategoriaRepository;
import com.loja.demo.services.CategoriaServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController extends GeneralController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaServices categoriaServices;

    @GetMapping()
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();

        return ResponseEntity.ok().body(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Categoria>> findById(@PathVariable Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        return ResponseEntity.ok().body(categoria);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Categoria>> findCategoriaName(@PathVariable String name) {
        List<Categoria> categorias = categoriaRepository.findByName(name);

        return ResponseEntity.ok().body(categorias);
    }

    @PostMapping()
    public ResponseEntity<Categoria> insertCategoria(@Valid @RequestBody Categoria categoria) {
        Categoria categoriaUpdated = categoriaServices.insertCategoria(categoria);

        return ResponseEntity.created(this.getUri(categoriaUpdated.getCd_categoria())).body(categoriaUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria (@PathVariable Integer id) {
        categoriaServices.deleteCategoria(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Integer id, @Valid @RequestBody Categoria categoria) {
        Categoria categoriaUpdated = categoriaServices.updateCategoria(id, categoria);

        return ResponseEntity.created(this.getUri(id)).body(categoriaUpdated);
    }
}
