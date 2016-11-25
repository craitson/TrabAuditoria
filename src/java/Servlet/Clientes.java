/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controler.ClienteDB;
import Controler.ControllerData;
import Controler.LogDB;
import Controler.ProdutoDB;
import Modelo.Cliente;
import Modelo.JsonCliente;
import Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Craitson
 */
public class Clientes extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario us = (Usuario) session.getAttribute("usuario");

        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        ArrayList<Clientes> list = ClienteDB.getClientes();

        Gson gson = new GsonBuilder().create();
        String clientes = gson.toJson(list, ArrayList.class);

        response.setContentType("application/json");
        response.getWriter().write(clientes);

        ControllerData dt = new ControllerData();
        boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "C", "Cliente", "CONSULTOU OS CLIENTES", "");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario us = (Usuario) session.getAttribute("usuario");

        String cod = request.getParameter("cod");

        int codigo = Integer.parseInt(cod);

        Cliente cli = new Cliente();
        cli = ClienteDB.getClient(codigo);

        boolean excluir = ClienteDB.excluiCli(codigo);

        if (excluir) {
            ControllerData dt = new ControllerData();
            boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Cliente", "NOME", cli.getNome());
            boolean logs2 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Cliente", "SEXO", cli.getSexo());
            boolean logs3 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Cliente", "NASCIMENTO", cli.getNascimento());
            boolean logs4 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Cliente", "ENDERECO", cli.getEndereco());
            boolean logs5 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Cliente", "ATIVO", "" + cli.getAtivo());
            boolean logs6 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Cliente", "CEP", cli.getCep());
            boolean logs7 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Cliente", "SALDO", "" + cli.getSaldoDevedor());
            boolean logs8 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Cliente", "CODIGO", "" + cli.getCodigo());
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
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

        Cliente cliiii = gson.fromJson(sb.toString(), JsonCliente.class).getCliente();

        Cliente cli = new Cliente();
        cli = ClienteDB.getClient(cliiii.getCodigo());

        boolean insere = ClienteDB.alteraCli(cliiii);

        if (insere) {

            ControllerData dt = new ControllerData();
            boolean logs1 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Cliente", "NOME", cli.getNome(), cliiii.getNome());
            boolean logs2 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Cliente", "SEXO", cli.getSexo(), cliiii.getSexo());
            boolean logs3 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Cliente", "NASCIMENTO", cli.getNascimento(), cliiii.getNascimento());
            boolean logs4 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Cliente", "ENDERECO", cli.getEndereco(), cliiii.getEndereco());
            boolean logs5 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Cliente", "ATIVO", "" + cli.getAtivo(), "" + cliiii.getAtivo());
            boolean logs6 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Cliente", "CEP", cli.getCep(), cliiii.getCep());
            boolean logs7 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Cliente", "SALDO", "" + cli.getSaldoDevedor(), "" + cliiii.getSaldoDevedor());
            boolean logs8 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Cliente", "CODIGO", "" + cli.getCodigo(), "" + cliiii.getCodigo());

        }
    }
}
