package com.loja.demo.controller.municipio;

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
import com.loja.demo.model.entity.municipio.Municipio;
import com.loja.demo.services.municipio.MunicipioServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/municipio")
public class MunicipioController extends GeneralController {

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

    @PostMapping()
    public ResponseEntity<?> insertMunicipio(@Valid @RequestBody Municipio municipio) {
        return municipioServices.insertMunicipio(municipio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMunicipio(@PathVariable Integer id) {
        return municipioServices.deleteMunicipio(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMunicipio(@PathVariable Integer id, @Valid @RequestBody Map<String, Object> municipio) {
        return municipioServices.updateMunicipio(id, municipio);
    }
}
