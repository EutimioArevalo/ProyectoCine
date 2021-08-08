<%-- 
    Document   : ModificarCartelera
    Created on : 30 jul. 2021, 12:16:41
    Author     : timoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Cartelera</title>
    <!--FONT AWESOME-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <!--FONT OSWALD-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
    <!--VENTANA PERSONALIZADA CSS-->
    <link rel="stylesheet" href="estiloModificarCar.css">
</head>
<body>
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
        <div class="informacionCartelera">
            <div class="portada">
                <img src="../Imagenes/Luca.jpg" alt="Insertar Imagen">
                <a href="#" class="cambiarImagen"> Cambiar Imagen <i class="fas fa-highlighter"></i></a>
            </div>
            <div class="lista">
                <ul>
                    <li>
                        <label for="labelPrecio">A CONTINUACIÓN LLENE LOS CAMPOS NECESARIOS:</label>
                    </li>
                    <li>
                        <a href="#" class="botonCartelera"> Seleccionar Pelicula</a>
                    </li>
                    <li>
                        <select>
                            <option value="">Escoja una Sala:</option>
                            <option value="opcion-1">Sala 1</option>
                            <option value="opcion-2">Sala 2</option>
                            <option value="opcion-x">Sala 3</option>
                        </select>
                    </li>
                    <li>
                        <select>
                            <option value="">Escoja un Horario:</option>
                            <option value="opcion-1">Opcion 1</option>
                            <option value="opcion-2">Opcion 2</option>
                            <option value="opcion-x">Opcion 3</option>
                        </select>
                    </li>
                    <li>
                        <select>
                            <option value="">Promociones:</option>
                            <option value="opcion-1">Opcion 1</option>
                            <option value="opcion-2">Opcion 2</option>
                            <option value="opcion-x">Opcion 3</option>
                        </select>
                    </li>
                    <li>
                        <label for="labelPrecio">Precio: </label>
                        <input type="text" name = "precio" id="idPrecio">
                        <label for="labelPrecio">  $</label>
                    </li>
                    <li>
                        <a href="#" class="botonCartelera"> Dar de Baja <i class="fas fa-trash"></i></a>
                    </li>
                    <li>
                        <a href="#" class="botonGuardar"> Guardar Informacion</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
