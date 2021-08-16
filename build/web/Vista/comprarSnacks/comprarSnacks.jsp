<%-- 
    Document   : comprarSnacks
    Created on : 30 jul. 2021, 12:15:17
    Author     : timoa
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Snack"%>
<%@page import="Controlador.JPA.SnackJpaController"%>
<%@page import="Controlador.DAO.SnackDAO"%>
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
        <link rel="stylesheet" href="../../styles.css">
        <link rel="stylesheet" href="comprarSnacks.css">
        <title>Comprar Snacks</title>
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
    </head>
    <%

        HttpSession sesion = request.getSession();

        SnackDAO snackDAO = new SnackDAO();
        SnackJpaController njc = new SnackJpaController();

        String idCuenta = "";
        if (sesion.getAttribute("idCuenta") == null) {
            idCuenta = null;
        } else {
            idCuenta = sesion.getAttribute("idCuenta").toString();
        }
        String idPelicula = sesion.getAttribute("idPelicula").toString();
        String idCartelera = sesion.getAttribute("idCartelera").toString();
        List<Integer> listaAsientos = (List<Integer>) sesion.getAttribute("listaAsientos");
        List<Integer> intSnack = new ArrayList<>();
        //System.out.println(listaAsientos);

        String idRol = "";
        if (sesion.getAttribute("idRol") == null) {
            idRol = null;
        } else {
            idRol = sesion.getAttribute("idRol").toString();
        }

        if (request.getParameter("btnSeleccionar") != null) {
            for (int i = 0; i < njc.findSnackEntities().size(); i++) {
                if (request.getParameter("num" + i) != null) {
                    intSnack.add(Integer.valueOf(request.getParameter("num" + i).toString()));
                } else {
                    intSnack.add(0);
                }
                
            }
            sesion.setAttribute("listaSnacks", intSnack);
            response.sendRedirect("../comprarTicket/comprarTicket.jsp");
        }

    %>
    <body>
        <div class="container">
            <div class="titulo">
                <h3>Snacks</h3>
            </div>

            <hr><!-- comment -->

            <div class="list-group lista">
                <form action="comprarSnacks.jsp" method="POST">

                    <%
                        int cont = 0;
                        for (Snack s : njc.findSnackEntities()) {
                    %>
                    <div class="list-group-item item_container">
                        <div class="row row_item">

                            <div class="col-md-1">

                                <input class="form-check-input" type="number" min="0" value="0" id="combo1" name="num<%=cont%>">

                            </div>
                            <div class="col-md-4">
                                <h5><%=s.getNombre() + ": " + s.getDescripcion()%></h5>
                            </div>
                            <div class="col-md-4 imagenCombo">
                                <img src=<%=s.getImg()%> alt="combo" height="100" width="150">
                            </div>
                            <div class="col precio">
                                <%=s.getPrecio()%>
                            </div>

                        </div>
                    </div>
                    <%cont++;
                        }%>
                    <input type="submit" name="btnSeleccionar" value="Aceptar">                            
                </form>  

            </div>
            <!-- boton  -->
            <div class="row container_button">
                </br>
                <div class="botona"> 
                    <a href="../comprarTicket/comprarTicket.jsp" class="btn btn-primary boton" tabindex="-1" role="button"
                       >CONTINUAR</a>
                </div>

            </div>
        </div>
    </body>

</html>
