<%-- 
    Document   : ListaCarteleras
    Created on : 30 jul. 2021, 12:16:28
    Author     : timoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Carteleras</title>
    <!--FONT AWESOME-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <!--FONT OSWALD-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
    <!--VENTANA PERSONALIZADA CSS-->
    <link rel="stylesheet" href="estiloListaCarteleras.css">
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
        <div class="listaCarteleras">
            <div>
                <img src="/imagenes/Luca.jpg" alt="Cartelera 1">
                <h3>Cartelera 1</h3>
                <a href="/ModificarCartelera/ModificarCartelera.html"> Modificar Cartelera <i class="fas fa-highlighter"></i></a>
                <a href="#"> Dar de Baja <i class="fas fa-trash"></i></a>
            </div>
            <div>
                <img src="../imagenes/cartelera2.jpg" alt="Cartelera 2">
                <h3>Cartelera 2</h3>
                <a href="#"> Modificar Cartelera <i class="fas fa-highlighter"></i></a>
                <a href="#"> Dar de Baja <i class="fas fa-trash"></i></a>
            </div>
            <div>
                <img src="/imagenes/Thor.png" alt="Cartelera 3">
                <h3>Cartelera 3</h3>
                <a href="#"> Modificar Cartelera <i class="fas fa-highlighter"></i></a>
                <a href="#"> Dar de Baja <i class="fas fa-trash"></i></a>
            </div>
            <div>
                <img src="/imagenes/Cartelera.jpg" alt="Cartelera 4">
                <h3>Cartelera 4</h3>
                <a href="#"> Modificar Cartelera <i class="fas fa-highlighter"></i></a>
                <a href="#"> Dar de Baja <i class="fas fa-trash"></i></a>
            </div>
        </div>
        <div class="boton">
            <a href="#">Agregar Cartelera</a>
        </div>
    </div>
</body>

</html>
