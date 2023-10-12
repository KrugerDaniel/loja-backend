package com.loja.demo.model;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nr_pedido;

    @NotEmpty
    @Length(max = 192, message = "Atributo DT_EMISSAo deve ter, no máximo, 2 caracteres.")
    private String dt_emissao;

    @NotNull
    private Double vl_total;

    @ManyToOne
    @JoinColumn(name = "cd_cliente")
    private Cliente cliente;

    public Pedido() {}

    public Pedido(Integer nr_pedido, @NotEmpty String dt_emissao, @NotNull Double vl_total, Cliente cliente) {
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

    public String getDt_emissao() {
        return dt_emissao;
    }

    public void setDt_emissao(String dt_emissao) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date;

        try {
            date = LocalDate.parse(dt_emissao, formatador);
        } catch (DateTimeException exception) {
            throw new DateTimeException("Data fora do padrão (dd/mm/yyyy)");
        }
        
        this.dt_emissao = date.toString();
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
