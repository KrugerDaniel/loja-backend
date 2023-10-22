package com.loja.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.Cliente;
import com.loja.demo.model.Endereco;
import com.loja.demo.model.Municipio;
import com.loja.demo.repository.ClienteRepository;
import com.loja.demo.repository.EnderecoRepository;
import com.loja.demo.repository.MunicipioRepository;

import enums.HttpStatusCode;

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

    public ResponseEntity<?> findEnderecoByCliente(Integer id) {
        try {
            List<Endereco> enderecos = enderecoRepository.findEnderecoByCliente(id);

            if (enderecos.isEmpty()) {
                throw new ObjectNotFoundException("Cliente " + id + " não encontrado!");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(enderecos);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findEnderecoByMunicipio(Integer id) {
        try {
            List<Endereco> enderecos = enderecoRepository.findEnderecoByMunicipio(id);

            if (enderecos.isEmpty()) {
                throw new ObjectNotFoundException("Município " + id + " não encontrado!");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(enderecos);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findEnderecoByLogradouro(String logradouro) {
        try {
            List<Endereco> enderecos = enderecoRepository.findEnderecoByLogradouro(logradouro);
            if (enderecos.isEmpty()) {
                throw new ObjectNotFoundException("Logradouro " + logradouro + " não encontrado!");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(enderecos);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findEnderecoByCEP(String cep) {
        try {
            List<Endereco> enderecos = enderecoRepository.findEnderecoByCEP(cep);
            if (enderecos.isEmpty()) {
                throw new ObjectNotFoundException("CEP " + cep + " não encontrado!");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(enderecos);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findEnderecoByBairro(String bairro) {
        try {
            List<Endereco> enderecos = enderecoRepository.findEnderecoByBairro(bairro);
            if (enderecos.isEmpty()) {
                throw new ObjectNotFoundException("Bairro " + bairro + " não encontrado!");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(enderecos);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> insertEndereco(Endereco endereco) {
        try {
            endereco.setCd_endereco(null);
            Endereco newEndereco = enderecoRepository.save(endereco);

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

    public ResponseEntity<?> updateEndereco(Integer id, Endereco endereco) {
        try {
            Endereco newEndereco = this.findEndereco(id);

            this.getMunicipio(endereco.getMunicipio().getCd_municipio());
            this.getCliente(endereco.getCliente().getCd_cliente());

            newEndereco.setNm_logradouro(endereco.getNm_logradouro());
            newEndereco.setDs_complemento(endereco.getDs_complemento());
            newEndereco.setNm_bairro(endereco.getNm_bairro());
            newEndereco.setNr_cep(endereco.getNr_cep());
            newEndereco.setMunicipio(endereco.getMunicipio());
            newEndereco.setCliente(endereco.getCliente());

            newEndereco = enderecoRepository.save(newEndereco);
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

    public ResponseEntity<?> updateEnderecoLogradouro(Integer id, String logradouro) {
        try {
            Endereco newEndereco = this.findEndereco(id);

            newEndereco.setNm_logradouro(logradouro);

            newEndereco = enderecoRepository.save(newEndereco);
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

    public ResponseEntity<?> updateEnderecoBairro(Integer id, String bairro) {
        try {
            Endereco newEndereco = this.findEndereco(id);

            newEndereco.setNm_logradouro(bairro);

            newEndereco = enderecoRepository.save(newEndereco);
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

    public ResponseEntity<?> updateEnderecoCEP(Integer id, String cep) {
        try {
            Endereco newEndereco = this.findEndereco(id);

            newEndereco.setNm_logradouro(cep);

            newEndereco = enderecoRepository.save(newEndereco);
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

	public ResponseEntity<?> updateEnderecoMunicipio(Integer id, Municipio municipio) {
        try {
            Endereco endereco = this.findEndereco(id);

            endereco.setMunicipio(municipio);

            endereco = enderecoRepository.save(endereco);
            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(endereco);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
	}

    public ResponseEntity<?> updateEnderecoCliente(Integer id, Cliente cliente) {
        try {
            Endereco endereco = this.findEndereco(id);

            endereco.setCliente(cliente);

            endereco = enderecoRepository.save(endereco);
            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(endereco);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> updateEnderecoMunicipioById(Integer id, Integer municipioId) {
        try {
            Municipio municipio = this.getMunicipio(municipioId);
            Endereco endereco = this.findEndereco(id);

            endereco.setMunicipio(municipio);

            endereco = enderecoRepository.save(endereco);
            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(endereco);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> updateEnderecoClienteById(Integer id, Integer clienteId) {
        try {
            Cliente cliente = this.getCliente(clienteId);
            Endereco endereco = this.findEndereco(id);

            endereco.setCliente(cliente);

            endereco = enderecoRepository.save(endereco);
            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(endereco);
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
                () -> new ObjectNotFoundException("Endereço " + id + " não encontrado")
            );
    }

    private Municipio getMunicipio(Integer id) {
        return municipioRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Município " + id + " não encontrado!")
            );
    }

    private Cliente getCliente(Integer id) {
        return clienteRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Cliente " + id + " não encontrado!")
            );
    }
}
