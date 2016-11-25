/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controler.ControllerData;
import Controler.LogDB;
import Controler.vendaDB;
import Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Craitson
 */
public class cadVenda extends HttpServlet {

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
        processRequest(request, response);

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

        venda.setData(dt.pegaDataHora());

        boolean insere = vendaDB.insereVenda(venda);

        boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Venda", "CODIGO_PRODUTO", "" + venda.getProduto().getCodigo());
        boolean logs2 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Venda", "CODIGO_CLIENTE", "" + venda.getCliente().getCodigo());
        boolean logs3 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Venda", "DATA", venda.getData());
        boolean logs4 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Venda", "QUANTIDADE", "" + venda.getQuantidade());
        boolean logs6 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Venda", "DATA_PAGAMENTO", venda.getData_pagamento());
        boolean logs5 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Venda", "VALOR_PAGO", "" + venda.getValor_pago());

    }

}
