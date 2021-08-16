<%-- 
    Document   : Pag_InfoPeli
    Created on : 30 jul. 2021, 12:16:12
    Author     : timoa
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Cartelera"%>
<%@page import="java.util.List"%>
<%@page import="Controlador.DAO.CarteleraDAO"%>
<%@page import="Controlador.DAO.CuentaDAO"%>
<%@page import="Controlador.DAO.PeliculaDAO"%>
<%@page import="Controlador.Utiles"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pagina de Información de Peliculas</title>
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--CUSTOME CSS-->
        <link rel="stylesheet" href="infoPeli.css">
    </head>
    <%
        HttpSession sesion = request.getSession();

        String idPelicula = sesion.getAttribute("idPelicula").toString();
        List<Cartelera> listaCartelera = (ArrayList<Cartelera>) sesion.getAttribute("listaCartelera");

        String idCuenta = "";
        if (sesion.getAttribute("idCuenta") == null) {
            idCuenta = null;
        } else {
            idCuenta = sesion.getAttribute("idCuenta").toString();
        }

        PeliculaDAO peliculaDAO = new PeliculaDAO();
        CuentaDAO c = new CuentaDAO();
        CarteleraDAO carDAO = new CarteleraDAO();

        for (Cartelera ca : listaCartelera) {
            if (request.getParameter("btnSeleccionar" + ca.getIdCartelera()) != null) {

                sesion.setAttribute("idCartelera", ca.getIdCartelera());
                sesion.setAttribute("idSala", ca.getIdSala().getIdSala());

                response.sendRedirect("../comprarTicket/seleccionarAsiento.jsp");
            }
        }


    %>
    <body>
        <div class="container">
            <nav class="nav">
                <ul class="nav-menu">
                    <img src="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png" alt="Cine Logo" class="logo">
                    <li>
                        <a href="../../index.jsp">Regresar</a>
                    </li>
                </ul>
            </nav>

            <hr>

            <header class="titulo">
                <%=peliculaDAO.buscar(Integer.valueOf(idPelicula)).getTitulo()%>
            </header>

            <div id="tarjeta">
                <div id="portada">
                    <img src=<%=peliculaDAO.buscar(Integer.valueOf(idPelicula)).getPortada()%> alt="peli1">
                </div>
                <div id="infoPeli">
                    <p><%=peliculaDAO.buscar(Integer.valueOf(idPelicula)).getSipnosis()%> </p>
                </div>
            </div>
            <br>

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

                <div>
                    <table border="">
                        <TR><TH>Fecha de Estreno</TH>
                            <TD><%=peliculaDAO.buscar(Integer.valueOf(idPelicula)).getFechaEstreno()%></TD></TR>
                        <TR><TH>Duración</TH>
                            <TD><%=peliculaDAO.buscar(Integer.valueOf(idPelicula)).getDuracion()%></TD></TR>
                        <TR><TH>Genero</TH>
                            <TD><%=peliculaDAO.buscar(Integer.valueOf(idPelicula)).getGenero()%></TD></TR>
                        <TR><TH>Idioma</TH>
                            <TD><%=peliculaDAO.buscar(Integer.valueOf(idPelicula)).getIdioma()%></TD></TR>
                        <TR><TH>Director</TH>
                            <TD><%=peliculaDAO.buscar(Integer.valueOf(idPelicula)).getDirector()%></TD></TR>
                    </table>
                </div>
            </div>

            <br>
            <center><h1>RESERVA TU BOLETO!!!</h1></center>

            <div>

                <table border="">
                    <tr>
                        <td>Disponible Hasta</td>
                        <td>Horario</td>
                        <td>Precio</td>
                        <td>Resevar</td>
                    </tr>
                    <%for (Cartelera ca : listaCartelera) {
                    %>
                    <tr>

                        <td><%=ca.getDuracionCartelera()%></td>
                        <td><%=ca.getHorario()%></td>
                        <td>$<%=ca.getPrecio()%></td>
                        <td>
                            <form action="Pag_InfoPeli.jsp" method="POST">
                                <input type="submit" name="btnSeleccionar<%=ca.getIdCartelera()%>" value="Seleccionar"> 
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </table>
            </div>   

    </body>

</html>