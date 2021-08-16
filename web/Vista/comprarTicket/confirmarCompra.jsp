<%-- 
    Document   : confirmarCompra
    Created on : 30 jul. 2021, 12:15:43
    Author     : timoa
--%>

<%@page import="Controlador.Utiles"%>
<%@page import="Controlador.DAO.FacturaDAO"%>
<%@page import="Modelo.Snack"%>
<%@page import="Controlador.JPA.SnackJpaController"%>
<%@page import="Controlador.DAO.CarritoDAO"%>
<%@page import="Controlador.JPA.DetallefacturaJpaController"%>
<%@page import="Controlador.DAO.CarteleraDAO"%>
<%@page import="Controlador.DAO.TicketDAO"%>
<%@page import="Modelo.Ticket"%>
<%@page import="Controlador.DAO.DetalleFacturaDAO"%>
<%@page import="Controlador.DAO.CuentaDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!--FONT AWESOME-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="../../styles.css">
        <link rel="stylesheet" href="comprarTicket.css">
        <title>Confirmar Compra</title>
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
    </head>
    <%
        HttpSession sesion = request.getSession();
        Utiles u = new Utiles();
        CuentaDAO cuentaDAO = new CuentaDAO();
        TicketDAO ticketDAO = new TicketDAO();
        CarteleraDAO carteleraDAO = new CarteleraDAO();
        DetalleFacturaDAO detalleDAO = new DetalleFacturaDAO();
        CarritoDAO carritoDAO = new CarritoDAO();
        FacturaDAO facturaDAO = new FacturaDAO();
        

        SnackJpaController s = new SnackJpaController();

        String idCuenta = "";
        if (sesion.getAttribute("idCuenta") != null) {
            idCuenta = sesion.getAttribute("idCuenta").toString();
        }
        String idCartelera = sesion.getAttribute("idCartelera").toString();
        String idSala = sesion.getAttribute("idSala").toString();
        float total = (Float.valueOf(sesion.getAttribute("total").toString()) * 100f) / 100f;
        List<Integer> listaAsientos = (List<Integer>) sesion.getAttribute("listaAsientos");
        List<String> nombreasientos = (List<String>) sesion.getAttribute("nombreAsientos");
        List<String> tipoAsientos = (List<String>) sesion.getAttribute("tipoAsiento");
        List<Integer> listaSnack = (List<Integer>) sesion.getAttribute("listaSnacks");
        List<Boolean> selecAsientos = u.toArry(carteleraDAO.buscar(Integer.valueOf(idCartelera)).getAsientos());
        
        List<Snack> lSnacks = s.findSnackEntities();

        int snacks = 0;
        for (int i = 0; i < listaSnack.size(); i++) {
            snacks += listaAsientos.get(i);
        }

        detalleDAO.agregar(listaAsientos.size(), snacks, total);

        for (int i = 0; i < listaAsientos.size(); i++) {
            float precio = 0;
            if (tipoAsientos.get(i).equals("Adulto")) {
                precio = carteleraDAO.buscar(Integer.valueOf(idCartelera)).getPrecio();
            } else {
                precio = carteleraDAO.buscar(Integer.valueOf(idCartelera)).getPrecio() * 0.50f;
            }
            ticketDAO.agregar(carteleraDAO.buscar(Integer.valueOf(idCartelera)).getIdPelicula().getTitulo(), precio, nombreasientos.get(i), tipoAsientos.get(i), carteleraDAO.buscar(Integer.valueOf(idCartelera)), detalleDAO.buscarUltimo());
        }

        for (int i = 0; i < listaSnack.size(); i++) {
            if (listaSnack.get(i) != 0) {
                carritoDAO.agregar(lSnacks.get(i).getNombre(), listaSnack.get(i), lSnacks.get(i).getPrecio(), (lSnacks.get(i).getPrecio() * listaSnack.get(i)), lSnacks.get(i), detalleDAO.buscarUltimo());
            }
        }

        facturaDAO.crear(total, cuentaDAO.buscarCuentaId(Integer.valueOf(idCuenta)).getPersona().getNombres(), cuentaDAO.buscarCuentaId(Integer.valueOf(idCuenta)).getPersona(), detalleDAO.buscarUltimo());

        for (int i = 0; i < listaAsientos.size(); i++) {
            selecAsientos.set(listaAsientos.get(i), true);
        }
        String jsonAsientos = u.asignarAsientos(selecAsientos);
        carteleraDAO.asientos(Integer.valueOf(idCartelera), jsonAsientos);
        
        String email = cuentaDAO.buscarCuentaId(Integer.valueOf(idCuenta)).getPersona().getEmail();
        u.enviarCorreo(email, u.generarTxt(carteleraDAO.buscar(Integer.valueOf(idCartelera)), cuentaDAO.buscarCuentaId(Integer.valueOf(idCuenta)).getPersona(),total, nombreasientos.toString()));


    %>
    <body>
        <div class="container">
            <div class="centrara">
                <img src="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png" alt="combo" height="100" width="200">
                <hr><!-- comment -->
                <center>
                    <h1>¡¡Felicidades!!</h1>
                    <h3>Su compra fue efectuada con éxito</h3>
                    <h3>Su ticket estará disponible en su correo electrónico</h3>
                    <h3>Disfrute su película <%=cuentaDAO.buscarCuentaId(Integer.valueOf(idCuenta)).getPersona().getNombres()%>!!</h3>
                    <div class="col">               
                </center>

                <hr>

                <a href="../../index.jsp" class="btn btn-primary" tabindex="-1" role="button"
                   >ACEPTAR</a>
            </div>
        </div>
    </div>
</body>
</html>
