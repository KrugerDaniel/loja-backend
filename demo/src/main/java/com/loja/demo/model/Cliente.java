package com.loja.demo.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cd_cliente;

    @NotEmpty
    @Length(max = 50, message = "Atributo NM_CLIENTE deve ter, no máximo, 50 caracteres.")
    private String nm_cliente;

    @NotEmpty
    @Length(max = 15, message = "Atributo NR_TELEFONE deve ter, no máximo, 15 caracteres.")
    private String nr_telefone;

    @NotEmpty
    @Length(max = 50, message = "Atributo DS_EMAIL deve ter, no máximo, 50 caracteres.")
    private String ds_email;

    @NotNull
    private Double vl_limite_credito;

    public Cliente() {}

    public Cliente(Integer cd_cliente, @NotEmpty String nm_cliente, @NotEmpty String nr_telefone,
            @NotEmpty String ds_email, @NotNull Double vl_limite_credito) {
        this.setCd_cliente(cd_cliente);
        this.setNm_cliente(nm_cliente);
        this.setDs_email(ds_email);
        this.setNr_telefone(nr_telefone);
        this.setVl_limite_credito(vl_limite_credito);
    }

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
        if (vl_limite_credito < 0) {
            throw new IllegalArgumentException("O limite do crédito não pode ser abaixo de 0.");
        }
        
        this.vl_limite_credito = vl_limite_credito;
    }
}
