/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ConexaoBanco;
import dao.ProdutoDAO;
import dao.VendaDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import modelo.Produto;
import modelo.Venda;

/**
 *
 * @author jpedroc
 */
public class DominioController {
    
    private final ProdutoDAO produtoDAO;
    private final VendaDAO vendaDAO;

    public DominioController() throws ClassNotFoundException, SQLException {
        ConexaoBanco.obterConexao();
        produtoDAO = new ProdutoDAO();
        vendaDAO = new VendaDAO();
    }
    
    public List<Produto> obterProdutos() throws ClassNotFoundException, SQLException{
        return produtoDAO.listar();
    }
    
    public void inserirVenda(String data, Integer qtd, Double valorTotal, Produto produto) throws ClassNotFoundException, SQLException, ParseException{
        Venda venda = new Venda();
        venda.setDataVenda(data);
        venda.setProduto(produto);
        venda.setQtdeVenda(qtd);
        venda.setValorTotal(valorTotal);
        vendaDAO.inserir(venda);
    }
    
    public Venda buscarVenda(Integer id) throws ClassNotFoundException, SQLException{
        Venda venda = new Venda();
        venda = vendaDAO.pesquisar(id);
        return venda;
    }
    
    public List<Venda> listarVendasDia(String dataPesq) throws ClassNotFoundException, SQLException {
        return vendaDAO.listarVendasDia(dataPesq);
    }
    
    public List<Venda> listarTodos() throws ClassNotFoundException, SQLException {
        return vendaDAO.listar();
    }
    
    public void inserirProduto(String nome, Integer quantidade, Double preco) throws ClassNotFoundException, SQLException {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);
        produtoDAO.inserir(produto);
    }
    
    public Produto buscarProdutoPorId(Integer id) throws ClassNotFoundException, SQLException {
        Produto produto = produtoDAO.buscarPorId(id);
        return produto;
    }
        
}
