package com.loja.demo.repository.endereco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loja.demo.model.entity.endereco.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    @Query("SELECT endereco FROM Endereco endereco WHERE endereco.cliente.cd_cliente = :clienteId")
    List<Endereco> findEnderecoByCliente(@Param(value = "clienteId") Integer clienteId);

    @Query("SELECT endereco FROM Endereco endereco WHERE endereco.municipio.cd_municipio = :municipioId")
    List<Endereco> findEnderecoByMunicipio(@Param(value = "municipioId") Integer municipioId);

    @Query("SELECT endereco FROM Endereco endereco WHERE endereco.nm_logradouro LIKE %:nmLogradouro%")
    List<Endereco> findEnderecoByLogradouro(@Param(value = "nmLogradouro") String nmLogradouro);

    @Query("SELECT endereco FROM Endereco endereco WHERE endereco.nr_cep = :nrCEP")
    List<Endereco> findEnderecoByCEP(@Param(value = "nrCEP") String nrCEP);

    @Query("SELECT endereco FROM Endereco endereco WHERE endereco.nm_logradouro LIKE %:nmBairro%")
    List<Endereco> findEnderecoByBairro(@Param(value = "nmBairro") String nmBairro);
}