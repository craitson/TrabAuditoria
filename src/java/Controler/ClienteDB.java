/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Craitson
 */
public class ClienteDB {

    private static String insereCliente = "insert into cliente(nome,sexo, nascimento,endereco,ativo,cep,saldo_devedor) values (?,?,?,?,?,?,?)";
    private static String todosClientes = "select * from cliente";
    private static String excluirClientes = "delete from cliente where codigo_cliente = ?";
    private static String altera = "update cliente set nome = ?, sexo = ?, nascimento = ?, endereco = ? ,ativo = ?, cep = ? where codigo_cliente = ?";
    private static String getCli = "select * from cliente where codigo_cliente = ?";
    
    
    public static boolean alteraCli(Cliente cli) {
        boolean alterou = false;
        try {
            String a = "";
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(altera);
            pstmt.setString(1, cli.getNome());
            pstmt.setString(2, cli.getSexo());
            pstmt.setString(3, cli.getNascimento());
            pstmt.setString(4, cli.getEndereco());
            pstmt.setBoolean(5, cli.getAtivo());
            pstmt.setString(6, a=""+cli.getCep());
            pstmt.setInt(7, cli.getCodigo());

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

    public static boolean excluiCli(int dom) {
        boolean alterou = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(excluirClientes);
            pstmt.setInt(1, dom);
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

    public static ArrayList getClientes() {
        ArrayList lista = new ArrayList();
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(todosClientes);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int auxCodigo = rs.getInt("codigo_cliente");
                String auxNome = rs.getString("nome");
                String auxSexo = rs.getString("sexo");
                String auxNasc = rs.getString("nascimento");
                String auxEnd = rs.getString("endereco");
                boolean auxAtivo = rs.getBoolean("ativo");
                String auxCep = rs.getString("cep");
                float auxSaldo = rs.getFloat("saldo_devedor");
                Cliente cli = new Cliente(auxCodigo, auxNome, auxEnd, auxSexo, auxNasc, auxAtivo, auxSaldo, auxCep);
                // Dominio dominio = new Dominio(auxC   odigo, auxNome, auxIpServer, auxPort, auxExcluido);
                lista.add(cli);
            }
        } catch (SQLException erro) {
            System.out.println("Erro de SQL " + erro.getMessage());
        } finally {
            return lista;
        }
    }
    
    public static Cliente getClient(int cod) {
        Cliente cli = new Cliente();
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(getCli);
            pstmt.setInt(1, cod);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int auxCodigo = rs.getInt("codigo_cliente");
                String auxNome = rs.getString("nome");
                String auxSexo = rs.getString("sexo");
                String auxNasc = rs.getString("nascimento");
                String auxEnd = rs.getString("endereco");
                boolean auxAtivo = rs.getBoolean("ativo");
                String auxCep = rs.getString("cep");
                float auxSaldo = rs.getFloat("saldo_devedor");
                cli = new Cliente(auxCodigo, auxNome, auxEnd, auxSexo, auxNasc, auxAtivo, auxSaldo, auxCep);
            }
        } catch (SQLException erro) {
            System.out.println("Erro de SQL " + erro.getMessage());
        } finally {
            return cli;
        }
    }

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
