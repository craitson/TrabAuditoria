/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controler.ClienteDB;
import Controler.ControllerData;
import Controler.LogDB;
import Modelo.Cidade;
import Modelo.Cliente;
import Modelo.JsonCliente;
import Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Craitson
 */
public class cadCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario us = (Usuario) session.getAttribute("usuario");
        processRequest(request, response);
        StringBuilder sb = new StringBuilder();
        String s;
        String senhaCript = "";

        while ((s = request.getReader().readLine()) != null) {
            sb.append(s);
            // System.out.println(sb.toString());
        }

        //String teste = "[{\"cep\":45678, \"nome\":\"teste\", \"estado\": \"teste\"}]";
        Gson gson = new GsonBuilder().create();

        Cliente cli = gson.fromJson(sb.toString(), JsonCliente.class).getCliente();

        boolean insere = ClienteDB.insereCliente(cli);

        ControllerData dt = new ControllerData();
        boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Cliente", "NOME", cli.getNome());
        boolean logs2 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Cliente", "SEXO", cli.getSexo());
        boolean logs3 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Cliente", "NASCIMENTO", cli.getNascimento());
        boolean logs4 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Cliente", "ENDERECO", cli.getEndereco());
        boolean logs5 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Cliente", "ATIVO", "" + cli.getAtivo());
        boolean logs6 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Cliente", "CEP", cli.getCep());
        boolean logs7 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Cliente", "SALDO", "" + cli.getSaldoDevedor());

//        
//        Produto prod = gson.fromJson(sb.toString(), JsonProduto.class).getProduto();
//        Produto produto = new Produto(prod.getEstoque(), prod.getDescricao(), prod.getPreco());
    }

}
