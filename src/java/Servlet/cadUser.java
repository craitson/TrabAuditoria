/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controler.ControllerData;
import Controler.Criptografia;
import Controler.LogDB;
import Controler.UsuarioDB;
import Modelo.JsonUsuario;
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
@WebServlet(name = "cadUser", urlPatterns = {"/cadUser"})
public class cadUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

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

        Usuario usuario = new Usuario(user.getNome(), user.getLogin(), user.getEmail(), user.getAtivo(), user.getTipo(), senhaCript);
        boolean inseriu = UsuarioDB.insereUsuario(usuario);

        if (inseriu) {
            ControllerData dt = new ControllerData();
            boolean logs1 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Usuario", "NOME", "" + usuario.getNome());
            boolean logs2 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Usuario", "LOGIN", "" + usuario.getLogin());
            boolean logs3 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Usuario", "SENHA", "" + usuario.getSenha());
            boolean logs4 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Usuario", "ATIVO", "" + usuario.getAtivo());
            boolean logs5 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Usuario", "TIPO", "" + usuario.getTipo());
            boolean logs6 = LogDB.Insert(us.getCodigo(), dt.pegaDataHora(), "I", "Usuario", "EMAIL", "" + usuario.getEmail());
        }

    }
}
