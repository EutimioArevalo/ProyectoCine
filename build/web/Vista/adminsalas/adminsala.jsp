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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>vista_sala</title>

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

        int output = 0;
        if (request.getParameter("btnAgregar") != null) {
            int filas = Integer.valueOf(request.getParameter("snFilas"));
            int columnas = Integer.valueOf(request.getParameter("snColumnas"));
            int nroAsientos = filas * columnas;
            int nroSala = aux.size() + 1;
            sdao.agregar(nroAsientos, nroSala, filas, columnas);
        }


    %>

    <body>

        <div class="container">
            <nav class="nav-main">
                <img src="https://www.pngkit.com/png/full/786-7863517_para-cine-logo-de-cine-colombia-png.png" alt="Cine LOGO" class="logo">
                <ul class="nav-menu">
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
                </ul>
            </nav>
            <hr>

            <section class="centrado">
                <section class="vista_sala">
                    <h5>Administracion salas:</h5>
                    <div class="Lista_despegable"> <!--inicio box-->
                        <p>Salas:</p>
                        <form >
                            <select name="nroSalas" style="width: 130px;">
                                <%                                    for (Sala sala : aux) {

                                    
                                    

                                %>
                                <option>Sala: <%sala.getNroSala(); %></option>
                                <%}%>
                            </select>
                            <input name="btnSeleccionar" type="submit" value="Seleccionar Sala">


                            <p>Filas:</p>
                            <input name="snFilas" type="number" min="5" id="idFilas">

                            <p>Columnas:</p>
                            <input name="snColumnas" type="number" min="10" id="idColumnas">

                            <%if (sesion.getAttribute ( 
                                
                            "idSala") != null) {
                                    
                            %>
                            <h6>Nro. asientos</h6>
                            <output></output>
                                <%}%>
                            <p>Disponibilidad:</p>

                            <p class=" caja">
                                <select name="disponibilidad" style="width: 130px;" >
                                    <!--opciones para el numero de salas-->
                                    <option> No disponible </option>
                                    <option> Disponible </option>

                                    <br></p>

                                    <input name="btnAgregar" type="submit" value="Agregar sala">

                                    <input name="btnDarBaja" type="submit" value="Dar de Baja">
                                    </p>
                                    </form>
                                    </div> 
                                    </section>
                                    </section>
                                    </div>
                                    </body>
                                    </html>
