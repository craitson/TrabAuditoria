/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Modelo.Cidade;
import Modelo.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Craitson
 */
public class ProdutoDB {

    private static String insereProduto = "insert into produto(descricao, preco, estoque) values (?,?,?)";
    private static String sqlTodos = "select * from produto";
    private static String excluirProd = "delete from produto where codigo_produto = ?";
    private static String altera = "update produto set descricao = ?, preco = ?, estoque = ? where codigo_produto = ?";
    private static String sqlProd = "select * from produto where codigo_produto = ?";

    public static Produto getProd(int cod) {
        Produto pro = new Produto();
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(sqlProd);
            pstmt.setInt(1, cod);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int auxCod = rs.getInt("codigo_produto");
                String auxNome = rs.getString("descricao");
                float auxPre = rs.getFloat("preco");
                int auxEstoq = rs.getInt("estoque");
                pro = new Produto(auxCod, auxEstoq, auxNome, auxPre);

            }
        } catch (SQLException erro) {
            System.out.println("Erro de SQL " + erro.getMessage());
        } finally {
            return pro;
        }
    }

    public static boolean alteraProd(Produto prod) {
        boolean alterou = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(altera);
            pstmt.setString(1, prod.getDescricao());
            pstmt.setFloat(2, prod.getPreco());
            pstmt.setInt(3, prod.getEstoque());
            pstmt.setInt(4, prod.getCodigo());

            int valor = pstmt.executeUpdate();
            if (valor == 1) {
                alterou = true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro de Sql " + erro.getMessage());
        } finally {
            return alterou;
        }
    }

    public static boolean excluiProd(int prod) {
        boolean alterou = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(excluirProd);
            pstmt.setInt(1, prod);
            int valor = pstmt.executeUpdate();
            if (valor == 1) {
                alterou = true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro de Sql " + erro.getMessage());
        } finally {
            return alterou;
        }
    }

    public static ArrayList getProdutos() {
        ArrayList lista = new ArrayList();
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(sqlTodos);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int auxCod = rs.getInt("codigo_produto");
                String auxNome = rs.getString("descricao");
                float auxPre = rs.getFloat("preco");
                int auxEstoq = rs.getInt("estoque");
                Produto prod = new Produto(auxCod, auxEstoq, auxNome, auxPre);
                //Cidade cid = new Cidade(auxCep, auxNome, auxEst);
                lista.add(prod);
            }
        } catch (SQLException erro) {
            System.out.println("Erro de SQL " + erro.getMessage());
        } finally {
            return lista;
        }
    }

    public static boolean insereProduto(Produto prod) {
        boolean inseriu = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(insereProduto);
            pstmt.setString(1, prod.getDescricao());
            pstmt.setFloat(2, prod.getPreco());
            pstmt.setInt(3, prod.getEstoque());
            int valor = pstmt.executeUpdate();
            if (valor == 1) {
                inseriu = true;
            }

        } catch (SQLException erro) {
            System.out.println("Erro SQL: " + erro.getMessage());
        } finally {
            return inseriu;
        }
    }

}
