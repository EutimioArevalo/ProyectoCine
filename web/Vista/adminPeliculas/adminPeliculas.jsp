<%-- 
    Document   : adminPeliculas
    Created on : 30 jul. 2021, 12:13:40
    Author     : timoa
--%>

<%@page import="Controlador.Utiles"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Modelo.Pelicula"%>
<%@page import="Controlador.JPA.PeliculaJpaController"%>
<%@page import="Controlador.DAO.PeliculaDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Administrar Películas</title>
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
        <!--FONT AWESOME-->
        <!--FONT AWESOME-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="../inicio/inicio.css">
        <link rel="stylesheet" href="adminPeliculas.css"> 
    </head>

    <%
        HttpSession sesion = request.getSession();
        PeliculaDAO pdao = new PeliculaDAO();
        PeliculaJpaController pjc = new PeliculaJpaController();
        Utiles u = new Utiles();

        int cont = 0;
        List<Pelicula> aux = pjc.findPeliculaEntities();
        int id = 0;

        if (request.getParameter("btnAgregar") != null) {

            String titulo = request.getParameter("txtTitulo").toString();
            String sipnosis = request.getParameter("areaSipnosis").toString();
            String trailer = request.getParameter("txtTrailer").toString();
            String hidden = request.getParameter("hidden").toString();
            String fechaEstreno = request.getParameter("dateFechaEsteno").toString();
            String duracion = request.getParameter("txtHora").toString() + "h" + request.getParameter("textMinuto").toString();
            String genero = request.getParameter("cbxGeneros").toString();
            String idioma = request.getParameter("cbxIdiomas").toString();
            String director = request.getParameter("txtDirector").toString();
            String actor = request.getParameter("txtActor").toString();
            String clasificacion = request.getParameter("cbxClasificacion").toString();
            String resolucion = request.getParameter("cbxResolucion").toString();
            String Formato = request.getParameter("cbxFormato").toString();
            pdao.guardar(titulo, sipnosis, trailer, hidden, fechaEstreno, duracion, genero, idioma, director, actor, clasificacion, resolucion, Formato);
        }

        if (request.getParameter("btnEditar") != null) {
            int idPeli = Integer.valueOf(sesion.getAttribute("idPeli").toString());
            String titulo = request.getParameter("txtTitulo").toString();
            String sipnosis = request.getParameter("areaSipnosis").toString();
            String trailer = request.getParameter("txtTrailer").toString();
            String hidden = request.getParameter("hidden").toString();
            String fechaEstreno = request.getParameter("dateFechaEsteno").toString();
            String duracion = request.getParameter("txtHora").toString() + "h" + request.getParameter("textMinuto").toString();
            String genero = request.getParameter("cbxGeneros").toString();
            String idioma = request.getParameter("cbxIdiomas").toString();
            String director = request.getParameter("txtDirector").toString();
            String actor = request.getParameter("txtActor").toString();
            String clasificacion = request.getParameter("cbxClasificacion").toString();
            String resolucion = request.getParameter("cbxResolucion").toString();
            String Formato = request.getParameter("cbxFormato").toString();
            pdao.editar(idPeli, titulo, sipnosis, trailer, hidden, fechaEstreno, duracion, genero, idioma, director, actor, clasificacion, resolucion, Formato);

            sesion.setAttribute("idPeli", null);
        }

        for (int i = 0; i < aux.size(); i++) {
            if (request.getParameter("btnSeleccionar" + i) != null) {
                sesion.setAttribute("idPeli", pjc.findPeliculaEntities().get(i).getIdPelicula());
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

            <div class="formulario">
                <center>
                    <form action="adminPeliculas.jsp" method="POST" class="form-pelicula">

                        <%if (sesion.getAttribute("idPeli") == null) { %>

                        <p>Titulo de la Pelicula:  <input type="text" name="txtTitulo"></p>
                        <p>Sipnosis de la pelicula:  <textarea type="textarea" name="areaSipnosis" cols="50" rows="5"></textarea></p>
                        <P>URL del Trailer (Youtube):  <input type="text" name="txtTrailer"></p>
                        <P>Portada de la pelicula:  <img id="id-portada"></P>
                        <input type="hidden" name="hidden" id="hidden">
                        <input type="file" name="portada" id="portada-pelicula">
                        <P>Fecha de Estreno:  <input type="date" name="dateFechaEsteno" ></P>
                        <p>Duracion de la pelicula:  <input type="number" name="txtHora" min="0"> h: <input type="number" name="textMinuto" min="0"></p>
                        <P>Genero: <input list="generos" name="cbxGeneros"></P>

                        <datalist id="generos">
                            <option value="Thriller">
                            <option value="Comedia">
                            <option value="Romance">
                            <option value="Accion">
                            <option value="Aventuras">
                        </datalist>

                        <P>Idioma:  <input list="idiomas" name="cbxIdiomas"></P>

                        <datalist id="idiomas">
                            <option value="Español">
                            <option value="Ingles">
                            <option value="Coreano">
                            <option value="Japones">
                            <option value="Frances">
                        </datalist>

                        <P>Director:  <input type="text" name="txtDirector"></P>
                        <P>Actor Principal:  <input type="text" name="txtActor"></P>

                        <P>Clasificación:  <input list="clasificacion" name="cbxClasificacion"></P>
                        <datalist id="clasificacion">
                            <option value="AA">
                            <option value="A">
                            <option value="B">
                            <option value="B15">
                            <option value="C">
                            <option value="D">
                        </datalist>

                        <P>Resolución:  <input list="resolucion" name="cbxResolucion"></P>    
                        <datalist id="resolucion">
                            <option value="HD">
                            <option value="2K">
                            <option value="4K">
                        </datalist>

                        <P>Formato:  <input list="formato" name="cbxFormato"></P>                
                        <datalist id="formato">
                            <option value="VistaVision">
                            <option value="IMAX">
                            <option value="Digital 3D">
                        </datalist>
                        <input class= "buttons" type="submit" name="btnAgregar" value="Agregar">

                        <%} else {
                            String Sid = sesion.getAttribute("idPeli").toString();
                            id = Integer.valueOf(Sid);
                        %>
                        <p>Titulo de la Pelicula:  <input type="text" name="txtTitulo" value="<%=pdao.buscar(id).getTitulo()%>"></p>
                        <p>Sipnosis de la pelicula:  <textarea type="textarea" name="areaSipnosis" cols="50" rows="5"><%=pdao.buscar(id).getSipnosis()%></textarea>
                        <P>URL del Trailer (Youtube):  <input type="text" name="txtTrailer" value="<%=pdao.buscar(id).getTrailer()%>"></p>
                        <P>Portada de la pelicula:  <img src="<%=pdao.buscar(id).getPortada()%>" id="id-portada" ></P>
                        <input type="hidden" name="hidden" id="hidden" value="<%=pdao.buscar(id).getPortada()%>">
                        <input type="file" name="portada" id="portada-pelicula">
                        <P>Fecha de Estreno:  <input type="date" name="dateFechaEsteno" value="<%=pdao.buscar(id).getFechaEstreno()%>"></P>
                            <%
                                int hora = u.obtenerDuracion(pdao.buscar(id).getDuracion(), "HH");
                                int minuto = u.obtenerDuracion(pdao.buscar(id).getDuracion(), "MM");
                            %>
                        <p>Duracion de la pelicula:  <input type="number" name="txtHora" min="0" max="24" value="<%=hora%>"> h: <input type="number" name="textMinuto" min="0" max="60" value="<%=minuto%>"></p>
                        <P>Genero: <input list="generos" name="cbxGeneros" value="<%=pdao.buscar(id).getGenero()%>"></P>

                        <datalist id="generos">
                            <option value="Thriller">
                            <option value="Comedia">
                            <option value="Romance">
                            <option value="Accion">
                            <option value="Aventuras">
                        </datalist>

                        <P>Idioma:  <input list="idiomas" name="cbxIdiomas" value="<%=pdao.buscar(id).getIdioma()%>"></P>

                        <datalist id="idiomas">
                            <option value="Español">
                            <option value="Ingles">
                            <option value="Coreano">
                            <option value="Japones">
                            <option value="Frances">
                        </datalist>

                        <P>Director:  <input type="text" name="txtDirector" value="<%=pdao.buscar(id).getDirector()%>"></P>
                        <P>Actor Principal:  <input type="text" name="txtActor" value="<%=pdao.buscar(id).getElenco()%>"></P>

                        <P>Clasificación:  <input list="clasificacion" name="cbxClasificacion" value="<%=pdao.buscar(id).getClasificacion()%>"></P>
                        <datalist id="clasificacion">
                            <option value="AA">
                            <option value="A">
                            <option value="B">
                            <option value="B15">
                            <option value="C">
                            <option value="D">
                        </datalist>

                        <P>Resolución:  <input list="resolucion" name="cbxResolucion" value="<%=pdao.buscar(id).getResolucion()%>"></P>    
                        <datalist id="resolucion">
                            <option value="HD">
                            <option value="2K">
                            <option value="4K">
                        </datalist>

                        <P>Formato:  <input list="formato" name="cbxFormato" value="<%=pdao.buscar(id).getFormato()%>"></P>                
                        <datalist id="formato">
                            <option value="VistaVision">
                            <option value="IMAX">
                            <option value="Digital 3D">
                        </datalist>
                        
                        <input class= "buttons" type="submit" name="btnEditar" value="Editar">
                        <input class= "buttons" type="submit" name="btnDarDeBaja" value="Eliminar">
                        <%}%>
                        
                        
                    </form>
                </center>
            </div>

            <div id="tablaPeliculas">
                <h1>Peliculas en Base de datos</h1>
                <center>
                    <table border="1" cellspacing="5">
                        <thead>
                            <tr>
                                <th>Titulo</th>
                                <th>Genero</th>
                                <th>Idioma</th>
                                <th>Director</th>
                                <th>Actor Principal</th>
                                <th>Clasificacion</th>
                                <th>Selección</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Pelicula pelicula : pjc.findPeliculaEntities()) {
                            %>
                            <tr>
                                <td><%=pelicula.getTitulo()%></td>
                                <td><%=pelicula.getGenero()%></td>
                                <td><%=pelicula.getIdioma()%></td>
                                <td><%=pelicula.getDirector()%></td>
                                <td><%=pelicula.getElenco()%></td>
                                <td><%=pelicula.getClasificacion()%></td>
                                <td>
                                    <form action="adminPeliculas.jsp" method="POST">
                                        <input type="submit" name="btnSeleccionar<%=cont%>" value="Seleccionar" ><!-- Botón para seleccionar -->
                                    </form>
                                </td>
                            </tr>
                            <%cont++;
                                }%>
                        </tbody>
                    </table>
                </center>
            </div>
        </div>
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
        <script src="cloudinary.js"></script>
    </body>

</html>
