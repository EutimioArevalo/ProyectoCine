<%-- 
    Document   : Pag_InfoPeli
    Created on : 30 jul. 2021, 12:16:12
    Author     : timoa
--%>

<%@page import="Controlador.DAO.CuentaDAO"%>
<%@page import="Controlador.DAO.PeliculaDAO"%>
<%@page import="Controlador.Utiles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pagina de Información de Peliculas</title>
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--CUSTOME CSS-->
        <link rel="stylesheet" href="infoPeli.css">
    </head>
    <%
        HttpSession sesion = request.getSession();

        String idPelicula = sesion.getAttribute("idPelicula").toString();
        String idCuenta = "";
        if (sesion.getAttribute("idCuenta") == null) {
            idCuenta = null;
        } else {
            idCuenta = sesion.getAttribute("idCuenta").toString();
        }

        PeliculaDAO peliculaDAO = new PeliculaDAO();
        CuentaDAO c = new CuentaDAO();

        if (request.getParameter("btnComprar") != null) {
            response.sendRedirect("../comprarTicket/seleccionarAsiento.jsp");
        }

    %>
    <body>
        <div class="container">
            <nav class="nav">
                <ul class="nav-menu">
                    <img src="../imagenes/logo.png" alt="Cine Logo" class="logo">
                    <li>
                        <a href="../../index.jsp">Regresar</a>
                    </li>
                </ul>
            </nav>

            <hr>
            <p><%=idCuenta%></p>
            <p><%=idPelicula%></p>
            <p><%=sesion.getAttribute("idRol")%></p>

            <header class="titulo">
                <%=peliculaDAO.buscar(Integer.valueOf(idPelicula)).getTitulo()%>
            </header>

            <div id="tarjeta">
                <div id="portada">
                    <img src=<%=peliculaDAO.buscar(Integer.valueOf(idPelicula)).getPortada()%> alt="peli1">
                </div>
                <div id="infoPeli">
                    <p><%=peliculaDAO.buscar(Integer.valueOf(idPelicula)).getSipnosis()%> </p>
                    <p><%=idCuenta%></p>
                </div>
            </div>

            <%
                String code = "https://www.youtube.com/embed/";
                Utiles u = new Utiles();
                code += u.obtenerenlace(peliculaDAO.buscar(Integer.valueOf(idPelicula)).getTrailer());
            %>
            <div id="general">
                <div id="video">
                    <iframe width="676" height="380" src=<%=code%>
                            title="YouTube video player" frameborder="0"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                            allowfullscreen></iframe>
                </div>

                <div id="tabla">
                    <table border="">
                        <tr>
                            <td colspan="2">02-08 Julio</td>
                        </tr>
                        <tr>
                            <td>Hora</td>
                            <td>Formato</td>
                        </tr>
                        <tr>
                            <td>16:30</td>
                            <td>4K2D</td>
                        </tr>
                        <tr>
                            <td>19:30</td>
                            <td>4K2D</td>
                        </tr>
                        <tr>
                            <td>14:50</td>
                            <td>2D</td>
                        </tr>
                    </table>
                </div>

                <div id="boton">
                    <form action="Pag_InfoPeli.jsp" method="POST">
                        <input type ="submit" name="btnComprar" value="Realizar Compra">
                    </form>
                </div>

            </div>

    </body>

</html>