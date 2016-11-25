/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Modelo.Cidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Craitson
 */
public class CidadeDB {

    private static String insereCidade = "insert into cidade(cep, nome, estado) values (?,?,?)";
    private static String sqlBuscaCeps = "select * from cidade";
    private static String sqlBuscaCe = "select * from cidade where cep = ?";
    private static String excluirCid = "delete from cidade where cep = ?";
    private static String altera = "update cidade set nome = ?, estado = ? where cep = ?";

     public static boolean alteraCid(Cidade cid) {
        boolean alterou = false;
        try {
            String a = "";
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(altera);
            pstmt.setString(1, cid.getNome());
            pstmt.setString(2, cid.getEstado());
            pstmt.setString(3, a = ""+cid.getCep());
           
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
    
    public static boolean excluiCid(String cep) {
        boolean alterou = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(excluirCid);
            pstmt.setString(1, cep);
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

    public static Cidade getCid(int cep) {
        Cidade cid = new Cidade();
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(sqlBuscaCe);
            pstmt.setString(1, ""+cep);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int auxCep = rs.getInt("cep");
                String auxNome = rs.getString("nome");
                String auxEst = rs.getString("estado");
                cid = new Cidade(auxCep, auxNome, auxEst);
                
            }
        } catch (SQLException erro) {
            System.out.println("Erro de SQL " + erro.getMessage());
        } finally {
            return cid;
        }
    }

    
    public static ArrayList getCidades() {
        ArrayList lista = new ArrayList();
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(sqlBuscaCeps);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int auxCep = rs.getInt("cep");
                String auxNome = rs.getString("nome");
                String auxEst = rs.getString("estado");
                Cidade cid = new Cidade(auxCep, auxNome, auxEst);
                lista.add(cid);
            }
        } catch (SQLException erro) {
            System.out.println("Erro de SQL " + erro.getMessage());
        } finally {
            return lista;
        }
    }
}
