<%-- 
    Document   : inicio
    Created on : 5 ago. 2021, 23:41:01
    Author     : timoa
--%>

<%@page import="Controlador.DAO.CuentaDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Menu Inicio</title>
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
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
        CuentaDAO c = new CuentaDAO();

        String idCuenta = "";
        if (sesion.getAttribute("idCuenta") == null) {
            idCuenta = null;
        } else {
            idCuenta = sesion.getAttribute("idCuenta").toString();
        }

        String idRol = "";
        if (sesion.getAttribute("idRol") == null) {
            idRol = null;
        } else {
            idRol = sesion.getAttribute("idRol").toString();
        }
        
        if (request.getParameter("btnCerrarSes") != null) {
            sesion.setAttribute("idCuenta", null);
            sesion.setAttribute("idRol", null);
            response.sendRedirect("../../index.jsp");
        }
        
if(request.getParameter("btnRegistro") != null){
response.sendRedirect("registro.jsp");
        }

    %>

    <body>
        <div class="container">
            <nav class="nav-main">
                <img src="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png" alt="Cine LOGO" class="logo">
                <ul class="nav-menu">
                    <%                        if (Integer.valueOf(idRol) == 2) {

                    %>
                    <li>
                        <a href="../inicio/inicio.jsp">Inicio</a>
                    </li>

                    <li>
                        <a href="../registrarse/Pag_Registrarse.jsp">Modificar Información</a>
                    </li>

                    <li>
                        <a href="../comprarTicket/seleccionarPelicula.jsp">Vender Ticket</a>
                    </li>

                    <%} else {%>
                    <li>
                        <a href="../inicio/inicio.jsp">Inicio</a>
                    </li>
                    <li>
                        <a href="../ListaCarteleras/ListaCarteleras.jsp">Modificar Carteleras</a>
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
                        <a href="../comprarTicket/seleccionarPelicula.jsp">Vender Ticket</a>
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
                    <h1>Bienvendido <%=c.buscarCuentaId(Integer.valueOf(idCuenta)).getUsuario()%></h1>
                    <form>
                        <p>CERRAR SESION: <input type="submit" name="btnCerrarSes" value="Cerrar Sesión"></p>
                        <p>VER REGISTRO DE VENTAS: <input type="submit" name="btnRegistro" value="Registro de Ventas"></p>
                    </form>
                </center>

            </div>

        </div>
    </body>
</html>
