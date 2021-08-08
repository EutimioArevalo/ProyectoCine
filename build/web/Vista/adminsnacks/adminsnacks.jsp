<%-- 
    Document   : adminsnacks
    Created on : 30 jul. 2021, 12:14:59
    Author     : timoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Vista_cine</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--CUSTOME CSS-->
        <link rel="stylesheet" href="adminsnacks.css">  


        <div class="container">
            <nav class="nav-main">
                <img src="../Imagenes/logo.png" alt="Cine LOGO" class="logo">
                <ul class="nav-menu">
                    <li>
                        <a href="#">Inicio</a>
                    </li>
                    <li>
                        <a href="ModificarCartelera.jsp">Modificar Carteleras</a>
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
                        <a href="#">Modificar Información</a>
                    </li>
                    <li>
                        <a href="../adminsnacks/adminsnacks.jsp">Administrar Snacks</a>
                    </li>
                </ul>
            </nav>
            <hr>

            <section class="form_info">
                <h5>Formulario combos: </h5>
                <p>Ingrese la información del combo : </p>
                <div class="datatable_container">
                    <table class="datatable">
                        <thead>
                            <tr>
                                <!--se definen el numero de columnas-->
                                <th></th>
                                <th> <strong>Numero</strong> </th>
                                <th>Descripción</th>
                                <th>Precio</th>
                            </tr>
                        <tbody>
                            <!--se definen el numero de filas-->
                            <tr>
                                <td> <input type="checkbox" name="" id="" ></td>

                                <td>1</td>
                                <td>Hot dogs, palomitas, chocolate Galak</td>
                                <td>1.25</td>
                            </tr>
                            <tr>
                                <td> <input type="checkbox" name="" id="" ></td>

                                <td>2</td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td> <input type="checkbox" name="" id="" ></td>

                                <td>3</td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td> <input type="checkbox" name="" id="" ></td>

                                <td>4</td>
                                <td></td>
                                <td></td>
                            </tr>





                        </tbody>
                        </thead>
                    </table>

                </div>
                <p2>Información:</p2>
                <input class= "controls" type="text" name="nombres" id="nombres" placeholder="Ingrese información">
                <p2>Precio:</p2>
                <input class= "controls" type="number" name="nombres" id="nombres" placeholder="Ingrese su precio">
                <input class= "buttons" type="submit" value="Agregar">
                <input class= "buttons" type="submit" value="Modificar">
                <input class= "buttons" type="submit" value="Dar de baja">
                <h5></h5>
                <input class= "button" type="submit" value="Aceptar">
            </section>
            <nav class="nav-main">


            </nav>
        </div>


    </body>
</html>
