package com.loja.demo.controller;

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

import com.loja.demo.model.Municipio;
import com.loja.demo.services.MunicipioServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/municipio")
public class MunicipioController {

    @Autowired
    private MunicipioServices municipioServices;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return municipioServices.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return municipioServices.findById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findMunicipioByName(@PathVariable String name) {
        return municipioServices.findMunicipioByName(name);
    }

    @GetMapping("/uf/{uf}")
    public ResponseEntity<?> findMunicipioByUF(@PathVariable String uf) {
        return municipioServices.findMunicipioByUF(uf);
    }

    @PostMapping()
    public ResponseEntity<?> insertMunicipio(@Valid @RequestBody Municipio municipio) {
        return municipioServices.insertMunicipio(municipio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMunicipio(@PathVariable Integer id) {
        return municipioServices.deleteMunicipio(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMunicipio(@PathVariable Integer id, @Valid @RequestBody Municipio municipio) {
        return municipioServices.updateMunicipio(id, municipio);
    }

    @PutMapping("/{id}/name/{name}")
    public ResponseEntity<?> updateMunicipioName(@PathVariable Integer id, @PathVariable String name) {
        return municipioServices.updateMunicipioName(id, name);
    }

    @PutMapping("/{id}/uf/{uf}")
    public ResponseEntity<?> updateMunicipioUF(@PathVariable Integer id, @PathVariable String uf) {
        return municipioServices.updateMunicipioUF(id, uf);
    }
}
