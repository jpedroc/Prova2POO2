/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


/**
 *
 * @author jean_
 */
public class Venda {
    private Integer idVenda;
    private String dataVenda;
    private Integer qtdeVendida;
    private Double valorTotal;
    private Produto produto;

    public Venda() {
    }

    @Override
    public String toString() {
        return "idVenda=" + idVenda + ", dataVenda=" + dataVenda + ", qtdeVendida=" + qtdeVendida + ", valorTotal=" + valorTotal + ", produto=" + produto.getNome() + '\n';
    }

    public Venda(Integer idVenda, String dataVenda, Integer qtdeVenda, Double valorTotal, Produto produto) {
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
        this.qtdeVendida = qtdeVenda;
        this.valorTotal = valorTotal;
        this.produto = produto;
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Integer getQtdeVenda() {
        return qtdeVendida;
    }

    public void setQtdeVenda(Integer qtdeVenda) {
        this.qtdeVendida = qtdeVenda;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
}
