<%-- 
    Document   : ModificarCartelera
    Created on : 30 jul. 2021, 12:16:41
    Author     : JonathanJavier
--%>

<%@page import="Modelo.Sala"%>
<%@page import="Controlador.JPA.SalaJpaController"%>
<%@page import="Modelo.Pelicula"%>
<%@page import="Controlador.JPA.PeliculaJpaController"%>
<%@page import="Modelo.Cartelera"%>
<%@page import="Modelo.Cartelera"%>
<%@page import="java.util.List"%>
<%@page import="Controlador.JPA.CarteleraJpaController"%>
<%@page import="Controlador.DAO.CarteleraDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Modificar Cartelera</title>
        <!--FONT AWESOME-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--VENTANA PERSONALIZADA CSS-->
        <link rel="stylesheet" href="estiloModificarCar.css">
    </head>
    <%
        HttpSession sesion = request.getSession();
        CarteleraDAO cDAO = new CarteleraDAO();
        CarteleraJpaController cjc = new CarteleraJpaController();
        PeliculaJpaController pjc = new PeliculaJpaController();
        List<Pelicula> listaPeliculas = pjc.findPeliculaEntities();
        SalaJpaController sjc = new SalaJpaController();
        List<Sala> listaSalas = sjc.findSalaEntities();
    %>
    <body>
        <div class="container">
            <nav class="nav-main">
                <img src="../Imagenes/logo.png" alt="Cine LOGO" class="logo">
                <ul class="nav-menu">
                    <li>
                        <a href="#">Inicio</a>
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
                        <a href="../comprarTicket/comprarTicket.jsp">Vender Ticket</a>
                    </li>
                    <li>
                        <a href="#">Modificar Información</a>
                    </li>
                    <li>
                        <a href="../adminsnacks/adminsnacks.jsp">Administrar Snacks</a>
                    </li>
                </ul>
            </nav>
            <hr>
            <div class="informacionCartelera">
                <div class="portada">
                    <img src="../Imagenes/Luca.jpg" alt="Insertar Imagen">
                </div>
                <div class="lista">
                    <form id="form" action="AdministrarEmpleados.jsp" method="POST">
                        <p><p>A CONTINUACIÓN LLENE LOS CAMPOS NECESARIOS:</p>
                        <p><p>Fecha Limite: <input type="text" name="fechaLimite" size="30" placeholder="dd-mm-aa"></p></p>
                        <p><p><select>
                                <option value="">Escoja una Sala:</option>
                                <option value="opcion-1">Sala 1</option>
                                <option value="opcion-2">Sala 2</option>
                                <option value="opcion-x">Sala 3</option>
                        </select></p></p>
                        <p><p><select>
                                <option value="">Escoja una Sala:</option>
                                <option value="opcion-1">Sala 1</option>
                                <option value="opcion-2">Sala 2</option>
                                <option value="opcion-x">Sala 3</option>
                            </select></p></p>
                        <p><p>Hora de Comienzo: <input type="time" id="idHorario" name="horario" min="07:30" max="01:30" required></p></p>
                        <p><p>Precio de Entrada: <input type="text" name = "precio" id="idPrecio"></p></p>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
