package com.loja.demo.model.dto.itempedido;

public class ItemPedidoDTO {

    private Integer produto;
    private Integer pedido;
    private Integer qt_produto;
    private Double vl_produto;
    private Double vl_total;

    public Integer getProduto() {
        return produto;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }

    public Integer getPedido() {
        return pedido;
    }

    public void setPedido(Integer pedido) {
        this.pedido = pedido;
    }

    public Integer getQt_produto() {
        return qt_produto;
    }

    public void setQt_produto(Integer qt_produto) {
        this.qt_produto = qt_produto;
    }

    public Double getVl_produto() {
        return vl_produto;
    }

    public void setVl_produto(Double vl_produto) {
        this.vl_produto = vl_produto;
    }

    public Double getVl_total() {
        return vl_total;
    }

    public void setVl_total(Double vl_total) {
        this.vl_total = vl_total;
    }
}
