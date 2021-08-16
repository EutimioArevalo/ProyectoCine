<%-- 
    Document   : inicio
    Created on : 5 ago. 2021, 23:41:01
    Author     : timoa
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Controlador.DAO.CuentaDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tipo de Asientos</title>
        <link rel="shortcut icon" href="https://res.cloudinary.com/djsa7v6bs/image/upload/v1629058563/boleto_p5b5s5.png">
        <!--FONT AWESOME-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--VENTANA PERSONALIZADA CSS-->
        <link rel="stylesheet" href="../inicio/inicio.css">
    </head>

    <%

        HttpSession sesion = request.getSession();
        List<Integer> listaAsientos = (List<Integer>) sesion.getAttribute("listaAsientos");
        List<String> nombreasientos = (List<String>) sesion.getAttribute("nombreAsientos");
        List<String> tipoAsientos = new ArrayList<>();

        if (request.getParameter("btnAceptar") != null) {
            int cont = 0;
            for (int i = 0; i < listaAsientos.size(); i++) {

                tipoAsientos.add(request.getParameter("tipo" + i).toString());
                if (request.getParameter("tipo" + i).toString().equals("Adulto")) {
                    cont++;
                }
            }
            if (cont == 0) {
                out.print("<script>alert('Tiene que haber por lo menos un adulto');</script>");
                tipoAsientos.clear();
            } else {
                sesion.setAttribute("tipoAsiento", tipoAsientos);
                response.sendRedirect("../comprarSnacks/comprarSnacks.jsp");
            }
        }

    %>

    <body>
        <div class="container">
            <center>
                <br>
                <h1>Escoja que tipo de asientos son los que desea</h1>
            </center>

            <hr><!-- comment -->
            <br><!-- comment -->
            <center>
                <table border="2">
                    <form>


                        <thead>
                            <tr>
                                <%for (int i = 0; i < nombreasientos.size(); i++) {

                                %>
                                <th><%=nombreasientos.get(i).toString()%></th>
                                    <%}%>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <%for (int i = 0; i < nombreasientos.size(); i++) {

                                %>
                                <td>
                                    <select name="tipo<%=i%>" style="width: 130px;">
                                        <option> Adulto </option>
                                        <option> Menor </option>
                                    </select>
                                </td>
                                <%}%>
                            </tr>
                        </tbody>
                        <input type="submit" name="btnAceptar" value="Aceptar">
                    </form>
                </table>

            </center>




        </div>
    </body>
</html>
