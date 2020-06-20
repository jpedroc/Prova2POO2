/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.Produto;
import modelo.Venda;

/**
 *
 * @author jean_
 */
public class ProdutoDAO {
    
    Statement stm;
    
    public ProdutoDAO() {
        
    }
    
    public void inserir(Produto produto) throws ClassNotFoundException, SQLException {
        stm = ConexaoBanco.obterConexao().createStatement();
        
        String sql = "INSERT INTO Produto(nome, quantidade, preco) " +
                 "VALUES(?, ?, ?)";
        
        PreparedStatement pstm = ConexaoBanco.obterConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pstm.setString(1, produto.getNome());
        pstm.setInt(2, produto.getQuantidade());
        pstm.setDouble(3, produto.getPreco());
        
        pstm.execute();
        
        ResultSet response = pstm.getGeneratedKeys();
        if( response.next() ){
            produto.setIdProduto(response.getInt(1));
        }
    }
    
    public Produto buscarPorId(Integer id) throws ClassNotFoundException, SQLException {
        stm = ConexaoBanco.obterConexao().createStatement();
        Venda venda = new Venda();
        Produto produto = new Produto();
        
        String sql = "SELECT p.idProduto, p.nome, p.quantidade, p.preco"+
                " FROM Produto p"+
                " WHERE p.idProduto = ?";
        
        PreparedStatement pstm = ConexaoBanco.obterConexao().prepareStatement(sql);
        pstm.setInt(1, id);
        
        pstm.execute();
        
        ResultSet response = pstm.getResultSet();
        if( response.next() ){
            produto = new Produto(response.getInt("idProduto"), response.getString("nome"), response.getInt("quantidade"), response.getDouble("preco"));
        }
        
        return produto;
    }
   
    public List<Produto> listar() throws ClassNotFoundException, SQLException {
        Produto produto;
        stm = ConexaoBanco.obterConexao().createStatement();
        List<Produto> listaProdutos = new ArrayList<>();
        
        String sql = "SELECT * FROM Produto";
        
        ResultSet response = stm.executeQuery(sql);
        
        while( response.next() ){
            produto = new Produto( response.getInt("idProduto"), response.getString("nome"), response.getInt("quantidade"), response.getDouble("preco"));
            listaProdutos.add(produto);
        }
            
        return listaProdutos;
    }   
  
    
}
