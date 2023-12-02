package com.loja.demo.services.endereco;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.loja.demo.enums.HttpStatusCode;
import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.entity.municipio.Municipio;
import com.loja.demo.model.entity.cliente.Cliente;
import com.loja.demo.model.entity.endereco.Endereco;
import com.loja.demo.repository.cliente.ClienteRepository;
import com.loja.demo.repository.endereco.EnderecoRepository;
import com.loja.demo.repository.municipio.MunicipioRepository;
import com.loja.demo.services.GeneralService;

@Service
public class EnderecoServices extends GeneralService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    public ResponseEntity<?> findAll() {
        try {
            List<Endereco> enderecos = enderecoRepository.findAll();

            if (enderecos.isEmpty()) {
                throw new ObjectNotFoundException("Não há Endereços");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(enderecos);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                    .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findById(Integer id) {
        try {
            Endereco endereco = this.findEndereco(id);

            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(endereco);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                    .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> insertEndereco(Map<String, Object> endereco) {
        try {
            final Endereco newEndereco = new Endereco();

            endereco.forEach((campo, valor) -> {
                switch (campo) {
                    case "nm_logradouro":
                        newEndereco.setNm_logradouro((String) valor);
                        break;
                    case "ds_complemento":
                        newEndereco.setDs_complemento((String) valor);
                        break;
                    case "nm_bairro":
                        newEndereco.setNm_bairro((String) valor);
                        break;
                    case "nr_cep":
                        newEndereco.setNr_cep((String) valor);
                        break;
                    case "municipio":
                        newEndereco.setMunicipio(this.getMunicipio((Integer) valor));
                        break;
                    case "cliente":
                        newEndereco.setCliente(this.getCliente((Integer) valor));
                        break;
                }
            });
            
            enderecoRepository.save(newEndereco);

            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(newEndereco.getCd_endereco()))
                .body(newEndereco);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> deleteEndereco(Integer id) {
        try {
            this.findEndereco(id);

            enderecoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatusCode.NO_CONTENT.getCode()).build();
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                    .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> updateEndereco(Integer id, Map<String, Object> endereco) {
        try {
            final Endereco newEndereco = this.findEndereco(id);

            endereco.forEach((campo, valor) -> {
                switch (campo) {
                    case "nm_logradouro":
                        newEndereco.setNm_logradouro((String) valor);
                        break;
                    case "ds_complemento":
                        newEndereco.setDs_complemento((String) valor);
                        break;
                    case "nm_bairro":
                        newEndereco.setNm_bairro((String) valor);
                        break;
                    case "nr_cep":
                        newEndereco.setNr_cep((String) valor);
                        break;
                    case "municipio":
                        newEndereco.setMunicipio(this.getMunicipio((Integer) valor));
                        break;
                    case "cliente":
                        newEndereco.setCliente(this.getCliente((Integer) valor));
                        break;
                }
            });

            enderecoRepository.save(newEndereco);
            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                    .headers(this.getHeaders(id))
                    .body(newEndereco);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                    .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    private Endereco findEndereco(Integer id) {
        return enderecoRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Endereço " + id + " não encontrado"));
    }

    private Municipio getMunicipio(Integer id) {
        return municipioRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Município " + id + " não encontrado!"));
    }

    private Cliente getCliente(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Cliente " + id + " não encontrado!"));
    }
}
