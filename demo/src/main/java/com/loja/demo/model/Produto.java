package com.loja.demo.model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cd_produto;

    @NotEmpty
    private String nm_produto;

    @NotNull
    private Integer qt_estoque;

    @NotNull
    private Double vl_produto;

    public Produto() {}

    public Produto(Integer cd_produto, @NotEmpty String nm_produto, @NotNull Integer qt_estoque,
            @NotNull Double vl_produto) {
        this.setCd_produto(cd_produto);
        this.setNm_produto(nm_produto);
        this.setQt_estoque(qt_estoque);
        this.setVl_produto(vl_produto);
    }

    public Integer getCd_produto() {
        return cd_produto;
    }

    public void setCd_produto(Integer cd_produto) {
        this.cd_produto = cd_produto;
    }

    public String getNm_produto() {
        return nm_produto;
    }

    public void setNm_produto(String nm_produto) {
        this.nm_produto = nm_produto;
    }

    public Integer getQt_estoque() {
        return qt_estoque;
    }

    public void setQt_estoque(Integer qt_estoque) {
        this.qt_estoque = qt_estoque;
    }

    public Double getVl_produto() {
        return vl_produto;
    }

    public void setVl_produto(Double vl_produto) {
        if (vl_produto < 0) {
            throw new IllegalArgumentException("O valor do produto não pode ser abaixo de 0");
        }
        
        this.vl_produto = vl_produto;
    }
}