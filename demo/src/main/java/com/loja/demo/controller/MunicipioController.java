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

import com.loja.demo.model.Municipio;
import com.loja.demo.repository.MunicipioRepository;
import com.loja.demo.services.MunicipioServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/municipio")
public class MunicipioController extends GeneralController {
    
    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private MunicipioServices municipioServices;

    @GetMapping()
    public ResponseEntity<List<Municipio>> findAll() {
        List<Municipio> municipios = municipioRepository.findAll();

        return ResponseEntity.ok().body(municipios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Municipio>> findById(@PathVariable Integer id) {
        Optional<Municipio> municipio = municipioRepository.findById(id);

        return ResponseEntity.ok().body(municipio);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Municipio>> findMunicipioByName(@PathVariable String name) {
        List<Municipio> municipios = municipioRepository.findMunicipioByName(name);

        return ResponseEntity.ok().body(municipios);
    }

    @GetMapping("/uf/{uf}")
    public ResponseEntity<List<Municipio>> findMunicipioByUF(@PathVariable String uf) {
        List<Municipio> municipios = municipioRepository.findMunicipioByUF(uf);

        return ResponseEntity.ok().body(municipios);
    }

    @PostMapping()
    public ResponseEntity<Municipio> insertMunicipio(@Valid @RequestBody Municipio municipio) {
        Municipio newMunicipio = municipioServices.insertMunicipio(municipio);

        return ResponseEntity.created(this.getUri(newMunicipio.getCd_municipio())).body(newMunicipio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMunicipio(@PathVariable Integer id) {
        municipioServices.deleteMunicipio(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Municipio> updateMunicipio(@PathVariable Integer id, @Valid @RequestBody Municipio municipio) {
        Municipio municipioUpdated = municipioServices.updateMunicipio(id, municipio);

        return ResponseEntity.created(this.getUri(id)).body(municipioUpdated);
    }

    @PutMapping("/{id}/name/{name}")
    public ResponseEntity<Municipio> updateMunicipioName(@PathVariable Integer id, @PathVariable String name) {
        Municipio municipio = municipioServices.updateMunicipioName(id, name);

        return ResponseEntity.created(this.getUri(id)).body(municipio);
    }

    @PutMapping("/{id}/uf/{uf}")
    public ResponseEntity<Municipio> updateMunicipioUF(@PathVariable Integer id, @PathVariable String uf) {
        Municipio municipio = municipioServices.updateMunicipioUF(id, uf);

        return ResponseEntity.created(this.getUri(id)).body(municipio);
    }
}
