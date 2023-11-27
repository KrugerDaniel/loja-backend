package com.loja.demo.model.dto.cliente;

public class ClienteDTO {

    private Integer cd_cliente;
    private String nm_cliente;
    private String nr_telefone;
    private String ds_email;
    private Double vl_limite_credito;

    public Integer getCd_cliente() {
        return cd_cliente;
    }

    public void setCd_cliente(Integer cd_cliente) {
        this.cd_cliente = cd_cliente;
    }

    public String getNm_cliente() {
        return nm_cliente;
    }

    public void setNm_cliente(String nm_cliente) {
        this.nm_cliente = nm_cliente;
    }

    public String getNr_telefone() {
        return nr_telefone;
    }

    public void setNr_telefone(String nr_telefone) {
        this.nr_telefone = nr_telefone;
    }

    public String getDs_email() {
        return ds_email;
    }

    public void setDs_email(String ds_email) {
        this.ds_email = ds_email;
    }

    public Double getVl_limite_credito() {
        return vl_limite_credito;
    }

    public void setVl_limite_credito(Double vl_limite_credito) {
        this.vl_limite_credito = vl_limite_credito;
    }

}
