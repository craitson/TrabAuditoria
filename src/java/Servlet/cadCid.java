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
import Modelo.Log;
import Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
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
@WebServlet(name = "cadCid", urlPatterns = {"/cadCid"})
public class cadCid extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        while ((s = request.getReader().readLine()) != null) {
            sb.append(s);
            //System.out.println(sb.toString());
        }

        //String teste = "[{\"cep\":45678, \"nome\":\"teste\", \"estado\": \"teste\"}]";
        Gson gson = new GsonBuilder().create();
        Cidade cidade = gson.fromJson(sb.toString(), JsonCidade.class).getCidade();

        Cidade cid = new Cidade(cidade.getCep(), cidade.getNome(), cidade.getEstado());
        boolean inseriu = CidadeDB.insereCidade(cid);
        
        if(inseriu){            
            ControllerData dt = new ControllerData();
            boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Cidade", "CEP", ""+cidade.getCep());
            boolean logs2 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Cidade", "NOME", ""+cidade.getNome());
            boolean logs3 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Cidade", "ESTADO", ""+cidade.getEstado());
        }
    }
}
