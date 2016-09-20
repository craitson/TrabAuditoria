/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Craitson
 */
public class ClienteDB {
     private static String insereCliente = "insert into cliente(nome,sexo, nascimento,endereco,ativo,cep,saldo_devedor) values (?,?,?,?,?,?,?)";

    public static boolean insereCliente(Cliente cli) {
        boolean inseriu = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(insereCliente);
            pstmt.setString(1, cli.getNome());
            pstmt.setString(2, cli.getSexo());
            pstmt.setString(3, cli.getNascimento());
            pstmt.setString(4, cli.getEndereco());
            pstmt.setBoolean(5, cli.getAtivo());
            pstmt.setString(6, cli.getCep());
            pstmt.setFloat(7, cli.getSaldoDevedor());
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
