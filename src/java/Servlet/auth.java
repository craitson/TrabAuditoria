/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controler.Email;
import Controler.UsuarioDB;
import Modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Craitson
 */
@WebServlet(name = "auth", urlPatterns = {"/auth"})
public class auth extends HttpServlet {
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //recebe user + senha do form
        String user = request.getParameter("user").trim();
        String pass = request.getParameter("password").trim();

        Usuario usuario = UsuarioDB.getUsuario(user, pass);
        Email e = new Email();

        if (usuario == null) {
            //flag usuario + 1 status
            boolean flag = UsuarioDB.flagUsuario(user);
            if (flag) {
                //Verifica a quantidade que esta no status
                int qtd = UsuarioDB.getQtdFlags(user);
                if (qtd > 3) {
                    //bloqueando usuario
                    boolean blq = UsuarioDB.bloqUser(user);
                    //Enviar email e bloquear usuario
                    e.enviaEmail(user);
                } 
            }
            request.setAttribute("erro", "true");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {
            //se autenticou irá limapa o status
            boolean limpa = UsuarioDB.limpaFlag(user);

            //Direciona para o index
            request.getRequestDispatcher("index.jsp").forward(request, response);
            

        }

    }

}
