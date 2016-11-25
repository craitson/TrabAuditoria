<%-- 
    Document   : index
    Created on : 22/11/2016, 20:40:40
    Author     : Craitson
--%>

<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="trab_auditoria">
    <head>
        <title>Trabalho Auditoria </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="materialize/css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/css.css" type="text/css" rel="stylesheet" />
        <link rel="icon" href="img/shourt.png" type="image/gif" sizes="16x16">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

        <script type="text/javascript" src="js/angularjs/1.4.7/angular.min.js"></script>
        <script type="text/javascript" src="js/angularjs/1.4.7/angular-sanitize.min.js"></script>
    </head>
    <body>
        <%

            Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (usuario == null) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
                //response.sendRedirect("login.jsp");
            }
            

        %>
        <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>

        <!--cabecalho-->
        <div class="row">
            <nav>
                <div class="nav-wrapper grey lighten-3">
                    &nbsp;&nbsp;<img src="img/lm.png" style="width: 80px;height: 80px;">
                    <ul id="nav-mobile" class="right hide-on-med-and-down">

                        <li><span style="color: black"><%=usuario.getNome()%></span></li>
                        <li><span style="color: black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></li>
                        <li><a href="destroy"><span style="color: black">Sair</span></a></li>
                        <li><span style="color: black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></li>
                    </ul>

                </div>
            </nav>
        </div>


        <div class="row">

            <div class="col s3">

                <div ng-app = "mainApp">
                    <ul class="collapsible" data-collapsible="accordion">
                        <li>
                            <a href="#viewClientes"><div class="collapsible-header"><i class="fa fa-child"></i>Clientes</div></a>
                        <li>
                            <a href="#viewVendas"><div class="collapsible-header"><i class="fa fa-shopping-basket"></i>Vendas</div></a>
                        </li>
                        
                    </ul>
                </div>
            </div>
            <!--<div class="col s8" >-->
            <div class="col s8" ng-view="conteudo">




            </div>

        </div>




        <script type="text/javascript" src="js/angularjs/1.4.7/angular-route.min.js"></script>
        <script src="js/main.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>


    </body>
</html>
