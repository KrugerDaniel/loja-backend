package com.loja.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.Cliente;
import com.loja.demo.model.Endereco;
import com.loja.demo.model.Municipio;
import com.loja.demo.repository.ClienteRepository;
import com.loja.demo.repository.EnderecoRepository;
import com.loja.demo.repository.MunicipioRepository;

@Service
public class EnderecoServices {
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    public Endereco insertEndereco(Endereco endereco) {
        endereco.setCd_endereco(null);
        return enderecoRepository.save(endereco);
    }

    public void deleteEndereco(Integer id) {
        enderecoRepository.findById(id).orElseThrow(
            () -> new ObjectNotFoundException("Endereço " + id + " não encontrado!")
        );

        enderecoRepository.deleteById(id);
    }

    public Endereco updateEndereco(Integer id, Endereco endereco) {
        Endereco newEndereco = this.findEndereco(id);

        this.validateMunicipio(endereco);
        this.validateCliente(endereco);

        newEndereco.setNm_logradouro(endereco.getNm_logradouro());
        newEndereco.setDs_complemento(endereco.getDs_complemento());
        newEndereco.setNm_bairro(endereco.getNm_bairro());
        newEndereco.setNr_cep(endereco.getNr_cep());
        newEndereco.setMunicipio(endereco.getMunicipio());
        newEndereco.setCliente(endereco.getCliente());

        return enderecoRepository.save(newEndereco);
    }

    public Endereco updateEnderecoLogradouro(Integer id, String logradouro) {
        Endereco newEndereco = this.findEndereco(id);

        newEndereco.setNm_logradouro(logradouro);

        return enderecoRepository.save(newEndereco);
    }

    public Endereco updateEnderecoBairro(Integer id, String bairro) {
        Endereco newEndereco = this.findEndereco(id);

        newEndereco.setNm_logradouro(bairro);

        return enderecoRepository.save(newEndereco);
    }

    public Endereco updateEnderecoCEP(Integer id, String cep) {
        Endereco newEndereco = this.findEndereco(id);

        newEndereco.setNm_logradouro(cep);

        return enderecoRepository.save(newEndereco);
    }

	public Endereco updateEnderecoMunicipio(Integer id, Municipio municipio) {
        Endereco endereco = this.findEndereco(id);

        endereco.setMunicipio(municipio);

        return enderecoRepository.save(endereco);
	}

    public Endereco updateEnderecoCliente(Integer id, Cliente cliente) {
        Endereco endereco = this.findEndereco(id);

        endereco.setCliente(cliente);

        return enderecoRepository.save(endereco);
    }

    public Endereco updateEnderecoMunicipioById(Integer id, Integer municipioId) {
        Municipio municipio = this.getMunicipio(municipioId);
        Endereco endereco = this.findEndereco(id);

        endereco.setMunicipio(municipio);

        return enderecoRepository.save(endereco);
    }

    public Endereco updateEnderecoClienteById(Integer id, Integer clienteId) {
        Cliente cliente = this.getCliente(clienteId);
        Endereco endereco = this.findEndereco(id);

        endereco.setCliente(cliente);

        return enderecoRepository.save(endereco);
    }

    private Endereco findEndereco(Integer id) {
        return enderecoRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Endereço " + id + " não encontrado")
            );
    }

    private void validateMunicipio(Endereco endereco) {
        Integer municipioId = endereco.getMunicipio().getCd_municipio();
        municipioRepository.findById(municipioId)
            .orElseThrow(
                () -> new ObjectNotFoundException("Município " + municipioId + " não encontrado!")
            );
    }

    private void validateCliente(Endereco endereco) {
        Integer clienteId = endereco.getCliente().getCd_cliente();
        clienteRepository.findById(clienteId)
            .orElseThrow(
                () -> new ObjectNotFoundException("Cliente " + clienteId + " não encontrado!")
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
