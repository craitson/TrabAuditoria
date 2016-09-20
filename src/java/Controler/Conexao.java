/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Craitson
 */
public class Conexao {

    private static String driver = "org.postgresql.Driver";
    private static String servidor = "localhost";
    private static String banco = "trabAuditoria";
    private static String usuario = "postgres";
    private static String senha = "1";
    //private static String url = "jdbc:postgres://" + servidor + ":3306/" + banco;
    private static String url = "jdbc:postgresql://"+ servidor +":5432/"+banco;

    public static Connection getConexao() {
        Connection conexao = null;

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);

        } catch (ClassNotFoundException erro) {
            System.out.println("Erro de driver " + erro.getMessage());
        } catch (SQLException erro) {
            System.out.println("Erro de SQL " + erro.getMessage());
        } finally {
            return conexao;
        }

    }

    public static void fechaConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException erro) {
                System.out.println("Erro ao fechar Conexao");
            }
        }

    }

}
