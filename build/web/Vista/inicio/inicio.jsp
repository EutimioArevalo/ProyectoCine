<%-- 
    Document   : inicio
    Created on : 5 ago. 2021, 23:41:01
    Author     : timoa
--%>

<%@page import="Controlador.DAO.CuentaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Menu Inicio</title>
        <!--FONT AWESOME-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--VENTANA PERSONALIZADA CSS-->
        <link rel="stylesheet" href="inicio.css">
    </head>

    <%

        HttpSession sesion = request.getSession();
        String usuario = sesion.getAttribute("usuario").toString();
        String id = sesion.getAttribute("id").toString();
        CuentaDAO c = new CuentaDAO();

        if (request.getParameter("btnCerrarSes") != null) {
            
        }

    %>

    <body>
        <div class="container">
            <nav class="nav-main">
                <img src="https://www.pngkit.com/png/full/786-7863517_para-cine-logo-de-cine-colombia-png.png" alt="Cine LOGO" class="logo">
                <ul class="nav-menu">
                    <%                        if (c.buscarROl(sesion.getAttribute("usuario").toString()) == 2) {

                    %>
                    <li>
                        <a href="../inicio/inicio.jsp">Inicio</a>
                    </li>

                    <li>
                        <a href="../registrarse/Pag_Registrarse.jsp">Modificar Información</a>
                    </li>

                    <li>
                        <a href="../comprarTicket/comprarTicket.jsp">Vender Ticket</a>
                    </li>

                    <%} else {%>
                    <li>
                        <a href="../inicio/inicio.jsp">Inicio</a>
                    </li>
                    <li>
                        <a href="../ModificarCartelera/ModificarCartelera.jsp">Modificar Carteleras</a>
                    </li>
                    <li>
                        <a href="../adminPeliculas/adminPeliculas.jsp">Administrar Peliculas</a>
                    </li>
                    <li>
                        <a href="../AdministrarEmpleados/AdministrarEmpleados.jsp">Administrar Empleados</a>
                    </li>
                    <li>
                        <a href="../adminsalas/adminsala.jsp">Gestionar Sala</a>
                    </li>
                    <li>
                        <a href="../comprarTicket/comprarTicket.jsp">Vender Ticket</a>
                    </li>
                    <li>
                        <a href="../registrarse/Pag_Registrarse.jsp">Modificar Información</a>
                    </li>
                    <li>
                        <a href="../adminsnacks/adminsnacks.jsp">Administrar Snacks</a>
                    </li>
                    <%}%>
                </ul>
            </nav>

            <hr>

            <div>
                <center>
                    <h1>Bienvendido <%=usuario%></h1>
                    <form>
                        <p>CERRAR SESION: <input type="submit" name="btnCerrarSes" value="Cerrar Sesión"></p>
                    </form>
                </center>

            </div>

        </div>
    </body>
</html>
