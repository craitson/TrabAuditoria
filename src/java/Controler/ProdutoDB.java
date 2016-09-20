/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Modelo.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Craitson
 */
public class ProdutoDB {

    private static String insereProduto = "insert into produto(descricao, preco, estoque) values (?,?,?)";

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
