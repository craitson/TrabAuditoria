/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controler.Criptografia;
import Controler.Email;
import Controler.EnvioEmail;
import Controler.UsuarioDB;
import Modelo.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
@WebServlet(name = "auth", urlPatterns = {"/auth"})
public class auth extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        //response.sendRedirect("login.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recebe user + senha do form
        String user = request.getParameter("user").trim();
        String pass = request.getParameter("password").trim();

        String senhacript = Criptografia.criptografar(pass);
        boolean trv = false;

        Usuario usuario = UsuarioDB.getUsuario(user, senhacript);
        //Email e = new Email();

        if (usuario == null) {
            //flag usuario + 1 status
            boolean flag = UsuarioDB.flagUsuario(user);
            if (flag) {
                //Verifica a quantidade que esta no status
                int qtd = UsuarioDB.getQtdFlags(user);
                if (qtd >= 3) {
                    //bloqueando usuario
                    boolean blq = UsuarioDB.bloqUser(user);
                    trv = true;
                   // EnvioEmail.enviaEmail(user);

                }

            }

            //irá mostra que o usuario foi bloqueado
            if (trv) {
                request.setAttribute("erro", "Usuário/Senha Inválidos");
                request.getRequestDispatcher("login.jsp").forward(request, response);

            }

            request.setAttribute("erro", "Usuário/Senha Inválidos");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {
            //se autenticou irá limapa o status
            boolean limpa = UsuarioDB.limpaFlag(user);
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            //Direciona para o index
            if (usuario.getTipo() == 0) {
                request.getRequestDispatcher("indexAdm.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }
}
