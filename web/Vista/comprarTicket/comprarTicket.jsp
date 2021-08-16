<%-- 
    Document   : comprarTicket
    Created on : 30 jul. 2021, 12:15:33
    Author     : timoa
--%>

<%@page import="Controlador.JPA.RolJpaController"%>
<%@page import="Modelo.Persona"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Snack"%>
<%@page import="Controlador.JPA.SnackJpaController"%>
<%@page import="java.util.List"%>
<%@page import="Controlador.DAO.CarteleraDAO"%>
<%@page import="Controlador.DAO.PeliculaDAO"%>
<%@page import="Controlador.Utiles"%>
<%@page import="Controlador.CtrlPayment"%>
<%@page import="Controlador.DAO.PersonaDAO"%>
<%@page import="Controlador.DAO.CuentaDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="ISO-8859-1">
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
        <title>Comprar Tickets</title>
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
    </head>
    <%

        HttpSession sesion = request.getSession();

        Utiles u = new Utiles();
        CtrlPayment cp = new CtrlPayment();

        CuentaDAO c = new CuentaDAO();
        PersonaDAO p = new PersonaDAO();
        RolJpaController r = new RolJpaController();
        PeliculaDAO pelidao = new PeliculaDAO();
        CarteleraDAO cardao = new CarteleraDAO();
        PersonaDAO pdao = new PersonaDAO();

        SnackJpaController sjc = new SnackJpaController();

        String idPelicula = sesion.getAttribute("idPelicula").toString();
        String idCartelera = sesion.getAttribute("idCartelera").toString();
        List<Integer> listaAsientos = (List<Integer>) sesion.getAttribute("listaAsientos");
        List<Integer> listaSnacks = (List<Integer>) sesion.getAttribute("listaSnacks");
        List<String> tipoAsientos = (List<String>) sesion.getAttribute("tipoAsiento");
        

        List<Snack> lSnacks = sjc.findSnackEntities();
        List<String> asientos = (List<String>) sesion.getAttribute("nombreAsientos");

        float total = 0;

        for (int i = 0; i < listaAsientos.size(); i++) {
            if (tipoAsientos.get(i).equals("Adulto")) {
                total += cardao.buscar(Integer.valueOf(idCartelera)).getPrecio();
            } else {
                total += cardao.buscar(Integer.valueOf(idCartelera)).getPrecio() / 2;
            }
        }

        total = Math.round(total * 100f) / 100f;

        float tSnacks = 0;

        for (int i = 0; i < lSnacks.size(); i++) {
            if (listaSnacks.get(i) != null) {
                tSnacks += listaSnacks.get(i) * lSnacks.get(i).getPrecio();
            }
        }

        tSnacks = Math.round((tSnacks) * 100f) / 100f;

        float valorTotal = Math.round((total + tSnacks) * 100f) / 100f;
        sesion.setAttribute("total", valorTotal);
        String jsonPayment = cp.prepareCheckout(valorTotal);
        String idPayment = cp.obtenerId(jsonPayment);

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

        if (request.getParameter("btnAceptar") != null) {
            //u.enviarCorreo(request.getParameter("txtEmail").toString());
            String nombres = request.getParameter("txtNombres");
            String apellidos = request.getParameter("txtApellidos");
            String cedula = request.getParameter("txtCedula");
            String email = request.getParameter("txtEmail");
            String telefono = request.getParameter("txtTelefono");
            p.crear(nombres, apellidos, cedula, email, telefono, r.findRol(3));
            sesion.setAttribute("idPersonaAux", p.buscarCedula(cedula).getIdPersona());
        }


    %>
    <body>

        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="row">
                        <center>
                            <p><br></p>
                            <h3>Datos Facturación</h3>
                            <br><!-- comment -->

                            <p>Precio de la pelicula" "<%=pelidao.buscar(Integer.valueOf(idPelicula)).getTitulo()%>" = $<%=cardao.buscar(Integer.valueOf(idCartelera)).getPrecio()%> </p>
                            <p>Asientos seleccionados: <%=asientos.toString()%></p> 
                            <p>Precio total del Ticket: $<%=total%> </p>
                            <p>Precio total de los snacks: $<%=tSnacks%> </p>
                            <p>Precio total a pagar: $<%=valorTotal%> </p>

                        </center>

                    </div>
                    <div class="row">
                        <form action="comprarTicket.jsp" method="POST">
                            <%if (idRol == null || 3 != Integer.valueOf(idRol)) {
                            %>

                            <%if (sesion.getAttribute("idPersonaAux") != null) {
                            %> 
                            <p>Nombres: <input type="text" name="txtNombres1" value=<%=pdao.buscarPorId(Integer.valueOf(sesion.getAttribute("idPersonaAux").toString())).getNombres()%>></p>
                            <p>Apellidos: <input type="text" name="txtApellidos1" value=<%=pdao.buscarPorId(Integer.valueOf(sesion.getAttribute("idPersonaAux").toString())).getApellidos()%>></p>
                            <p>Cedula: <input type="number" name="txtCedula1" value=<%=pdao.buscarPorId(Integer.valueOf(sesion.getAttribute("idPersonaAux").toString())).getCedula()%>></p>
                            <p>Telefono: <input type="tel" name="txtTelefono1" value=<%=pdao.buscarPorId(Integer.valueOf(sesion.getAttribute("idPersonaAux").toString())).getTelefono()%>></p>
                            <p>Correo: <input type="email" name="txtEmail1" value=<%=pdao.buscarPorId(Integer.valueOf(sesion.getAttribute("idPersonaAux").toString())).getEmail()%> ></p>
                                <%} else {%>
                            <p>Nombres: <input type="text" name="txtNombres1"></p>
                            <p>Apellidos: <input type="text" name="txtApellidos1"></p>
                            <p>Cedula: <input type="number" name="txtCedula1"></p>
                            <p>Telefono: <input type="tel" name="txtTelefono1"></p>
                            <p>Correo: <input type="email" name="txtEmail1"></p>
                            <input type="submit" name="btnAceptar">
                            <%}%>


                            <%} else {%>
                            <p>Nombres: <input type="text" name="txtNombres" value=<%=c.buscarPersona(Integer.valueOf(idCuenta)).getNombres()%>></p>
                            <p>Apellidos: <input type="text" name="txtApellidos" value=<%=c.buscarPersona(Integer.valueOf(idCuenta)).getApellidos()%>></p>
                            <p>Cedula: <input type="number" name="txtCedula" value=<%=c.buscarPersona(Integer.valueOf(idCuenta)).getCedula()%>></p>
                            <p>Telefono: <input type="tel" name="txtTelefono" value=<%=c.buscarPersona(Integer.valueOf(idCuenta)).getTelefono()%>></p>
                            <p>Correo: <input type="email" name="txtEmail" value=<%=c.buscarPersona(Integer.valueOf(idCuenta)).getEmail()%>></p>
                                <%}%>
                        </form>
                        <div>
                            <h3>Metodo de Pago</h3>

                            <form action="http://localhost:8080/ProyectoCineWEB/Vista/comprarTicket/confirmarCompra.jsp" method="POST" class="paymentWidgets" data-brands="AMEX MASTER UNIONPAY VISA">

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://test.oppwa.com/v1/paymentWidgets.js?checkoutId=<%=idPayment%>"></script>
    </body>

</html>
