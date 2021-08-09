<%-- 
    Document   : adminsala
    Created on : 30 jul. 2021, 12:14:28
    Author     : timoa
--%>

<%@page import="Modelo.Cartelera"%>
<%@page import="Controlador.Jpa.CarteleraJpaController"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="Controlador.Jpa.SalaJpaController"%>
<%@page import="Controlador.Dao.SalaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>vista_sala</title>
        <link rel="stylesheet" href="styles.css">
    </head>

    <%
        SalaDAO sala= new SalaDAO(); 
        SalaJpaController salactrl= new SalaJpaController(); 
        Cartelera cartelera = new Cartelera(); 
        int nrofilas = Integer.parseInt(request.getParameter("spinnerfila"));
        int nrocolumnas = Integer.parseInt(request.getParameter("spinnercolum"));
        int numerodefilas = nrofilas * nrocolumnas;
    %>


    <body>

        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--CUSTOME CSS-->
        <link rel="stylesheet" href="adminsala.css"> 

        <div class="container">
            <<nav class="nav-main">
                <img src="../Imagenes/logo.png" alt="Cine LOGO" class="logo">
                <ul class="nav-menu">
                    <li>
                        <a href="#">Inicio</a>
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
                            <select name="nrosalas" style="width: 130px;" >
                                <!--opciones para el numero de salas-->
                                <%
                                    for (int i = 0; i < 5; i++) {
                                %>
                                <option> Sala nro <%=i + 1%> </option>
                                <%
                                    }
                                %>

                            </select>
                        </form>
                    </div> <!--se temrmina opciones combo box-->

                    <!-- creacion spinner numericos -->
                    <div class="box">
                        <!<!-- filas y salas -->
                        <p>Filas:</p>
                        <form>
                            <input class="controls" name= "spinnerfila" type="number">
                        </form>

                    </div>
                    <div class="box2">

                        <p>Columnas:</p>
                        <input class="controls" name="spinnercolum" type="number">
                    </div>

                    <!-- finalizacion spinner numerico -->

                    <!-- presentacion asientos -->
                    <h6>Nro. asientos</h6>
                    <%
                        request.getParameter("spinnerfila");
                        request.getParameter("spinnercolum");


                    %>
                    <output>

                    </output>

                    <!-- finalizacion aisentos-->
                    <form action="adminsala.jsp" method="POST">

                        <p>Disponibilidad:</p>
                        <p class=" caja">
                            <!-- inputs de la dispobilidad -->

                            <select name="disponibilidad" style="width: 130px;" >
                                <!--opciones para el numero de salas-->
                                <%                                    for (int i = 0; i < 2; i++) {
                                        if (i % 2 != 0) {
                                %>
                                <option> No disponible </option>
                                <%
                                } else {
                                %>
                                <option> Disponible </option>
                                <%
                                    }
                                %>
                                <%
                                    }
                                %>

                            </select>

                        </p>

                    </form>

                </section>

                <div class="interaccion">
                    <form>
                        <!--inputs de los botonesde la vista -->
                        <input class="buttons" name="btns" type="submit" value="Agregar sala">
                        <input class="buttons" name="btns" type="submit" value="Aceptar">
                        <input class="buttons" name="btns" type="submit" value="Dar de baja">
                        <input class="buttons" name="btns" type="submit" value="Modificar">
                    </form>


                </div>
            </section>       
        </div><!-- fin container-->

        <%            // para recibir los parametros
            //tipo de dato seguido del nombre del parametro request.getParameter(" "); 
            //nro de filas
            int estado = Integer.parseInt(request.getParameter("disponibilidad")); 
            int nrosala = Integer.parseInt(request.getParameter("nrosalas"));
             
            String accion = request.getParameter("btns");
            if (accion.equals("Agregar sala")) {
            // revisar id cartelera 
                sala.ingresar((short)estado, nrofilas, nrocolumnas, nrosala, cartelera); 
            } else if (accion.equals("Aceptar")) {
                // regresar de ventana 
            } else if (accion.equals("Dar de baja")) {
                sala.darbaja(nrosala); 
            } else if (accion.equals("Modificar")) {
                sala.modificar((short)estado, nrofilas, nrocolumnas, nrosala, cartelera); 
            }

        %>


    </body>
</html>
