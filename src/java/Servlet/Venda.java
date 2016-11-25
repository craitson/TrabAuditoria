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
import Controler.vendaDB;
import Modelo.Cliente;
import Modelo.JsonCliente;
import Modelo.JsonProduto;
import Modelo.Produto;
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
public class Venda extends HttpServlet {

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
        ArrayList<Venda> list = vendaDB.getVendas();

        Gson gson = new GsonBuilder().create();
        String venda = gson.toJson(list, ArrayList.class);

        response.setContentType("application/json");
        response.getWriter().write(venda);

        ControllerData dt = new ControllerData();
        boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "C", "Venda", "CONSULTOU AS VENDAS", "");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario us = (Usuario) session.getAttribute("usuario");
        String cod = request.getParameter("cod");

        int codigo = Integer.parseInt(cod);

        Modelo.Venda venda = new Modelo.Venda();
        venda = vendaDB.getVenda(codigo);

        boolean excluir = vendaDB.excluiVenda(codigo);

        ControllerData dt = new ControllerData();
        boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Venda", "CODIGO_PRODUTO", "" + venda.getProduto().getCodigo());
        boolean logs2 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Venda", "CODIGO_CLIENTE", "" + venda.getCliente().getCodigo());
        boolean logs3 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Venda", "DATA", venda.getData());
        boolean logs4 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Venda", "QUANTIDADE", "" + venda.getQuantidade());
        boolean logs6 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Venda", "DATA_PAGAMENTO", venda.getData_pagamento());
        boolean logs5 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Venda", "VALOR_PAGO", "" + venda.getValor_pago());

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario us = (Usuario) session.getAttribute("usuario");
        StringBuilder sb = new StringBuilder();
        String s;

        while ((s = request.getReader().readLine()) != null) {
            sb.append(s);
            // System.out.println(sb.toString());
        }

        //String teste = "[{\"cep\":45678, \"nome\":\"teste\", \"estado\": \"teste\"}]";
        Gson gson = new GsonBuilder().create();

        ControllerData dt = new ControllerData();
        Modelo.Venda venda = gson.fromJson(sb.toString(), Modelo.Venda.class);

        Modelo.Venda venAntes = new Modelo.Venda();
        venAntes = vendaDB.getVenda(venda.getCodigo());

        boolean altera = vendaDB.alteraVenda(venda);

        boolean logs1 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Venda", "Cliente", "" + venAntes.getCliente().getCodigo(), "" + venda.getCliente().getCodigo());
        boolean logs2 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Venda", "Produto", "" + venAntes.getProduto().getCodigo(), "" + venda.getProduto().getCodigo());
        boolean logs3 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Venda", "Quantidade", "" + venAntes.getQuantidade(), "" + venda.getQuantidade());
        boolean logs4 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Venda", "Data", "" + venAntes.getData(), "" + venda.getData());
        boolean logs5 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Venda", "Data_Pagamento", "" + venAntes.getData_pagamento(), "" + venda.getData_pagamento());
        boolean logs6 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Venda", "Valor_Pago", "" + venAntes.getValor_pago(), "" + venda.getValor_pago());

    }
}
