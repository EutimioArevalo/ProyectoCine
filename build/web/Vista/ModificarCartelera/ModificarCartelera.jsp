<%-- 
    Document   : ModificarCartelera
    Created on : 30 jul. 2021, 12:16:41
    Author     : JonathanJavier
--%>

<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="Controlador.DAO.PeliculaDAO"%>
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
        <title>Administrar Cartelera</title>
        <!--FONT AWESOME-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--VENTANA PERSONALIZADA CSS-->
        <link rel="stylesheet" href="estiloModificarCar.css">
    </head>
    <%
        HttpSession sesion = request.getSession();
        CarteleraDAO cDAO = new CarteleraDAO();
        PeliculaDAO pDAO = new PeliculaDAO();
        CarteleraJpaController cjc = new CarteleraJpaController();
        PeliculaJpaController pjc = new PeliculaJpaController();
        List<Pelicula> listaPeliculas = pjc.findPeliculaEntities();
        SalaJpaController sjc = new SalaJpaController();
        List<Sala> listaSalas = sjc.findSalaEntities();

        int idPelicula = 0;
        int idSala = 0;

        if (request.getParameter("btnConfirmar") != null) {
            try {
                if (sesion.getAttribute("idCartelera").toString().equals("0")) {
                    String fecha = request.getParameter("fecha").toString();
                    String horario = request.getParameter("horario").toString();
                    Float precio = Float.parseFloat(request.getParameter("precio").toString());
                    String pelicula = request.getParameter("peliculas").toString();
                    idPelicula = pDAO.buscar(pelicula).getIdPelicula();
                    idSala = Integer.parseInt(request.getParameter("salas"));
                    boolean coincidenciaHorario = cDAO.coincidenciaHorarios(fecha, horario, sjc.findSala(idSala));
                    if (!coincidenciaHorario) {
                        cDAO.crearCartelera(horario, fecha, precio, pDAO.buscar(pelicula), sjc.findSala(idSala));
                        response.sendRedirect("../ListaCarteleras/ListaCarteleras.jsp");
                        cjc.findCartelera(idSala).getPrecio();
                    }else{
                        out.print("<script>alert('Ya existe una función en la hora establecida');</script>");
                    } 
                }else{
                    out.print("<script>alert('Use el botón Guardar Información');</script>");
                }
            } catch (Exception e) {
                out.print("<script>alert('Error al momento de ingresar datos');</script>");
            }

        }

        if (request.getParameter("btnGuardarInfoEditada") != null) {
            try {
                if (!sesion.getAttribute("idCartelera").toString().equals("0")) {
                    int idCartelera = Integer.valueOf(sesion.getAttribute("idCartelera").toString());
                    String fecha = request.getParameter("fecha").toString();
                    String horario = request.getParameter("horario").toString();
                    Float precio = Float.parseFloat(request.getParameter("precio").toString());
                    String pelicula = request.getParameter("peliculas").toString();
                    idPelicula = pDAO.buscar(pelicula).getIdPelicula();
                    idSala = Integer.parseInt(request.getParameter("salas"));
                    cDAO.editarCartelera(idCartelera, horario, fecha, precio, pDAO.buscar(pelicula), sjc.findSala(idSala));
                    response.sendRedirect("../ListaCarteleras/ListaCarteleras.jsp");
                }else{
                    out.print("<script>alert('No está editando una cartelera');</script>");
                }
                
            } catch (Exception e) {
                out.print("<script>alert('Error al momento de ingresar datos');</script>");;
            }

        }

        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mesInt = fecha.get(Calendar.MONTH) + 1;
        String mes = "";
        if (mesInt < 10) {
            mes = "0" + mesInt;
        } else {
            mes = String.valueOf(mesInt);
        }
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaActual = anio + "-" + mes + "-" + dia;
    %>
    <body>
        <div class="container">
            <nav class="nav-main">
                <img src="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png" alt="Cine LOGO" class="logo">
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
                    <%if (Integer.valueOf(sesion.getAttribute("idPelicula").toString()) != 0) {
                            idPelicula = Integer.valueOf(sesion.getAttribute("idPelicula").toString());
                    %>
                    <img src="<%=pjc.findPelicula(idPelicula).getPortada()%>" alt="Insertar Imagen">
                    <%} else {%>
                    <img src="https://image.flaticon.com/icons/png/512/1/1601.png" alt="Insertar Imagen">
                    <%}%>
                </div>
                <div class="lista">
                    <form id="form" action="ModificarCartelera.jsp" method="POST">
                        <p><p>A CONTINUACIÓN LLENE LOS CAMPOS NECESARIOS:</p>
                        <%if (Integer.valueOf(sesion.getAttribute("idCartelera").toString()) != 0) {
                                int idCartelera = Integer.valueOf(sesion.getAttribute("idCartelera").toString());
                        %>
                        <p><p>Fecha Limite: <input type="date" class="fechaLimite" id="start" name="fecha" value="<%=cjc.findCartelera(idCartelera).getDuracionCartelera()%>"
                                                   min="2021-08-13" required></p></p>
                            <%} else {%>
                        <p><p>Fecha Limite: <input type="date" class="fechaLimite" id="start" name="fecha" value="<%=fechaActual%>"
                                                   min="2021-08-13" required></p></p>
                            <%}%>
                        <p><p><select name="salas" required>
                                <option value="">Escoja una Sala:</option>
                                <%for (Sala listaSala : listaSalas) {
                                %>
                                <option value="<%=listaSala.getIdSala()%>">Sala <%=listaSala.getNroSala()%></option>
                                <%idPelicula++;
                                    }%>
                            </select></p></p>
                        <p><p><select name="peliculas" required>
                                <option value="">Escoja una Pelicula:</option>
                                <%for (Pelicula listaPelicula : listaPeliculas) {
                                        listaPelicula.getTitulo();
                                %>
                                <option value="<%=listaPelicula.getTitulo()%>"><%=listaPelicula.getTitulo()%></option>
                                <%}%>
                            </select></p></p>
                            <%if (Integer.valueOf(sesion.getAttribute("idCartelera").toString()) != 0) {
                                    int idCartelera = Integer.valueOf(sesion.getAttribute("idCartelera").toString());
                                    cjc.findCartelera(idCartelera).getHorario();
                            %>
                        <p><p>Hora de Comienzo: <input type="time" class="horasCartelera" id="idHorario" name="horario" value="<%=cjc.findCartelera(idCartelera).getHorario()%>" required></p></p>
                            <%} else {%>
                        <p><p>Hora de Comienzo: <input type="time" class="horasCartelera" id="idHorario" name="horario" required></p></p>
                            <%}%>
                            <%if (Integer.valueOf(sesion.getAttribute("idCartelera").toString()) != 0) {
                                    int idCartelera = Integer.valueOf(sesion.getAttribute("idCartelera").toString());
                            %>
                        <p><p>Precio de Entrada: <input type="text" class="precioText" name = "precio" value="<%=cjc.findCartelera(idCartelera).getPrecio()%>"id="idPrecio" placeholder="0.00" required> USD</p></p>
                            <%} else {%>
                        <p><p>Precio de Entrada: <input type="text" class="precioText" name = "precio" id="idPrecio" placeholder="0.00" required> USD</p></p>
                            <%}%>
                        <input type="submit" name="btnConfirmar" class="botonConfirmar" value="Confirmar">
                        <input type="submit" name="btnGuardarInfoEditada" class="botonConfirmar" value="Guardar Información Editada">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
