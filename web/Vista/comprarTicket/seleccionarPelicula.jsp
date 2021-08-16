<%-- 
    Document   : seleccionarPelicula
    Created on : 15 ago. 2021, 21:15:50
    Author     : timoa
--%>

<%@page import="Controlador.DAO.CarteleraDAO"%>
<%@page import="Controlador.JPA.CarteleraJpaController"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Cartelera"%>
<%@page import="Controlador.DAO.PeliculaDAO"%>
<%@page import="Controlador.DAO.CuentaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="../../styles.css">
        <link rel="stylesheet" href="../inicio/inicio.css">
    </head>

    <%

        HttpSession sesion = request.getSession();
        CuentaDAO c = new CuentaDAO();
        PeliculaDAO pDAO = new PeliculaDAO();
        CarteleraJpaController cajc = new CarteleraJpaController();
        CarteleraDAO car = new CarteleraDAO();

        List<Integer> Peliaux = new ArrayList<>();
        for (Cartelera ca : cajc.findCarteleraEntities()) {
            Peliaux.add(ca.getIdPelicula().getIdPelicula());
        }

        Set<Integer> hash = new HashSet<>(Peliaux);
        List<Integer> Paux = new ArrayList<>(hash);

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

        for (Integer p : Paux) {
            if (request.getParameter("btnMasInformacion" + p) != null) {
                sesion.setAttribute("idPelicula", p);
                sesion.setAttribute("listaCartelera", car.obtenerPorPelicula(p));
                response.sendRedirect("../informacionPelicula/Pag_InfoPeli.jsp");
            }
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

            <div id="peliculas">

                <%
                    int cont = 0;
                    for (Integer p : Paux) {
                %>

                <div id="tarjeta">
                    <div id="portada">
                        <img src=<%=pDAO.buscar(p).getPortada()%> alt="peli1">
                    </div>
                    <div id="infoPeli">
                        <h2><%=pDAO.buscar(p).getTitulo()%></h2>
                        <p><%=pDAO.buscar(p).getSipnosis()%></p>
                        <form action="seleccionarPelicula.jsp" method="POST">
                            <input type ="submit" name="btnMasInformacion<%=p%>" value="Más Información">
                        </form>
                    </div>
                </div>

                <% cont++;
                    }%>

            </div>

        </div>
    </body>
</html>
