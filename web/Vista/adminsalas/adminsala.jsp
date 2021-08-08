<%-- 
    Document   : adminsala
    Created on : 30 jul. 2021, 12:14:28
    Author     : timoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>vista_sala</title>

        <!--FONT AWESOME-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="../inicio/inicio.css">
        <link rel="stylesheet" href="../adminsalas/adminsala.css">
    </head>
    <%
    %>

    <body>

        <div class="container">
            <nav class="nav-main">
                <img src="https://www.pngkit.com/png/full/786-7863517_para-cine-logo-de-cine-colombia-png.png" alt="Cine LOGO" class="logo">
                <ul class="nav-menu">
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
                </ul>
            </nav>
            <hr>

            <section class="centrado">
                <section class="vista_sala">
                    <h5>Administracion salas:</h5>
                    <div class="Lista_despegable"> <!--inicio box-->
                        <p>Salas:</p>
                        <form >
                            <select name="nrosalas" style="width: 130px;" id="">
                                <option selected> Sala nro 1</option>
                                <option> Sala nro 2</option>
                                <option> Sala nro 3</option>
                                <option> Sala nro 4</option>
                                <option> Sala nro 5</option>
                            </select>
                        </form>
                    </div> <!--se temrmina opciones combo box-->

                    <!-- creacion spinner numericos -->
                    <div class="box">
                        <p>Filas:</p>
                        <input class="controls" type="number">

                    </div>
                    <div class="box2">

                        <p>Columnas:</p>
                        <input class="controls" type="number">
                    </div>

                    <!-- finalizacion spinner numerico -->

                    <!-- presentacion asientos -->
                    <h6>Nro. asientos</h6>
                    <output>50</output>

                    <!-- finalizacion aisentos-->
                    <p>Disponibilidad:</p>
                    <p class=" caja">
                        <input class="boxes" type="radio" name="hm" value="h" required> SI
                        <input class="boxes" type="radio" name="hm" value="m" required> No
                    </p>


                </section>
                <div class="interaccion">

                    <input class="buttons" type="submit" value="Agregar sala">
                    <input class="buttons" type="submit" value="Aceptar">
                    <input class="buttons" type="submit" value="Cancelar">

                </div>
            </section>       
        </div><!-- fin container-->



    </body>
</html>
