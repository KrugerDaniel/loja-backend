package com.loja.demo.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nr_pedido;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dt_emissao;

    @NotNull
    private Double vl_total;

    @ManyToOne
    @JoinColumn(name = "cd_cliente")
    private Cliente cliente;

    public Pedido() {}

    public Pedido(Integer nr_pedido, @NotNull Date dt_emissao, @NotNull Double vl_total, Cliente cliente) {
        this.setNr_pedido(nr_pedido);
        this.setDt_emissao(dt_emissao);
        this.setVl_total(vl_total);
        this.setCliente(cliente);
    }

    public Integer getNr_pedido() {
        return nr_pedido;
    }

    public void setNr_pedido(Integer nr_pedido) {
        this.nr_pedido = nr_pedido;
    }

    public Date getDt_emissao() {
        return dt_emissao;
    }

    public void setDt_emissao(Date dt_emissao) {
        this.dt_emissao = dt_emissao;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
