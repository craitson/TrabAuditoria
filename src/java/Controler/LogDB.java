/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Craitson
 */
public class LogDB {
    
    private static String insereLog = "insert into log(id_user, data, tipo, tabela, campo, campo_depois) values (?,?,?,?,?,?)";
    private static String insereLogUp = "insert into log(id_user, data, tipo, tabela, campo, campo_antes ,campo_depois) values (?,?,?,?,?,?,?)";
    
    public static boolean Insert(int user, String data, String tipo, String tabela, String campo, String campo_depois) {
        boolean inseriu = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(insereLog);
            pstmt.setInt(1, user);
            pstmt.setString(2, data);
            pstmt.setString(3, tipo);
            pstmt.setString(4, tabela);
            pstmt.setString(5, campo);
            pstmt.setString(6, campo_depois);
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
    public static boolean Update(int user, String data, String tipo, String tabela, String campo,String campo_antes ,String campo_depois) {
        boolean inseriu = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(insereLogUp);
            pstmt.setInt(1, user);
            pstmt.setString(2, data);
            pstmt.setString(3, tipo);
            pstmt.setString(4, tabela);
            pstmt.setString(5, campo);
            pstmt.setString(6, campo_antes);
            pstmt.setString(7, campo_depois);
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
