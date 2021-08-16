<%-- 
    Document   : Pag_Registrarse
    Created on : 30 jul. 2021, 12:16:56
    Author     : timoa
--%>

<%@page import="Controlador.JPA.PersonaJpaController"%>
<%@page import="Controlador.JPA.CuentaJpaController"%>
<%@page import="Modelo.Cuenta"%>
<%@page import="Controlador.DAO.CuentaDAO"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="Controlador.JPA.FacturaJpaController"%>
<%@page import="Controlador.JPA.RolJpaController"%>
<%@page import="Controlador.DAO.PersonaDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--CUSTOME CSS-->
        <link rel="stylesheet" href="registrar.css">
    </head>
    <%
        HttpSession sesion = request.getSession();

        PersonaDAO p = new PersonaDAO();
        CuentaDAO c = new CuentaDAO();
        CuentaJpaController cjc = new CuentaJpaController();
        PersonaJpaController pjc = new PersonaJpaController();
        RolJpaController r = new RolJpaController();

        String idCuenta = "";
        String idRol = "";

        if (sesion.getAttribute("idCuenta") == null) {
            idCuenta = null;
        } else {
            idCuenta = sesion.getAttribute("idCuenta").toString();
        }

        if (sesion.getAttribute("idRol") == null) {
            idRol = null;
        } else {
            idRol = sesion.getAttribute("idRol").toString();
        }

        if (request.getParameter("btnAgregar") != null) {
            String nombres = request.getParameter("txtNombres");
            String apellidos = request.getParameter("txtApellidos");
            String cedula = request.getParameter("txtCedula");
            String email = request.getParameter("txtEmail");
            String telefono = request.getParameter("txtTelefono");
            try {
                p.crear(nombres, apellidos, cedula, email, telefono, r.findRol(3));
                c.crear(cedula);
               // sesion.setAttribute("idCuenta", c.buscarPorCedula(cedula));
               // sesion.setAttribute("idRol", r.findRol(3).getIdRol());
                out.print("<script>alert('Su usuario es el email que ingreso y su clave es su cedula. Puede modificarlas cuando desee');</script>");
                response.sendRedirect("../../index.jsp");
            } catch (Exception e) {
                out.print("<script>alert('Error al momento de ingresar datos');</script>");
            }
        }

        if (request.getParameter("btnEditar") != null) {
            String usuario = request.getParameter("txtUsuario");
            String clave = request.getParameter("txtClave");
            try {
                //c.editar(usuario, clave, c.buscar(usuario));
                c.editarA(Integer.valueOf(idCuenta), usuario, clave, "activo", c.buscarCuentaId(Integer.valueOf(idCuenta)).getPersona());
                response.sendRedirect("../../index.jsp");
            } catch (Exception e) {
                out.print("<script>alert('Error al momento de editar los datos');</script>");;
            }
        }

    %>
    <body>

        <div class="container">
            <%if (idRol != null) {%>
            <nav class="nav"><img src="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png" alt="Cine Logo" class="logo">
                <ul class="nav-menu">
                    <%if (Integer.valueOf(idRol) == 3) {
                    %>
                    <li>
                        <a href="../../index.jsp">Regresar</a>
                    </li>
                    <%
                    } else if (Integer.valueOf(idRol)
                            == 2) {

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

                    <%}%>

                </ul>
            </nav>

            <hr>
            <%}%>
            <div class="divFor">


                <form id="form" action="Pag_Registrarse.jsp" method="POST">

                    <%

                        if (sesion.getAttribute("idCuenta") == null) {
                    %>
                    <p>Nombres: <input type="text" name="txtNombres"></p>
                    <p>Apellidos: <input type="text" name="txtApellidos" ></p>
                    <p>Cedula: <input type="number" name="txtCedula" "></p>
                    <p>Telefono: <input type="tel" name="txtTelefono" ></p>
                    <p>Email: <input type="email" name="txtEmail"></p>
                    <input type="submit" name="btnAgregar" value="Aceptar">
                    <%
                    } else {
                    %>

                    <p>Usuario: <input type="text" name="txtUsuario" value="<%=c.buscarCuentaId(Integer.valueOf(idCuenta)).getUsuario()%>"></p>
                    <p>Clave:  <input type="text" name="txtClave" value="<%=c.buscarCuentaId(Integer.valueOf(idCuenta)).getClave()%>"></p>
                    <input type="submit" name="btnEditar" value="Aceptar">

                    <%}%>


                </form>

            </div>


        </div>


    </body>
</html>