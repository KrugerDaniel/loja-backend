package com.loja.demo.controller.endereco;

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

import com.loja.demo.controller.GeneralController;
import com.loja.demo.services.endereco.EnderecoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController extends GeneralController {

    @Autowired
    private EnderecoServices enderecoServices;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return enderecoServices.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return enderecoServices.findById(id);
    }

    @PostMapping()
    public ResponseEntity<?> insertEndereco(@Valid @RequestBody Map<String, Object> endereco) {
        return enderecoServices.insertEndereco(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEndereco(@PathVariable Integer id) {
        return enderecoServices.deleteEndereco(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEndereco(@PathVariable Integer id, @Valid @RequestBody Map<String, Object> endereco) {
        return enderecoServices.updateEndereco(id, endereco);
    }
}
