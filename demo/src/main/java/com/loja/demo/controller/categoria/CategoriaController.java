package com.loja.demo.controller.categoria;

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

import com.loja.demo.controller.GeneralController;
import com.loja.demo.model.entity.categoria.Categoria;
import com.loja.demo.services.categoria.CategoriaServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController extends GeneralController implements CategoriaApi {

    @Autowired
    private CategoriaServices categoriaServices;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return categoriaServices.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return categoriaServices.findById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findCategoriaByName(@PathVariable String name) {
        return categoriaServices.findCategoriaByName(name);
    }

    @PostMapping()
    public ResponseEntity<?> insertCategoria(@Valid @RequestBody Categoria categoria) {
        return categoriaServices.insertCategoria(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria (@PathVariable Integer id) {
        return categoriaServices.deleteCategoria(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoria(@PathVariable Integer id, @Valid @RequestBody Categoria categoria) {
        return categoriaServices.updateCategoria(id, categoria);
    }
}
