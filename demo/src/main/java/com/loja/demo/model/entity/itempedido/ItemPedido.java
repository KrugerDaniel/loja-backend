package com.loja.demo.model.entity.itempedido;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class ItemPedido implements Serializable {
    
    @EmbeddedId
    private ItemPedidoID id;

    @NotNull
    private Integer qt_produto;

    @NotNull
    private Double vl_produto;

    @NotNull
    private Double vl_total;

    public ItemPedido() {}

    public ItemPedido(ItemPedidoID id, @NotNull Integer qt_produto, @NotNull Double vl_produto,
            @NotNull Double vl_total) {    
        this.setId(id);
        this.setQt_produto(qt_produto);
        this.setVl_produto(vl_produto);
        this.setVl_total(vl_total);
    }

    public ItemPedidoID getId() {
        return id;
    }

    public void setId(ItemPedidoID id) {
        this.id = id;
    }

    public Integer getQt_produto() {
        return qt_produto;
    }

    public void setQt_produto(Integer qt_produto) {
        if (qt_produto < 0) {
            throw new IllegalArgumentException("A quantidade de produtos não pode ser abaixo de 0");
        }
        
        this.qt_produto = qt_produto;
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

    public Double getVl_total() {
        return vl_total;
    }

    public void setVl_total(Double vl_total) {
        if (vl_total < 0) {
            throw new IllegalArgumentException("O valor total não pode ser abaixo de 0");
        }
        
        this.vl_total = vl_total;
    }
}