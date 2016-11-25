/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controler.CidadeDB;
import Controler.ControllerData;
import Controler.LogDB;
import Modelo.Cidade;
import Modelo.JsonCidade;
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
public class Cidades extends HttpServlet {

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
        ArrayList<Cidade> list = CidadeDB.getCidades();

        Gson gson = new GsonBuilder().create();
        String cidade = gson.toJson(list, ArrayList.class);

        response.setContentType("application/json");
        response.getWriter().write(cidade);

        ControllerData dt = new ControllerData();
         boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "C", "Cidade", "CONSULTOU AS CIDADES", "");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

        Cidade ccid = new Cidade();

        ccid = CidadeDB.getCid(codigo);

        boolean excluir = CidadeDB.excluiCid(cod);

        if (excluir) {

            ControllerData dt = new ControllerData();
            boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Cidade", "CEP", "" + ccid.getCep());
            boolean logs2 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Cidade", "NOME", "" + ccid.getNome());
            boolean logs3 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Cidade", "ESTADO", "" + ccid.getEstado());

        }

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
            //System.out.println(sb.toString());
        }

        //String teste = "[{\"cep\":45678, \"nome\":\"teste\", \"estado\": \"teste\"}]";
        Gson gson = new GsonBuilder().create();
        Cidade cidade = gson.fromJson(sb.toString(), JsonCidade.class).getCidade();

        Cidade ccid = new Cidade();

        ccid = CidadeDB.getCid(cidade.getCep());

        Cidade cid = new Cidade(cidade.getCep(), cidade.getNome(), cidade.getEstado());
        boolean alterou = CidadeDB.alteraCid(cid);

        if (alterou) {

            ControllerData dt = new ControllerData();
            boolean logs1 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Cidade", "CEP", "" + ccid.getCep(), "" + cidade.getCep());
            boolean logs2 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Cidade", "NOME", "" + ccid.getNome(), "" + cidade.getNome());
            boolean logs3 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Cidade", "ESTADO", "" + ccid.getEstado(), "" + cidade.getEstado());

        }
    }
}