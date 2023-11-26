package com.loja.demo.controller.endereco;

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
import com.loja.demo.services.endereco.EnderecoServices;
import com.loja.demo.model.entity.cliente.Cliente;
import com.loja.demo.model.entity.endereco.Endereco;

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

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> findEnderecoByCliente(@PathVariable Integer id) {
        return enderecoServices.findEnderecoByCliente(id);
    }

    @GetMapping("/municipio/{id}")
    public ResponseEntity<?> findEnderecoByMunicipio(@PathVariable Integer id) {
        return enderecoServices.findEnderecoByMunicipio(id);
    }

    @GetMapping("/logradouro/{logradouro}")
    public ResponseEntity<?> findEnderecoByLogradouro(@PathVariable String logradouro) {
        return enderecoServices.findEnderecoByLogradouro(logradouro);
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<?> findEnderecoByCEP(@PathVariable String cep) {
        return enderecoServices.findEnderecoByCEP(cep);
    }

    @GetMapping("/bairro/{bairro}")
    public ResponseEntity<?> findEnderecoByBairro(@PathVariable String bairro) {
        return enderecoServices.findEnderecoByBairro(bairro);
    }

    @PostMapping()
    public ResponseEntity<?> insertEndereco(@Valid @RequestBody Endereco endereco) {
        return enderecoServices.insertEndereco(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEndereco(@PathVariable Integer id) {
        return enderecoServices.deleteEndereco(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEndereco(@PathVariable Integer id, @Valid @RequestBody Endereco endereco) {
        return enderecoServices.updateEndereco(id, endereco);
    }

    @PutMapping("/{id}/logradouro/{logradouro}")
    public ResponseEntity<?> updateEnderecoLogradouro(@PathVariable Integer id, @PathVariable String logradouro) {
        return enderecoServices.updateEnderecoLogradouro(id, logradouro);
    }

    @PutMapping("/{id}/bairro/{bairro}")
    public ResponseEntity<?> updateEnderecoBairro(@PathVariable Integer id, @PathVariable String bairro) {
        return enderecoServices.updateEnderecoBairro(id, bairro);
    }

    @PutMapping("/{id}/cep/{cep}")
    public ResponseEntity<?> updateEnderecoCEP(@PathVariable Integer id, @PathVariable String cep) {
        return enderecoServices.updateEnderecoCEP(id, cep);
    }

    @PutMapping("/{id}/municipio")
    public ResponseEntity<?> updateEnderecoMunicipio(@PathVariable Integer id, @Valid @RequestBody Municipio municipio) {
        return enderecoServices.updateEnderecoMunicipio(id, municipio);
    }

    @PutMapping("/{id}/cliente")
    public ResponseEntity<?> updateEnderecoCliente(@PathVariable Integer id, @Valid @RequestBody Cliente cliente) {
        return enderecoServices.updateEnderecoCliente(id, cliente);
    }

    @PutMapping("/{id}/municipio/{municipioId}")
    public ResponseEntity<?> updateEnderecoMunicipioById(@PathVariable Integer id, @PathVariable Integer municipioId) {
        return enderecoServices.updateEnderecoMunicipioById(id, municipioId);
    }

    @PutMapping("/{id}/municipio/{clienteId}")
    public ResponseEntity<?> updateEnderecoClienteById(@PathVariable Integer id, @PathVariable Integer clienteId) {
        return enderecoServices.updateEnderecoClienteById(id, clienteId);
    }
}
