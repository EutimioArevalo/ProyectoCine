<%-- 
    Document   : adminsala
    Created on : 30 jul. 2021, 12:14:28
    Author     : timoa
--%>

<%@page import="org.apache.catalina.ha.ClusterSession"%>
<%@page import="Modelo.Sala"%>
<%@page import="java.util.List"%>
<%@page import="Controlador.JPA.SalaJpaController"%>
<%@page import="Controlador.DAO.SalaDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>vista_sala</title>
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">

        <!--FONT AWESOME-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="../inicio/inicio.css">
        <link rel="stylesheet" href="../adminsalas/adminsala.css">
    </head>
    <%
        HttpSession sesion = request.getSession();

        SalaDAO sdao = new SalaDAO();
        SalaJpaController ctrlSala = new SalaJpaController();
        List<Sala> aux = ctrlSala.findSalaEntities();

        //System.out.println("Estoy en: "+sdao.obtenerSala(1).getIdSala());
        String idSala = "";
        if (sesion.getAttribute("idSala") != null) {
            idSala = sesion.getAttribute("idSala").toString();
            System.out.println(idSala);
        } else {
            idSala = null;
            //System.out.println(idSala);
        }

        if (request.getParameter("btnAgregar") != null) {
            int filas = Integer.valueOf(request.getParameter("snFilas"));
            int columnas = Integer.valueOf(request.getParameter("snColumnas"));
            int nroAsientos = filas * columnas;
            int nroSala = aux.size() + 1;
            sdao.agregar(nroAsientos, nroSala, filas, columnas);
            response.sendRedirect("../../Vista/adminsalas/adminsala.jsp");
        }

        if (request.getParameter("btnModificar") != null) {
            int filas = Integer.valueOf(request.getParameter("snFilas"));
            int columnas = Integer.valueOf(request.getParameter("snColumnas"));
            int nroAsientos = filas * columnas;
            
            String estado = "";
            if (request.getParameter("disponibilidad").toString().equals("Disponible")) {
                estado = "activo";
            } else {
                estado = "inactivo";
            }
            sdao.editar(Integer.valueOf(idSala), nroAsientos, filas, columnas, estado);
            out.print("<script>alert('Editado con exito');</script>");
            sesion.setAttribute("idSala", null);
            response.sendRedirect("../../Vista/adminsalas/adminsala.jsp");
        }

        if (request.getParameter("btnSeleccionar") != null) {
            for (int i = 1; i <= aux.size(); i++) {
                if (request.getParameter("nroSalas").toString().equalsIgnoreCase("Sala: " + i)) {
                    //System.out.println("Estoy en la sala: " + i);
                    System.out.println(sdao.obtenerSala(i).getIdSala());
                    sesion.setAttribute("idSala", sdao.obtenerSala(i).getIdSala());
                    response.sendRedirect("../../Vista/adminsalas/adminsala.jsp");
                }
            }
        }


    %>

    <body>

        <div class="container">
            <nav class="nav-main">
                <img src="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png" alt="Cine LOGO" class="logo">
                <ul class="nav-menu">
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
                </ul>
            </nav>
            <hr>

            <h5>Administracion salas:</h5>
            <div class="Lista_despegable"> <!--inicio box-->
                <p><%= (idSala != null ? "Sala: " + idSala : "Salas:")%></p>

                <form action="adminsala.jsp" method="post">

                    <% if (idSala == null) {
                    %>

                    <select name="nroSalas" style="width: 130px;">
                        <option></option>
                        <%                                    for (Sala sala : aux) {
                        %>

                        <option>Sala: <%=sala.getNroSala()%></option>
                        <%}%>
                    </select>
                    <br>
                    <input name="btnSeleccionar" type="submit" value="Seleccionar Sala">

                    <p>Filas:</p>
                    <input name="snFilas" type="number" min="3" id="idFilas">

                    <p>Columnas:</p>
                    <input name="snColumnas" type="number" min="7" id="idColumnas">
                    <br>
                    <input class="buttons" name="btnAgregar" type="submit" value="Agregar sala">

                    <%} else {

                    %>

                    <select name="nroSalas" style="width: 130px;">
                        <option></option>
                        <%                                    for (Sala sala : aux) {
                        %>

                        <option>Sala: <%=sala.getNroSala()%></option>
                        <%}%>
                    </select>
                    <br>
                    <input name="btnSeleccionar" type="submit" value="Seleccionar Sala">


                    <p>Filas:</p>
                    <input name="snFilas" type="number" min="3" id="idFilas" value="<%=sdao.obtenerSalaID(Integer.valueOf(idSala)).getNroFilas()%>">

                    <p>Columnas:</p>
                    <input name="snColumnas" type="number" min="7" id="idColumnas" value="<%=sdao.obtenerSalaID(Integer.valueOf(idSala)).getNroColumnas()%>">

                    <h6>Nro. asientos</h6>
                    <output><%=sdao.obtenerSalaID(Integer.valueOf(idSala)).getNroAsientos()%></output>

                    <p>Disponibilidad:</p>

                    <p class=" caja">
                        <select name="disponibilidad" style="width: 130px;">
                            <!--opciones para el numero de salas-->
                            <%if (sdao.obtenerSalaID(Integer.valueOf(idSala)).getEstado().equals("activo")) {%>
                            <option> Disponible </option>
                            <option> No disponible </option>
                            <%} else {%>
                            <option> No disponible </option>
                            <option> Disponible </option>
                            <%}%>
                        </select>
                    </p>

                    <br><!-- comment -->

                    <input class="buttons" name="btnModificar" type="submit" value="Modificar">
                    <input class="buttons" name="btnDarBaja" type="submit" value="Dar de Baja">
                    <%}%>
                </form>
            </div>
        </div>
    </body>
</html>
