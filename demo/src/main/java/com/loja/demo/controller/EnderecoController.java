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

import com.loja.demo.model.Cliente;
import com.loja.demo.model.Endereco;
import com.loja.demo.model.Municipio;
import com.loja.demo.repository.EnderecoRepository;
import com.loja.demo.services.EnderecoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController extends GeneralController {
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoServices enderecoServices;

    @GetMapping()
    public ResponseEntity<List<Endereco>> findAll() {
        List<Endereco> enderecos = enderecoRepository.findAll();

        return ResponseEntity.ok().body(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Endereco>> findById(@PathVariable Integer id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);

        return ResponseEntity.ok().body(endereco);
    }

    @PostMapping()
    public ResponseEntity<Endereco> insertEndereco(@Valid @RequestBody Endereco cliente) {
        Endereco newEndereco = enderecoServices.insertEndereco(cliente);
            
        return ResponseEntity.created(this.getUri(newEndereco.getCd_endereco())).body(newEndereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Integer id) {
        enderecoServices.deleteEndereco(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable Integer id, @Valid @RequestBody Endereco endereco) {
        Endereco enderecoUpdated = enderecoServices.updateEndereco(id, endereco);

        return ResponseEntity.created(this.getUri(id)).body(enderecoUpdated);
    }

    @PutMapping("/{id}/logradouro/{logradouro}")
    public ResponseEntity<Endereco> updateEnderecoLogradouro(@PathVariable Integer id, @PathVariable String logradouro) {
        Endereco enderecoUpdated = enderecoServices.updateEnderecoLogradouro(id, logradouro);

        return ResponseEntity.created(this.getUri(id)).body(enderecoUpdated);
    }

    @PutMapping("/{id}/bairro/{bairro}")
    public ResponseEntity<Endereco> updateEnderecoBairro(@PathVariable Integer id, @PathVariable String bairro) {
        Endereco enderecoUpdated = enderecoServices.updateEnderecoBairro(id, bairro);

        return ResponseEntity.created(this.getUri(id)).body(enderecoUpdated);
    }

    @PutMapping("/{id}/cep/{cep}")
    public ResponseEntity<Endereco> updateEnderecoCEP(@PathVariable Integer id, @PathVariable String cep) {
        Endereco enderecoUpdated = enderecoServices.updateEnderecoCEP(id, cep);

        return ResponseEntity.created(this.getUri(id)).body(enderecoUpdated);
    }

    @PutMapping("/{id}/municipio")
    public ResponseEntity<Endereco> updateEnderecoMunicipio(@PathVariable Integer id, @Valid @RequestBody Municipio municipio) {
        Endereco enderecoUpdated = enderecoServices.updateEnderecoMunicipio(id, municipio);

        return ResponseEntity.created(this.getUri(id)).body(enderecoUpdated);
    }

    @PutMapping("/{id}/cliente")
    public ResponseEntity<Endereco> updateEnderecoCliente(@PathVariable Integer id, @Valid @RequestBody Cliente cliente) {
        Endereco enderecoUpdated = enderecoServices.updateEnderecoCliente(id, cliente);

        return ResponseEntity.created(this.getUri(id)).body(enderecoUpdated);
    }

    @PutMapping("/{id}/municipio/{municipioId}")
    public ResponseEntity<Endereco> updateEnderecoMunicipioById(@PathVariable Integer id, @PathVariable Integer municipioId) {
        Endereco enderecoUpdated = enderecoServices.updateEnderecoMunicipioById(id, municipioId);

        return ResponseEntity.created(this.getUri(id)).body(enderecoUpdated);
    }

    @PutMapping("/{id}/municipio/{clienteId}")
    public ResponseEntity<Endereco> updateEnderecoClienteById(@PathVariable Integer id, @PathVariable Integer clienteId) {
        Endereco enderecoUpdated = enderecoServices.updateEnderecoClienteById(id, clienteId);

        return ResponseEntity.created(this.getUri(id)).body(enderecoUpdated);
    }
}
