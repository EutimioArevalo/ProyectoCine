<%-- 
    Document   : ListaCarteleras
    Created on : 30 jul. 2021, 12:16:28
    Author     : JonathanJavier
--%>

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
        <title>Lista de Carteleras</title>
        <!--FONT AWESOME-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--VENTANA PERSONALIZADA CSS-->
        <link rel="stylesheet" href="estiloListaCarteleras.css">
    </head>
    <%
        HttpSession sesion = request.getSession();
        CarteleraDAO cDAO = new CarteleraDAO();
        CarteleraJpaController cjc = new CarteleraJpaController();
        List<Cartelera> listaCarteleras = cjc.findCarteleraEntities();
        Cartelera cart = new Cartelera();
        
        if (request.getParameter("btnAgregarCartelera") != null) {
            sesion.setAttribute("idPelicula", 0);
            sesion.setAttribute("idCartelera", 0);
            response.sendRedirect("../ModificarCartelera/ModificarCartelera.jsp");
        }
        
        for (int i = 0; i < listaCarteleras.size(); i++) {
            if (request.getParameter("btnDarDeBaja" + i) != null) {
                sesion.setAttribute("idPelicula", cjc.findCarteleraEntities().get(i).getIdPelicula().getIdPelicula());
                sesion.setAttribute("idCartelera", cjc.findCarteleraEntities().get(i).getIdCartelera());
                cjc.destroy(Integer.parseInt(sesion.getAttribute("idCartelera").toString()));
                response.sendRedirect("../ListaCarteleras/ListaCarteleras.jsp");
            }
        }
        
        for (int i = 0; i < listaCarteleras.size(); i++) {
            if (request.getParameter("btnModificarCartelera" + i) != null) {
                sesion.setAttribute("idPelicula", cjc.findCarteleraEntities().get(i).getIdPelicula().getIdPelicula());
                sesion.setAttribute("idCartelera", cjc.findCarteleraEntities().get(i).getIdCartelera());
                response.sendRedirect("../ModificarCartelera/ModificarCartelera.jsp");
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
            <div class="listaCarteleras">
                <%  int contador = 0;
                    for (Cartelera listaCartelera : listaCarteleras) {
                        
                        listaCartelera.getIdPelicula().getPortada();
                %>
                <div>
                    <img src="<%=listaCartelera.getIdPelicula().getPortada()%>" alt="Cartelera">
                    <h3>Cartelera <%= contador%></h3>
                    <form action="ListaCarteleras.jsp" method="POST">
                        <input type="submit" class="botonModificar" name="btnModificarCartelera<%=contador%>" value="Modificar Cartelera" >
                    </form>
                    <form action="ListaCarteleras.jsp" method="POST">
                        <input type="submit" onclick="return confirm('¿Seguro que desea dar de baja esta cartelera?')" class="botonEliminarCart" name="btnDarDeBaja<%=contador%>" value="Dar de Baja" >
                    </form>
                </div>
                <%contador++;
                    }
                %>

            </div>
            <div class="boton">
                <form action="ListaCarteleras.jsp" method="POST">
                    <input type="submit" class="botonAgregarCart"name="btnAgregarCartelera" value="Agregar Cartelera" >
                </form>
            </div>
        </div>
    </body>

</html>
