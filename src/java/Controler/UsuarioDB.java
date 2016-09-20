/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Craitson
 */
public class UsuarioDB {

    private static String insereUser = "insert into usuario(nome, login, email, ativo, tipo) values (?,?,?,?,?)";
    public static  String sqlUsuario = "select * from usuario where login = ? and senha = ? and ativo = true";
    private static String sqlFlag = "update usuario set status = status + 1  where login = ?";
    private static String sqlQTDFlag = "select status from usuario where login = ?";
    private static String blqUser = "update usuario set ativo = false  where login = ?";
    private static String lmpFlag = "update usuario set status = 0 where login = ?";
    
    
    public static boolean limpaFlag(String user) {
        boolean flag = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(lmpFlag);
            pstmt.setString(1, user);
            int valor = pstmt.executeUpdate();
            if (valor == 1) {
                flag = true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro de Sql " + erro.getMessage());
        } finally {
            return flag;
        }
    }
    
    public static int getQtdFlags(String login) {
        int flag = 0;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(sqlQTDFlag);
            pstmt.setString(1, login);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                flag = Integer.parseInt(rs.getString("status"));
            }
        } catch (SQLException erro) {
            System.out.println("Erro SQL: " + erro.getMessage());
        } finally {
            return flag;
        }
    }
    
    public static boolean bloqUser(String user) {
        boolean blq = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(blqUser);
            pstmt.setString(1, user);
            int valor = pstmt.executeUpdate();
            if (valor == 1) {
                blq = true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro de Sql " + erro.getMessage());
        } finally {
            return blq;
        }
    }
    
    public static boolean flagUsuario(String user) {
        boolean flag = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(sqlFlag);
            pstmt.setString(1, user);
            int valor = pstmt.executeUpdate();
            if (valor == 1) {
                flag = true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro de Sql " + erro.getMessage());
        } finally {
            return flag;
        }
    }
    
    public static boolean insereUsuario(Usuario user) {
        boolean inseriu = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(insereUser);
            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getLogin());
            pstmt.setString(3, user.getEmail());
            pstmt.setBoolean(4, user.getAtivo());
            pstmt.setInt(5, user.getTipo());
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
    
    public static Usuario getUsuario(String login, String password) {
        Usuario usuario = null;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(sqlUsuario);
            pstmt.setString(1, login);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int auxCod = Integer.parseInt(rs.getString("codigo_usuario"));
                String auxNome = rs.getString("nome");
                String auxEmail = rs.getString("email");
                usuario = new Usuario(auxCod, auxNome, auxEmail);
            }

        } catch (SQLException erro) {
            System.out.println("Erro SQL: " + erro.getMessage());
        } finally {
            return usuario;
        }
    }
}
