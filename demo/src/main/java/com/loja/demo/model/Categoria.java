package com.loja.demo.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Categoria implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cd_categoria;

    @NotEmpty
    @Length(max = 50, message = "Atributo NM_CATEGORIA deve ter, no máximo, 50 caracteres.")
    private String nm_categoria;

    public Categoria() {}

    public Categoria(Integer cd_categoria, @NotEmpty String nm_categoria) {
        this.setCd_categoria(cd_categoria);
        this.setNm_categoria(nm_categoria);
    }

    public Integer getCd_categoria() {
        return cd_categoria;
    }

    public void setCd_categoria(Integer cd_categoria) {
        this.cd_categoria = cd_categoria;
    }

    public String getNm_categoria() {
        return nm_categoria;
    }

    public void setNm_categoria(String nm_categoria) {
        this.nm_categoria = nm_categoria;
    }
}
