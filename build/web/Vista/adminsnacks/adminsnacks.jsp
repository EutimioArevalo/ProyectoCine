<%-- 
    Document   : adminsnacks
    Created on : 30 jul. 2021, 12:14:59
    Author     : timoa
--%>

<%@page import="Controlador.DAO.SnackDAO"%>
<%@page import="Modelo.Snack"%>
<%@page import="java.util.List"%>
<%@page import="Controlador.JPA.SnackJpaController"%>
<%@page contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Vista_cine</title>
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
        <link rel="stylesheet" href="styles.css">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--CUSTOME CSS-->
        <link rel="stylesheet" href="adminsnacks.css">  
        <link rel="stylesheet" href="../inicio/inicio.css">  
    </head>

    <%

        HttpSession sesion = request.getSession();
        SnackJpaController sjc = new SnackJpaController();
        SnackDAO sdao = new SnackDAO();
        List<Snack> aux = sjc.findSnackEntities();

        int cont = 0;

        String idSnack = "";
        if (sesion.getAttribute("idSnack") != null) {
            idSnack = sesion.getAttribute("idSnack").toString();
        } else {
            idSnack = null;
        }

        if (request.getParameter("btnAgregar") != null) {
            String nombre = request.getParameter("txtNombre").toString();
            String descripcion = request.getParameter("txtDescripcion").toString();
            Float precio = Float.valueOf(request.getParameter("txtPrecio").toString());
            String img = request.getParameter("txtHidden").toString();
            try {
                sdao.agregar(nombre, descripcion, precio, img);
            } catch (Exception e) {
                out.print("<script>alert('Error al momento de agregar Snack, revise los datos');</script>");
            }
            response.sendRedirect("../adminsnacks/adminsnacks.jsp");
        }

        if (request.getParameter("btnEditar") != null) {
            String nombre = request.getParameter("txtNombre").toString();
            String descripcion = request.getParameter("txtDescripcion").toString();
            Float precio = Float.valueOf(request.getParameter("txtPrecio").toString());
            String img = request.getParameter("txtHidden").toString();
            sdao.editar(Integer.valueOf(idSnack), nombre, descripcion, precio, img);
            sesion.setAttribute("idSnack", null);
            response.sendRedirect("../adminsnacks/adminsnacks.jsp");
            out.print("<script>alert('Editado con exito');</script>");
        }

        for (int i = 0; i < aux.size(); i++) {
            if (request.getParameter("btnSeleccion" + i) != null) {
                sesion.setAttribute("idSnack", aux.get(i).getIdSnack());
                response.sendRedirect("../adminsnacks/adminsnacks.jsp");
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
                        <a href="../comprarTicket/seleccionarAsiento.jsp">Vender Ticket</a>
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

            <section class="form_info">
                <br>
                <h5>Formulario combos: </h5>
                <p>Ingrese la información del combo : </p>
                <div class="datatable_container">
                    <table class="datatable">
                        <thead>
                            <tr>
                                <!--se definen el numero de columnas-->

                                <th> <strong>Nombre</strong> </th>
                                <th>Descripción</th>
                                <th>Precio</th>
                                <th>Selección:</th>
                            </tr>
                        <tbody>
                            <!--se definen el numero de filas-->
                            <%                                    for (Snack snack : aux) {

                            %>
                            <tr>

                                <td><%=snack.getNombre()%></td>
                                <td><%=snack.getDescripcion()%></td>
                                <td><%=snack.getPrecio()%></td>
                                <td> 
                                    <form action="adminsnacks.jsp" method="POST">
                                        <input type="submit" name="btnSeleccion<%=cont%>" value="Seleccionar">
                                    </form>
                                </td>


                            </tr>

                            <%cont++;
                                }%>

                        </tbody>
                        </thead>
                    </table>

                </div>


                <form action="adminsnacks.jsp" method="POST">
                    <%

                        if (idSnack == null) {

                    %>
                    <p2>Nombre:</p2>
                    <input class= "controls" type="text" name="txtNombre" id="nombres" placeholder="Ingrese un nombre para el combo">
                    <p2>Descripción:</p2>
                    <input class= "controls" type="text" name="txtDescripcion" id="nombres" placeholder="Ingrese una descripcion del combo">
                    <p2>Precio:</p2>
                    <input class= "controls" type="number" name="txtPrecio" id="nombres" placeholder="Ingrese su precio" step="any">
                    <p2>Imagen del combo:</p2>
                    <img id="img-combo" height="275" width="425"/>
                    <input type="hidden" name="txtHidden" id="hidden">
                    <input type="file" id="subirImg" value="Subir imagen">
                    <br><!-- comment -->
                    <hr><!-- comment -->
                    <br>
                    <input class= "buttons" name="btnAgregar" type="submit" value="Agregar">
                    <%} else {%>
                    <p2>Nombre:</p2>
                    <input class= "controls" type="text" name="txtNombre" id="nombres" placeholder="Ingrese un nombre para el combo" value="<%=sdao.buscarsnack(Integer.valueOf(idSnack)).getNombre()%>">
                    <p2>Descripción:</p2>
                    <input class= "controls" type="text" name="txtDescripcion" id="nombres" placeholder="Ingrese una descripcion del combo" value="<%=sdao.buscarsnack(Integer.valueOf(idSnack)).getDescripcion()%>">
                    <p2>Precio:</p2>
                    <input class= "controls" type="number" name="txtPrecio" id="nombres" placeholder="Ingrese su precio" step="any" value="<%=sdao.buscarsnack(Integer.valueOf(idSnack)).getPrecio()%>">
                    <p2>Imagen del combo:</p2>
                    <img src="<%=sdao.buscarsnack(Integer.valueOf(idSnack)).getImg()%>"  id="img-combo" height="275" width="425"/>
                    <input type="hidden" name="txtHidden" id="hidden">
                    <input type="file" id="subirImg" value="Subir imagen">
                    <br><!-- comment -->
                    <hr><!-- comment -->
                    <br>

                    <input class= "buttons" name="btnEditar" type="submit" value="Modificar">
                    <input class= "buttons" name="btnDarBaja" type="submit" value="Dar de baja">
                    <hr>
                    <%}%>
                </form>


            </section>
            <hr>
            <br>
        </div>

        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
        <script src="snack.js"></script>
    </body>
</html>
