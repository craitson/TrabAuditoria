/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Modelo.Cliente;
import Modelo.Produto;
import Modelo.Usuario;
import Modelo.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Craitson
 */
public class vendaDB {

    private static String insereVenda = "insert into venda(codigo_produto, codigo_cliente, data, quantidade, data_pagamento, valor_pago) values (?,?,?,?,?,?)";
    private static String TodasVendas = "select b.*, a.nome from venda B JOIN cliente a ON (B.codigo_cliente = A.codigo_cliente)";
    private static String dell = "delete from venda where id_venda = ?";
    private static String altera = "update venda set codigo_cliente = ? , data = ?, quantidade = ?, data_pagamento = ?, valor_pago = ?, codigo_produto = ? where id_venda = ?";
    private static String get = "select * from venda where id_venda = ?";

    public static Venda getVenda(int cod) {
        Venda ven = new Venda();
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(get);
            pstmt.setInt(1, cod);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                Produto prod = new Produto();
                int auxCod = rs.getInt("id_venda");
                String auxData = rs.getString("data");
                int auxQunatidade = rs.getInt("quantidade");
                String auxDataPag = rs.getString("data_pagamento");
                float auxvalPag = rs.getFloat("valor_pago");
                cli.setCodigo(rs.getInt("codigo_cliente"));
                prod.setCodigo(rs.getInt("codigo_produto"));
                ven = new Venda(auxCod, auxData, auxQunatidade, auxDataPag, auxvalPag, prod, cli);

            }
        } catch (SQLException erro) {
            System.out.println("Erro de SQL " + erro.getMessage());
        } finally {
            return ven;
        }
    }

    public static boolean alteraVenda(Venda venda) {
        boolean alterou = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(altera);

            pstmt.setInt(1, venda.getCliente().getCodigo());
            pstmt.setString(2, venda.getData());
            pstmt.setInt(3, venda.getQuantidade());
            pstmt.setString(4, venda.getData_pagamento());
            pstmt.setFloat(5, venda.getValor_pago());
            pstmt.setInt(6, venda.getProduto().getCodigo());
            pstmt.setInt(7, venda.getCodigo());

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

    public static boolean excluiVenda(int cod) {
        boolean alterou = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(dell);
            pstmt.setInt(1, cod);
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

    public static boolean insereVenda(Venda venda) {
        boolean inseriu = false;
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(insereVenda);
            pstmt.setInt(1, venda.getProduto().getCodigo());
            pstmt.setInt(2, venda.getCliente().getCodigo());
            pstmt.setString(3, venda.getData());
            pstmt.setInt(4, venda.getQuantidade());
            pstmt.setString(5, venda.getData_pagamento());
            pstmt.setFloat(6, venda.getValor_pago());
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

    public static ArrayList getVendas() {
        ArrayList lista = new ArrayList();
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(TodasVendas);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                //Usuario user = new Usuario();
                Cliente cli = new Cliente();
                Produto prod = new Produto();
                int auxCod = rs.getInt("id_venda");
                String auxData = rs.getString("data");
                int auxQunatidade = rs.getInt("quantidade");
                String auxDataPag = rs.getString("data_pagamento");
                float auxvalPag = rs.getFloat("valor_pago");
                cli.setCodigo(rs.getInt("codigo_cliente"));
                cli.setNome(rs.getString("nome"));
                prod.setCodigo(rs.getInt("codigo_produto"));
                Venda vend = new Venda(auxCod, auxData, auxQunatidade, auxDataPag, auxvalPag, prod, cli);
                lista.add(vend);
            }
        } catch (SQLException erro) {
            System.out.println("Erro de SQL " + erro.getMessage());
        } finally {
            return lista;
        }
    }
}
