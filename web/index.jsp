<%-- 
    Document   : index
    Created on : 30 jul. 2021, 12:03:45
    Author     : timoa
--%>

<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="Modelo.Cartelera"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Controlador.DAO.PeliculaDAO"%>
<%@page import="Controlador.JPA.SnackJpaController"%>
<%@page import="Modelo.Snack"%>
<%@page import="Controlador.JPA.PeliculaJpaController"%>
<%@page import="Modelo.Pelicula"%>
<%@page import="Controlador.DAO.CarteleraDAO"%>
<%@page import="Controlador.JPA.CarteleraJpaController"%>
<%@page import="java.util.List"%>
<%@page import="Controlador.JPA.CuentaJpaController"%>
<%@page import="Modelo.Cuenta"%>
<%@page import="Controlador.DAO.CuentaDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cine</title> 
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--CUSTOME CSS-->
        <link rel="stylesheet" href="styles.css">
    </head>
    <%

        HttpSession sesion = request.getSession(false);
        if (sesion.getAttribute("idRol") != null) {

            if (Integer.valueOf(sesion.getAttribute("idRol").toString()) != 3) {
                response.sendRedirect("Vista/inicio/inicio.jsp");
            }

        }

        CuentaDAO c = new CuentaDAO();
        CarteleraDAO car = new CarteleraDAO();
        PeliculaDAO pDAO = new PeliculaDAO();
        CuentaJpaController cjc = new CuentaJpaController();
        CarteleraJpaController cajc = new CarteleraJpaController();
        PeliculaJpaController peliculaJPA = new PeliculaJpaController();
        SnackJpaController snackJPA = new SnackJpaController();

        List<Integer> Peliaux = new ArrayList<>();

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

        for (Cartelera ca : cajc.findCarteleraEntities()) {
            Peliaux.add(ca.getIdPelicula().getIdPelicula());
        }

        Set<Integer> hash = new HashSet<>(Peliaux);
        List<Integer> Paux = new ArrayList<>(hash);

        int cont = 0;

        if (request.getParameter("btnLogin") != null) {
            String usuario = request.getParameter("txtUsuario").toString();
            String clave = request.getParameter("txtClave").toString();
            System.out.println(usuario + clave);
            if (c.verificarLogin(usuario, clave)) {

                sesion.setAttribute("idCuenta", c.buscarIDCuenta(usuario));
                sesion.setAttribute("idRol", c.buscaridROl(usuario));
                switch (c.buscaridROl(usuario)) {
                    case 1:
                        response.sendRedirect("Vista/inicio/inicio.jsp");
                        break;
                    case 2:
                        response.sendRedirect("Vista/inicio/inicio.jsp");
                        break;
                    case 3:
                        response.sendRedirect("index.jsp");
                        break;
                }
            } else {
                out.print("<script>alert('Usuario o clave no valida');</script>");
            }
        }

        if (request.getParameter("btnRegistrar") != null) {
            response.sendRedirect("Vista/registrarse/Pag_Registrarse.jsp");
        }

        if (request.getParameter("btnModificar") != null) {
            response.sendRedirect("Vista/registrarse/Pag_Registrarse.jsp");
        }

        if (request.getParameter("btnCerrarSesion") != null) {
            sesion.setAttribute("idCuenta", null);
            sesion.setAttribute("idRol", null);
            response.sendRedirect("index.jsp");
        }
        
        if (request.getParameter("btnRegistro") != null) {
            response.sendRedirect("Vista/inicio/registro.jsp");
        }

        for (Integer p : Paux) {
            if (request.getParameter("btnMasInformacion" + p) != null) {
                sesion.setAttribute("idPelicula", p);
                sesion.setAttribute("listaCartelera", car.obtenerPorPelicula(p));
                if (sesion.getAttribute("idCuenta") == null) {
                    sesion.setAttribute("idCuenta", null);
                }
                response.sendRedirect("Vista/informacionPelicula/Pag_InfoPeli.jsp");
            }
        }
        
        

    %>

    <body>

        <div class="container">
            <nav class="nav">  
                <ul class="nav-menu">
                    <img src="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png" alt="Cine Logo" class="logo">



                    <form id="form-menu" action="index.jsp" method="POST">
                        <%if (sesion.getAttribute("idCuenta") == null) {

                        %>
                        <li>
                            <input type="text" name="txtUsuario" placeholder="Usuario" />
                        </li>

                        <li>
                            <input type="password" name="txtClave" placeholder="Clave" />
                        </li>

                        <li>
                            <input type ="submit" name="btnLogin" value="Iniciar Sesion">
                        </li>

                        <li>
                            <input type ="submit" name="btnRegistrar" value="Registrarse">
                        </li>
                        
                        <%} else {%>

                        <li>
                            <input type ="submit" name="btnModificar" value="Modificar Información">
                        </li>

                        <li>
                            <input type ="submit" name="btnCerrarSesion" value="Cerrar Sesión">
                        </li>
                        <li>
                            <input type ="submit" name="btnRegistro" value="Ver Historia de Compra">
                        </li>
                        <%}%>
                    </form>
                </ul>
            </nav>

            <hr>

            <header class="bievenida">
                <%if (sesion.getAttribute("idCuenta") == null) {
                %>
                <h1>BIENVENIDOS A CINE UNL!!!</h1>
                <%} else {%>
                <h1>BIENVENIDO <%=c.buscarCuentaId(Integer.valueOf(sesion.getAttribute("idCuenta").toString())).getUsuario()%> A CINE UNL!!!</h1>
                <%}%>
                <p>Disfruta de las mejores peliculas, en las mejores salas, con los mejores precios.</p>


            </header>


            <div id="peliculas">

                <%
                    for (Integer p : Paux) {
                %>

                <div id="tarjeta">
                    <div id="portada">
                        <img src=<%=pDAO.buscar(p).getPortada()%> alt="peli1">
                    </div>
                    <div id="infoPeli">
                        <h2><%=pDAO.buscar(p).getTitulo()%></h2>
                        <p><%=pDAO.buscar(p).getSipnosis()%></p>
                        <form action="index.jsp" method="POST">
                            <input type ="submit" name="btnMasInformacion<%=p%>" value="Más Información">
                        </form>
                    </div>
                </div>

                <% cont++;
                    }%>

            </div>

            <header class="bievenida">
                <h1>Disfruta de nuestros snacks!!!</h1>
                <p>Acompaña tu pelicula con rica comida</p>
            </header>

            <div class="snacks">

                <%
                    for (Snack s : snackJPA.findSnackEntities()) {


                %>
                <div>
                    <img src=<%=s.getImg()%> alt="combo1">
                    <h2><%=s.getNombre()%></h2>
                    <p>Precio: $<%=s.getPrecio()%></p>
                    <p><%=s.getDescripcion()%></p>
                </div>
                <%}%>

            </div>

        </div>

    </body>

</html>
