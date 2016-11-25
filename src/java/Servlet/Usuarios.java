/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controler.ControllerData;
import Controler.Criptografia;
import Controler.LogDB;
import Controler.ProdutoDB;
import Controler.UsuarioDB;
import Modelo.JsonUsuario;
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
public class Usuarios extends HttpServlet {

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
        ArrayList<Usuarios> list = UsuarioDB.getUsuarios();

        Gson gson = new GsonBuilder().create();
        String usuarios = gson.toJson(list, ArrayList.class);

        response.setContentType("application/json");
        response.getWriter().write(usuarios);

        ControllerData dt = new ControllerData();
         boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "C", "Usuario", "CONSULTOU OS USUARIOS", "");
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

        Usuario usuario = new Usuario();
        usuario = UsuarioDB.getUsu(codigo);

        boolean excluir = UsuarioDB.excluiUser(codigo);

        if (excluir) {

            ControllerData dt = new ControllerData();
            boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Usuario", "NOME", "" + usuario.getNome());
            boolean logs2 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Usuario", "LOGIN", "" + usuario.getLogin());
            boolean logs3 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Usuario", "SENHA", "" + usuario.getSenha());
            boolean logs4 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Usuario", "ATIVO", "" + usuario.getAtivo());
            boolean logs5 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Usuario", "TIPO", "" + usuario.getTipo());
            boolean logs6 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Usuario", "EMAIL", "" + usuario.getEmail());
            boolean logs7 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "D", "Usuario", "CODIGO", "" + usuario.getEmail());
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

        Usuario user = gson.fromJson(sb.toString(), JsonUsuario.class).getUsuario();

        System.out.println(user.getAtivo());

        //criptgrafa senha MD5
        senhaCript = Criptografia.criptografar(user.getSenha());

        Usuario US = new Usuario(user.getNome(), user.getLogin(), user.getEmail(), user.getAtivo(), user.getTipo(), senhaCript, user.getCodigo());

        Usuario usuario = new Usuario();
        usuario = UsuarioDB.getUsu(US.getCodigo());

        boolean inseriu = UsuarioDB.alterUsu(US);

        if (inseriu) {
            ControllerData dt = new ControllerData();
            boolean logs1 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Usuario", "NOME", "" + usuario.getNome(), US.getNome());
            boolean logs2 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Usuario", "LOGIN", "" + usuario.getLogin(), US.getLogin());
            boolean logs3 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Usuario", "SENHA", "" + usuario.getSenha(), US.getSenha());
            boolean logs4 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Usuario", "ATIVO", "" + usuario.getAtivo(), ""+US.getAtivo());
            boolean logs5 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Usuario", "TIPO", "" + usuario.getTipo(), ""+US.getTipo());
            boolean logs6 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Usuario", "EMAIL", "" + usuario.getEmail(), US.getEmail());
            boolean logs7 = LogDB.Update(us.getCodigo(), dt.pegaDataHora(), "U", "Usuario", "CODIGO", "" + usuario.getCodigo(), ""+US.getCodigo());
        }

    }
}
