<%-- 
    Document   : registro
    Created on : 15 ago. 2021, 19:42:23
    Author     : timoa
--%>

<%@page import="Controlador.DAO.FacturaDAO"%>
<%@page import="Controlador.DAO.TicketDAO"%>
<%@page import="java.util.List"%>
<%@page import="Controlador.JPA.FacturaJpaController"%>
<%@page import="Modelo.Factura"%>
<%@page import="Controlador.DAO.CuentaDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro</title>
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--CUSTOME CSS-->
        <link rel="stylesheet" href="inicio.css">
    </head>
    <%
        HttpSession sesion = request.getSession();
        CuentaDAO cdao = new CuentaDAO();
        FacturaDAO fdao = new FacturaDAO();
        FacturaJpaController fjc = new FacturaJpaController();
        List<Factura> list = fjc.findFacturaEntities();

        TicketDAO tdao = new TicketDAO();

        String idCuenta = "";
        if (sesion.getAttribute("idCuenta") != null) {
            idCuenta = sesion.getAttribute("idCuenta").toString();
        } else {
            idCuenta = null;
        }


    %>
    <body>
        <div class="container">
            <nav class="nav-main">
                <img src="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png" alt="Cine LOGO" class="logo">
                <ul class="nav-menu">

                    <li>
                        <a href="../../index.jsp">Regresar</a>
                    </li>

                </ul>
            </nav>

            <hr>

            <div>

                <center>
                    <%                        if (cdao.buscarCuentaId(Integer.valueOf(idCuenta)).getPersona().getIdRol().getIdRol() == 3) {
                            List<Factura> listC = fdao.obtenerLista(Integer.valueOf(idCuenta));
                    %>


                    <h1>Bienvenido</h1>
                    <%
                        if (listC.size() == 0) {
                    %>
                    <h1>No ha realizado ninguna compra</h1>
                    <%} else {%>
                    <table border="1" id="tableR">
                        <thead>
                            <tr>
                                <th>Cliente</th>
                                <th>Cedula</th>
                                <th>Pelicula</th>
                                <th>Cantidad Asientos</th>
                                <th>Cantidad Snack</th>
                                <th>Precio Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Factura f : listC) {

                            %>
                            <tr>
                                <td><%=f.getPersona().getNombres()%> </td>
                                <td><%=f.getPersona().getCedula()%></td>
                                <td><%=tdao.obtenerIdCartelera(f.getIdDetalle().getIdDetalle()).getIdPelicula().getTitulo()%></td>
                                <td><%=f.getIdDetalle().getCantidadTicket()%></td>
                                <td><%=f.getIdDetalle().getCantidadSnack()%></td>
                                <td><%=f.getTotal()%></td>

                            </tr>
                            <%}%>

                        </tbody>
                    </table>
                    <%}%>

                    <%} else {%>



                    <h1>Bienvenido</h1>
                    <%
                        if (list.size() == 0) {

                    %>
                    <h1>No se han registrado Ventas</h1>
                    <%} else {%>
                    <table border="1" id="tableR">
                        <thead>
                            <tr>
                                <th>Cliente</th>
                                <th>Cedula</th>
                                <th>Pelicula</th>
                                <th>Cantidad Asientos</th>
                                <th>Cantidad Snack</th>
                                <th>Precio Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Factura f : list) {

                            %>
                            <tr>
                                <td><%=f.getPersona().getNombres()%> </td>
                                <td><%=f.getPersona().getCedula()%></td>
                                <td><%=tdao.obtenerIdCartelera(f.getIdDetalle().getIdDetalle()).getIdPelicula().getTitulo()%></td>
                                <td><%=f.getIdDetalle().getCantidadTicket()%></td>
                                <td><%=f.getIdDetalle().getCantidadSnack()%></td>
                                <td><%=f.getTotal()%></td>

                            </tr>
                            <%}%>

                        </tbody>
                    </table>

                    <%}%>
                    <%}%>
                </center>

            </div>

        </div>
    </body>
</html>
