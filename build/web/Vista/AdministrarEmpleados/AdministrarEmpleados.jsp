<%-- 
    Document   : AdministrarEmpleados
    Created on : 30 jul. 2021, 12:13:22
    Author     : timoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Administrar Empleados</title>
        <!--FONT AWESOME-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!--FONT OSWALD-->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
        <!--CUSTOME CSS-->
        <link rel="stylesheet" href="estiloAdminEmpleado.css">
        <link rel="stylesheet" href="../inicio/inicio.css">
        
    </head>
    <body>

        <div class="container">
            <nav class="nav-main">
                <img src="../Imagenes/logo.png" alt="Cine LOGO" class="logo">
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


            <div class="atrib">
                <ul class="lista">
                    <li>
                        <label for="lnombre">Nombres:</label> 
                        <input type="text" name="nombre" id="inombre">
                    </li>
                    <li>
                        <label for="lapellido">Apellidos:</label>
                        <input type="text" name="apellido" id="iapellido">
                    </li>
                    <li>
                        <label for="lcedula">Cedula:</label>
                        <input type="text" name="cedula" id="icedula">
                    </li>
                    <li>
                        <label for="ltelefono">Telefono:</label>
                        <input type="text" name="telefono" id="itelefono">
                    </li>
                    <li>
                        <label for="lcorreo">Correo:</label>
                        <input type="text" name="correo" id="icorreo">
                    </li>
                </ul>

                <div class="perfil">
                    <img src="../imagenes/perfil.png" alt="" id="perfil">
                    <button>Subir foto</button>
                </div>
                <hr>
                <div class="boton">
                    <button class="b1">Confirmar</button>
                    <button class="b2">
                        <a href="index.html">Cancelar</a>
                    </button>
                </div>
            </div>
            <div class="tablaEmpleados">
                <table class="datatable">
                    <thead>
                        <tr>
                            <th> <strong>Cédula</strong> </th>
                            <th>Nombre</th>
                        </tr>
                    <tbody>
                        <!--se definen el numero de filas-->
                        <tr>
                            <td> <label name="nombres" id="idEmpleados" >1150580726</td>
                            <td>Jonathan Andrade</td>
                            <td><a href="#" class="botonDarDeBaja">Dar de Baja</a></td>
                            <td><a href="#" class="botonEditar">Editar Información</a></td>
                        </tr>
                        <tr>
                            <td> <label name="nombres" id="idEmpleados" >1150580726</td>
                            <td>Eutimio Arévalo</td>
                            <td><a href="#" class="botonDarDeBaja">Dar de Baja</a></td>
                            <td><a href="#" class="botonEditar">Editar Información</a></td>
                        </tr>
                        <tr>
                            <td> <label name="nombres" id="idEmpleados" >1150580726</td>
                            <td>Eduardo Paccha</td>
                            <td><a href="#" class="botonDarDeBaja">Dar de Baja</a></td>
                            <td><a href="#" class="botonEditar">Editar Información</a></td>
                        </tr>
                        <tr>
                            <td> <label name="nombres" id="idEmpleados" >1150580726</td>
                            <td>David Rodríguez</td>
                            <td><a href="#" class="botonDarDeBaja">Dar de Baja</a></td>
                            <td><a href="#" class="botonEditar">Editar Información</a></td>
                        </tr>
                    </tbody>
                    </thead>
                </table>
            </div>
        </div>


    </body>
</html>
