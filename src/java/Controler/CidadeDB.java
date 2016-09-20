/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Modelo.Cidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Craitson
 */
public class CidadeDB {
    
    
    private static String insereCidade = "insert into cidade(cep, nome, estado) values (?,?,?)";

    public static boolean insereCidade(Cidade cid) {
        boolean inseriu = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(insereCidade);
            pstmt.setInt(1, cid.getCep());
            pstmt.setString(2, cid.getNome());
            pstmt.setString(3, cid.getEstado());
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
