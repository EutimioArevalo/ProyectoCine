<%-- 
    Document   : comprarTicket
    Created on : 30 jul. 2021, 12:15:33
    Author     : timoa
--%>

<%@page import="Controlador.Utiles"%>
<%@page import="Controlador.CtrlPayment"%>
<%@page import="Controlador.DAO.PersonaDAO"%>
<%@page import="Controlador.DAO.CuentaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- incluir boostrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

        <!--FONT AWESOME-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="../../styles.css">
        <link rel="stylesheet" href="comprarTicket.css">
        <link rel="stylesheet" href="../ListaCarteleras/estiloListaCarteleras.css">
        <title>Comprar Tickets</title>
    </head>
    <%

        HttpSession sesion = request.getSession();
        Utiles u = new Utiles();

        String idCuenta = "";
        if (sesion.getAttribute("idCuenta") == null) {
            idCuenta = null;
        } else {
            idCuenta = sesion.getAttribute("idCuenta").toString();
        }
        
        String idPelicula = sesion.getAttribute("idPelicula").toString();

        String idRol = "";
        if (sesion.getAttribute("idRol") == null) {
            idRol = null;
        } else {
            idRol = sesion.getAttribute("idRol").toString();
        }

        CtrlPayment cp = new CtrlPayment();
        CuentaDAO c = new CuentaDAO();
        PersonaDAO p = new PersonaDAO();
        String jsonPayment = cp.prepareCheckout();
        String idPayment = cp.obtenerId(jsonPayment);


        if (request.getParameter("btnComprar") != null) {
            u.enviarCorreo(request.getParameter("txtEmail").toString());
            response.sendRedirect("confirmarCompra.jsp");
        }

    %>
    <body>
        <div class="container">

            <%
                
                if (idRol.equals("2") || idRol.equals("1")) {
            %>
            <nav class="nav-main">
                <img src="../Imagenes/logo.png" alt="Cine LOGO" class="logo">
                <p>Esto solo aparece si soy admin o empleado</p>
                <ul class="nav-menu">
                    <%
                        if (idRol.equals("2")) {
                    %>
                    <li>
                        <a href="../inicio/inicio.jsp">Inicio</a>
                    </li>

                    <li>
                        <a href="../registrarse/Pag_Registrarse.jsp">Modificar Información</a>
                    </li>

                    <li>
                        <a href="../comprarTicket/comprarTicket.jsp">Vender Ticket</a>
                    </li>

                    <%} else if (idRol.equals("1")) {%>
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
                    <%}%>
                </ul>
            </nav>

            <hr>

            <%}else{}%>

            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="row">
                            <h3>Datos Facturación</h3>
                        </div>
                        <div class="row">
                            <form action="comprarTicket.jsp" method="POST">
                                <%if (!idRol.equals("3")) {
                                %>
                                <p>Nombres: <input type="text" name="txtNombres1"></p>
                                <p>Apellidos: <input type="text" name="txtApellidos1"></p>
                                <p>Cedula: <input type="number" name="txtCedula1"></p>
                                <p>Telefono: <input type="tel" name="txtTelefono1"></p>
                                <p>Correo: <input type="email" name="txtEmail1"></p>
                                    <%} else {%>
                                <p>Nombres: <input type="text" name="txtNombres" value=<%=c.buscarPersona(Integer.valueOf(idCuenta)).getNombres()%>></p>
                                <p>Apellidos: <input type="text" name="txtApellidos" value=<%=c.buscarPersona(Integer.valueOf(idCuenta)).getApellidos()%>></p>
                                <p>Cedula: <input type="number" name="txtCedula" value=<%=c.buscarPersona(Integer.valueOf(idCuenta)).getCedula()%>></p>
                                <p>Telefono: <input type="tel" name="txtTelefono" value=<%=c.buscarPersona(Integer.valueOf(idCuenta)).getTelefono()%>></p>
                                <p>Correo: <input type="email" name="txtEmail" value=<%=c.buscarPersona(Integer.valueOf(idCuenta)).getEmail()%>></p>
                                    <%}%>
                                <input type ="submit" name="btnComprar" value="Comprar">
                            </form>

                        </div>
                    </div>
                    <div class="col">
                        <!-- metodos de pago -->
                        <div class="montoTotal mb-3 row ">
                            <label for="monto_total" class="col-sm-5 col-form-label">Monto Total: </label>
                            <div class="col-sm-5">
                                <input type="number" class="form-control" id="monto_total">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script src="https://test.oppwa.com/v1/paymentWidgets.js?checkoutId=<%=idPayment%>"></script>
            <div class="container">
                <center>
                    <h3>Metodo de Pago</h3>
                </center>
                <form action="http://localhost:8080/ProyectoCineWEB/Vista/comprarTicket/confirmarCompra.jsp" class="paymentWidgets" data-brands="AMEX MASTER UNIONPAY VISA"></form>
            </div>
            <div class="col">               
                <form action="comprarTicket.jsp" method="POST">

                </form>

            </div>
    </body>

</html>
