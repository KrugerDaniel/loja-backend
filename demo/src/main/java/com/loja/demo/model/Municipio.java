package com.loja.demo.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Municipio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cd_munipio;

    @NotEmpty
    private String nm_municipio;

    @NotEmpty
    private String sg_uf;

    public Municipio() {}

    public Municipio(Integer cd_municipio, @NotEmpty String nm_municipio, @NotEmpty String sg_uf) {
        this.setCd_munipio(cd_municipio);
        this.setNm_municipio(nm_municipio);
        this.setSg_uf(sg_uf);
    }

    public Integer getCd_municipio() {
        return cd_munipio;
    }

    public void setCd_munipio(Integer cd_munipio) {
        this.cd_munipio = cd_munipio;
    }

    public String getNm_municipio() {
        return nm_municipio;
    }

    public void setNm_municipio(String nm_municipio) {
        this.nm_municipio = nm_municipio;
    }

    public String getSg_uf() {
        return sg_uf;
    }

    public void setSg_uf(String sg_uf) {
        this.sg_uf = sg_uf;
    }
}
