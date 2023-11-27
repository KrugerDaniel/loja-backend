package com.loja.demo.model.dto.endereco;

public class EnderecoDTO {

    private Integer cd_endereco;
    private String nm_logradouro;
    private String ds_complemento;
    private String nm_bairro;
    private String nr_cep;
    private Integer municipio;
    private Integer cliente;

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

    public Integer getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }
}
