package com.loja.demo.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Endereco implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cd_endereco;

    @NotEmpty
    private String nm_logradouro;

    @NotEmpty
    private String ds_complemento;

    @NotEmpty
    private String nm_bairro;

    @NotEmpty
    private  String nr_cep;

    @ManyToOne
    @JoinColumn(name = "cd_municipio")
    private Municipio municipio;
    
    @ManyToOne
    @JoinColumn(name = "cd_cliente")
    private Cliente cliente;

    public Endereco() {}

    public Endereco(Integer cd_endereco, @NotEmpty String nm_logradouro, @NotEmpty String ds_complemento,
            @NotEmpty String nm_bairro, @NotEmpty String nr_cep, Municipio municipio, Cliente cliente) {
        this.setCd_endereco(cd_endereco);
        this.setNm_logradouro(nm_logradouro);
        this.setDs_complemento(ds_complemento);
        this.setNm_bairro(nm_bairro);
        this.setNr_cep(nr_cep);
        this.setMunicipio(municipio);
        this.setCliente(cliente);
    }

    public Integer getCd_endereco() {
        return cd_endereco;
    }

    public void setCd_endereco(Integer cd_endereco) {
        this.cd_endereco = cd_endereco;
    }

    public String getNm_logradouro() {
        return nm_logradouro;
    }

    public void setNm_logradouro(String nm_logradouro) {
        this.nm_logradouro = nm_logradouro;
    }

    public String getDs_complemento() {
        return ds_complemento;
    }

    public void setDs_complemento(String ds_complemento) {
        this.ds_complemento = ds_complemento;
    }

    public String getNm_bairro() {
        return nm_bairro;
    }

    public void setNm_bairro(String nm_bairro) {
        this.nm_bairro = nm_bairro;
    }

    public String getNr_cep() {
        return nr_cep;
    }

    public void setNr_cep(String nr_cep) {
        this.nr_cep = nr_cep;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
