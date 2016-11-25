/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controler.ControllerData;
import Controler.LogDB;
import Controler.ProdutoDB;
import Modelo.JsonProduto;
import Modelo.Produto;
import Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Craitson
 */
@WebServlet(name = "cadProd", urlPatterns = {"/cadProd"})
public class cadProd extends HttpServlet {

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
        StringBuilder sb = new StringBuilder();
        String s;
        String senhaCript = "";

        while ((s = request.getReader().readLine()) != null) {
            sb.append(s);
            // System.out.println(sb.toString());
        }

        //String teste = "[{\"cep\":45678, \"nome\":\"teste\", \"estado\": \"teste\"}]";
        Gson gson = new GsonBuilder().create();

        Produto prod = gson.fromJson(sb.toString(), JsonProduto.class).getProduto();
        Produto produto = new Produto(prod.getEstoque(), prod.getDescricao(), prod.getPreco());

        boolean inseriu = ProdutoDB.insereProduto(produto);

        if (inseriu) {
            ControllerData dt = new ControllerData();
            boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Produto", "DESCRICAO", produto.getDescricao());
            boolean logs2 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Produto", "PRECO", "" + produto.getPreco());
            boolean logs3 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Produto", "ESTOQUE", "" + produto.getEstoque());
        }

    }

}
