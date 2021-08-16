<%-- 
    Document   : AdministrarEmpleados
    Created on : 30 jul. 2021, 12:13:22
    Author     : JonathanJavier
--%>

<%@page import="Modelo.Cuenta"%>
<%@page import="Controlador.JPA.CuentaJpaController"%>
<%@page import="Modelo.Persona"%>
<%@page import="Controlador.JPA.PersonaJpaController"%>
<%@page import="java.util.List"%>
<%@page import="Controlador.JPA.RolJpaController"%>
<%@page import="Controlador.DAO.CuentaDAO"%>
<%@page import="Controlador.DAO.PersonaDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Administrar Empleados</title>
        <!--FONT AWESOME-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--CUSTOME CSS-->
        <link rel="stylesheet" href="estiloAdminEmpleado.css">
    </head>
    <%
        HttpSession sesion = request.getSession();
        PersonaDAO p = new PersonaDAO();
        PersonaJpaController pjc = new PersonaJpaController();
        CuentaDAO c = new CuentaDAO();
        RolJpaController r = new RolJpaController();
        CuentaJpaController cjc = new CuentaJpaController();
        List<Cuenta> listaCuentas = cjc.findCuentaEntities();
        List<Persona> aux = pjc.findPersonaEntities();

        int cont = 0;
        int id = 0;

        if (request.getParameter("btnConfirmar") != null) {
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String cedula = request.getParameter("cedula");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            if (nombres.length() > 0 && apellidos.length() > 0 && cedula.length() > 0 && email.length() > 0 && telefono.length() > 0) {
                try {
                    p.crear(nombres, apellidos, cedula, email, telefono, r.findRol(2));
                    c.crear(cedula);
                    response.sendRedirect("../AdministrarEmpleados/AdministrarEmpleados.jsp");
                } catch (Exception e) {
                    out.print("<script>alert('Error al momento de ingresar datos');</script>");
                }
            } else {
                out.print("<script>alert('INGRESE TODOS LOS DATOS DE LOS CAMPOS SOLICITADOS');</script>");;
            }
        }
        if (request.getParameter("btnGuardarInfoEdit") != null) {
            try {
                int idPersona = Integer.valueOf(sesion.getAttribute("idPersona").toString());
                int idCuenta = Integer.valueOf(sesion.getAttribute("idCuenta").toString());
                String nombres = request.getParameter("nombres");
                String apellidos = request.getParameter("apellidos");
                String cedula = request.getParameter("cedula");
                String email = request.getParameter("email");
                String telefono = request.getParameter("telefono");
                p.editarPersona(idPersona, nombres, apellidos, cedula, email, telefono, r.findRol(2));
                c.editarCuenta(idCuenta, cedula);
                response.sendRedirect("../AdministrarEmpleados/AdministrarEmpleados.jsp");
                sesion.setAttribute("idPersona", null);
            } catch (Exception e) {
                out.print("<script>alert('ERROR AL EDITAR PERSONA');</script>");;
            }

        }
        
        if(request.getParameter("btnCancelar") != null){
            sesion.setAttribute("idPersona", null);
            response.sendRedirect("../AdministrarEmpleados/AdministrarEmpleados.jsp");
        }

        for (int i = 0; i < aux.size(); i++) {
            if (request.getParameter("btnEditarInf" + i) != null) {
                sesion.setAttribute("idPersona", pjc.findPersonaEntities().get(i).getIdPersona());
                sesion.setAttribute("idCuenta", cjc.findCuentaEntities().get(i).getIdCuenta());
            }
        }

        for (int i = 0; i < aux.size(); i++) {
            if (request.getParameter("btnDarDeBaja" + i) != null) {
                sesion.setAttribute("idCuenta", cjc.findCuentaEntities().get(i).getIdCuenta());
                sesion.setAttribute("cedulaPersona", pjc.findPersonaEntities().get(i).getCedula());
                String cedula = String.valueOf(sesion.getAttribute("cedulaPersona"));
                c.darDeBaja(Integer.valueOf(sesion.getAttribute("idCuenta").toString()), cedula);
                response.sendRedirect("../AdministrarEmpleados/AdministrarEmpleados.jsp");
            }
        }

    %>
    <body>
        <div class="container">
            <nav class="nav">
                <ul class="nav-menu">
                    <img src="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png" alt="Cine Logo" class="logo">
                    <li>
                        <a href="../AdministrarEmpleados/AdministrarEmpleados.jsp">Regresar <i class="fas fa-arrow-left"></i></a>
                    </li>
                </ul>
            </nav>

            <div class="divform">
                
                <form id="form" action="AdministrarEmpleados.jsp" method="POST">
                    <%if (sesion.getAttribute("idPersona") == null) {
                    %>
                    <header class="header">
                    Ingreso de Datos para el Nuevo Empleado
                    </header>
                    <p><p>Nombres: <input type="text" class="textField" name="nombres" size="30"></p></p>
                    <p><p>Apellidos: <input type="text" class="textField" name="apellidos" size="30"></p></p>
                    <p><p>Cédula: <input type="number" class="textField" name="cedula" size="30"></p></p>
                    <p><p>Teléfono: <input type="tel" class="textField" name="telefono" size="30"></p></p>
                    <p><p>Email: <input type="text" class="textField" name="email" size="30"></p></p>                  
                        <%} else {
                            String idString = sesion.getAttribute("idPersona").toString();
                            id = Integer.valueOf(idString);%>
                    <header class="header">
                    Editar la Información del Empleado
                    </header>        
                    <p><p>Nombres: <input type="text" class="textField" name="nombres" size="30" value="<%= pjc.findPersona(id).getNombres()%>"></p></p>
                    <p><p>Apellidos: <input type="text" class="textField" name="apellidos" size="30" value="<%= pjc.findPersona(id).getApellidos()%>"></p></p>
                    <p><p>Cédula: <input type="number" class="textField" name="cedula" size="30" value="<%= pjc.findPersona(id).getCedula()%>"></p></p>
                    <p><p>Teléfono: <input type="tel" class="textField" name="telefono" size="30" value="<%= pjc.findPersona(id).getTelefono()%>"></p></p>
                    <p><p>Email: <input type="text" class="textField" name="email" size="30" value="<%= pjc.findPersona(id).getEmail()%>"></p></p>
                        <%}%>
                    <input type="submit" class="botonConfir" name="btnConfirmar" value="Confirmar">
                    <input type="submit" class="botonConfir" name="btnGuardarInfoEdit" value="Guardar Edición">
                    <input type="submit" class="botonConfir" name="btnCancelar" value="Cancelar">
                </form>
            </div>

            <div class="tablaEmpleados">
                <header class="header">
                    Tabla de Empleados
                </header>
                <table class="datatable">
                    <thead>
                        <tr>
                            <th> <strong>Cédula</strong> </th>
                            <th>Nombre</th>
                        </tr>
                    <tbody>
                        <tr>
                            <%
                                for (Cuenta listaCuenta : listaCuentas) {

                                    if (listaCuenta.getPersona().getIdRol() == 2 && listaCuenta.getEstado().equalsIgnoreCase("activo")) {
                            %>
                        <tr>
                            <td><%= listaCuenta.getPersona().getCedula()%></td>
                            <td><%= listaCuenta.getPersona().getNombres() + " " + listaCuenta.getPersona().getApellidos()%></td>
                            <td><form action="AdministrarEmpleados.jsp" method="POST">
                                    <input type="submit" onclick="return confirm('¿Seguro que desea dar de baja a este empleado?')" class="botonDarDeBaja" name="btnDarDeBaja<%=cont%>" value="Dar de Baja" >
                                </form></td>
                            <td>
                                <form action="AdministrarEmpleados.jsp" method="POST">
                                    <input type="submit" class="botonEditarInfor" name="btnEditarInf<%=cont%>" value="Editar Información" >
                                </form>
                            </td>
                        </tr>
                        <%}
                                cont++;
                            }%>
                        <tr>
                        </tr>
                    </tbody>
                    </thead>
                </table>
            </div>
        </div>
    </body>
</html>
