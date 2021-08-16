<%-- 
    Document   : seleccionarAsiento
    Created on : 30 jul. 2021, 12:15:52
    Author     : timoa
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Controlador.DAO.CarteleraDAO"%>
<%@page import="Controlador.DAO.SalaDAO"%>
<%@page import="Controlador.Utiles"%>
<%@page import="java.util.List"%>
<%@page import="Controlador.JPA.SalaJpaController"%>
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
        <title>Seleccionar Asiento</title>
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
    </head>
    <%

        HttpSession sesion = request.getSession();
        Utiles u = new Utiles();
        SalaJpaController sjc = new SalaJpaController();
        SalaDAO sdao = new SalaDAO();
        CarteleraDAO cdao = new CarteleraDAO();

        String idSala = sesion.getAttribute("idSala").toString();
        String idCartelera = sesion.getAttribute("idCartelera").toString();
        List<Integer> listaAsientos = new ArrayList<>();
        List<String> nombreasientos = new ArrayList<>();

        List<Boolean> asientos = u.toArry(cdao.buscar(Integer.valueOf(idCartelera)).getAsientos());
        int filas = sjc.findSala(Integer.valueOf(idSala)).getNroFilas();
        int columnas = sjc.findSala(Integer.valueOf(idSala)).getNroColumnas();

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

        int c = 0;

        if (request.getParameter("btnSeleccionar") != null) {
            System.out.println("ESTOY DENTRO DEL BOTON");

            for (int i = 0; i < sjc.findSala(Integer.valueOf(idSala)).getNroAsientos(); i++) {
                //System.out.println(asientos.get(i));
                //System.out.println(request.getParameter("cb" + c));
                if (request.getParameter("cb" + i) != null && asientos.get(i) == false) {
                    listaAsientos.add(i);
                    nombreasientos.add(request.getParameter("cb" + i).toString());
                    //asientos.set(i, true);
                }
            }
            sesion.setAttribute("listaAsientos", listaAsientos);
            sesion.setAttribute("nombreAsientos", nombreasientos);
            response.sendRedirect("tipoAsientos.jsp");
            //String jsonAsientos = u.asignarAsientos(asientos);
            //cdao.asientos(Integer.valueOf(idCartelera), jsonAsientos);
        }

        if (request.getParameter("btnCancelar") != null) {
            sesion.setAttribute("idPelicula", null);
            response.sendRedirect("../../index.jsp");
        }

    %>

    <body>
        <!-- comment -->
        <div class="container">


            <div class="titulo_centrar">
                <h4>Seleccione sus asientos:</h4>
            </div>

            <!-- tabla de asientos  -->

            <center>

                <table class="table">

                    <thead>
                        <tr>
                            <%for (int i = 'A';
                                        i < 'A' + columnas;
                                        i++) {

                            %>
                            <th>
                                <h5 style="color:white;"><%=(char) i%></h5>
                            </th>
                            <%}%>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="seleccionarAsiento.jsp" method="POST">
                        <%int cont = 0;
                            for (int j = 1;
                                    j <= filas;
                                    j++) {

                        %>
                        <tr>
                            <%for (int i = 'A'; i < 'A' + columnas; i++) {

                            %>
                            <td>
                                <h5 style="color:white;"><%=(char) i + "" + j%></h5>
                                <%if (asientos.get(cont) == true) {%>

                                <input type="radio" name="cb<%=cont%>" checked value="<%=(char) i + "" + j%>">
                                <%} else {%>
                                <input type="radio" name="cb<%=cont%>" value="<%=(char) i + "" + j%>">
                                <%}%>

                            </td>
                            <%cont++;
                                }%>
                        </tr>
                        <%}%>
                        </tbody>
                        <br>
                        <hr>
                        <br>
                        <input type="submit" name="btnSeleccionar" value="Seleccionar Asientos">
                        <input type="submit" name="btnCancelar" value="Cancelar">
                    </form>.
                </table>
            </center>

            <br><!-- comment -->




        </div>
    </body>

</html>
