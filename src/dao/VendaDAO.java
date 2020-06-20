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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Venda;
import modelo.Produto;

/**
 *
 * @author jean_
 */

public class VendaDAO {
    
    Statement stm;
    
    public VendaDAO() {
        
    }
    
    public void inserir(Venda venda) throws ClassNotFoundException, SQLException, ParseException {
        stm = ConexaoBanco.obterConexao().createStatement();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        
        String sql = "INSERT INTO Venda(dataVenda, qtdeVendida, valorTotal, idProduto) " +
                 "VALUES(?, ?, ?, ?)";
        Date data = (Date) formato.parse(venda.getDataVenda());
        PreparedStatement pstm = ConexaoBanco.obterConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pstm.setDate(1,  new java.sql.Date(data.getTime()));
        pstm.setInt(2, venda.getQtdeVenda());
        pstm.setDouble(3, venda.getValorTotal());
        pstm.setInt(4, venda.getProduto().getIdProduto());
        
        pstm.execute();
        
        ResultSet response = pstm.getGeneratedKeys();
        if( response.next() ){
            venda.setIdVenda(response.getInt(1));
        }
    }
         
    public Venda pesquisar(Integer idVenda) throws ClassNotFoundException, SQLException {
        stm = ConexaoBanco.obterConexao().createStatement();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        Venda venda = new Venda();
        
        String sql = "SELECT v.idVenda, v.dataVenda, v.qtdeVendida, v.valorTotal, v.idProduto"+
                " FROM Venda v"+
                " WHERE v.idVenda = ?";
        PreparedStatement pstm = ConexaoBanco.obterConexao().prepareStatement(sql);
        pstm.setInt(1, idVenda);
        
        pstm.execute();
        
        ResultSet response = pstm.getResultSet();
        if ( response.next() ){
            venda.setIdVenda(response.getInt("idVenda"));
            venda.setDataVenda(formato.format(response.getDate("dataVenda")));
            venda.setQtdeVenda(response.getInt("qtdeVendida"));
            venda.setValorTotal(response.getDouble("valorTotal"));
            Produto produto = new Produto();
            venda.setProduto(produto);
            venda.getProduto().setIdProduto(response.getInt("idProduto"));
        }
        return venda;
    }
 
    public List<Venda> listar() throws ClassNotFoundException, SQLException {
        Venda venda;
        stm = ConexaoBanco.obterConexao().createStatement();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        List<Venda> listaVendas = new ArrayList<>();
        
        String sql = "SELECT v.idVenda, v.dataVenda, v.qtdeVendida, v.valorTotal, p.idProduto, p.nome FROM Venda v, Produto p WHERE p.idProduto = v.idProduto";
        
        ResultSet response = stm.executeQuery(sql);
        
        while( response.next() ){
            venda = new Venda();
            venda.setIdVenda(response.getInt("idVenda"));
            venda.setDataVenda(formato.format(response.getDate("dataVenda")));
            venda.setQtdeVenda(response.getInt("qtdeVendida"));
            venda.setValorTotal(response.getDouble("valorTotal"));
            Produto produto = new Produto();
            venda.setProduto(produto);
            venda.getProduto().setIdProduto(response.getInt("idProduto"));
            venda.getProduto().setNome(response.getString("nome"));
            listaVendas.add(venda);
        }
            
        return listaVendas;
    }
    
    
    public List<Venda> listarVendasDia(String dataPesq) throws ClassNotFoundException, SQLException, ParseException{
        Venda venda;
        stm = ConexaoBanco.obterConexao().createStatement();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        List<Venda> listaVendas = new ArrayList<>();
        Date data = (Date) formato.parse(dataPesq);
        
        String sql = "SELECT v.idVenda, v.dataVenda, v.qtdeVendida, v.valorTotal, p.idProduto, p.nome FROM Venda v, Produto p WHERE p.idProduto = v.idProduto and v.dataVenda = '" + new java.sql.Date(data.getTime()) + "'";
        
        ResultSet response = stm.executeQuery(sql);
        
        while( response.next() ){
            venda = new Venda();
            venda.setIdVenda(response.getInt("idVenda"));
            venda.setDataVenda(formato.format(response.getDate("dataVenda")));
            venda.setQtdeVenda(response.getInt("qtdeVendida"));
            venda.setValorTotal(response.getDouble("valorTotal"));
            Produto produto = new Produto();
            venda.setProduto(produto);
            venda.getProduto().setIdProduto(response.getInt("idProduto"));
            venda.getProduto().setNome(response.getString("nome"));
            listaVendas.add(venda);
        }
            
        return listaVendas;
   }

}
