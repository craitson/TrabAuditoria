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
public class Produtos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario us = (Usuario) session.getAttribute("usuario");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        ArrayList<Produtos> list = ProdutoDB.getProdutos();

        Gson gson = new GsonBuilder().create();
        String produto = gson.toJson(list, ArrayList.class);

        response.setContentType("application/json");
        response.getWriter().write(produto);

        ControllerData dt = new ControllerData();
        boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "C", "Produto", "CONSULTOU OS PRODUTOS", "");
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

        Produto pd = new Produto();
        pd = ProdutoDB.getProd(codigo);

        boolean excluir = ProdutoDB.excluiProd(codigo);

        if (excluir) {

            ControllerData dt = new ControllerData();
            boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Produto", "NOME", "" + pd.getDescricao());
            boolean logs2 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Produto", "CODIGO", "" + pd.getCodigo());
            boolean logs3 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Produto", "ESTOQUE", "" + pd.getEstoque());
            boolean logs4 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Produto", "PRECO", "" + pd.getPreco());

        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
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

        Produto pd = new Produto();
        pd = ProdutoDB.getProd(prod.getCodigo());

        boolean altera = ProdutoDB.alteraProd(prod);

        if (altera) {
            ControllerData dt = new ControllerData();
            boolean logs1 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Produto", "NOME", "" + pd.getDescricao(), produto.getDescricao());
            boolean logs2 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Produto", "CODIGO", "" + pd.getCodigo(), "" + prod.getCodigo());
            boolean logs3 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Produto", "ESTOQUE", "" + pd.getEstoque(), "" + produto.getEstoque());
            boolean logs4 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Produto", "PRECO", "" + pd.getPreco(), "" + produto.getPreco());

        }
    }
}
