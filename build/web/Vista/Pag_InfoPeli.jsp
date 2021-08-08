<%-- 
    Document   : Pag_InfoPeli
    Created on : 4 ago. 2021, 19:50:24
    Author     : josue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina de Información de Peliculas</title>
    <!--FONT OSWALD-->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400&display=swap" rel="stylesheet">
    <!--CUSTOME CSS-->
    <link rel="stylesheet" href="../styles.css">
</head>

<body>
    <div class="container">
        <nav class="nav">
            <ul class="nav-menu">
                <img src="../imagenes/logo.png" alt="Cine Logo" class="logo">
                <li>
                    <a href="index.html">Regresar</a>
                </li>
            </ul>
        </nav>

        <hr>

        <header class="titulo">
            Titulo de la pelicula
        </header>

        <div id="tarjeta">
            <div id="portada">
                <img src="../imagenes/geminis.jpg" alt="peli1">
            </div>
            <div id="infoPeli">
                <p>Henry Brogan, un asesino a sueldo ya demasiado mayor para seguir con su duro trabajo, decide
                    retirarse. Pero esto no le va a resultar tan fácil, pues tendrá que enfrentarse a un clon suyo,
                    mucho más joven. </p>
            </div>
        </div>

        <div id="general">
            <div id="video">
                <iframe width="676" height="380" src="https://www.youtube.com/embed/1sjDCN-7dSo?"
                    title="YouTube video player" frameborder="0"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowfullscreen></iframe>
            </div>

            <div id="tabla">
                <table border="">
                    <tr>
                        <td colspan="2">02-08 Julio</td>
                    </tr>
                    <tr>
                        <td>Hora</td>
                        <td>Formato</td>
                    </tr>
                    <tr>
                        <td>16:30</td>
                        <td>4K2D</td>
                    </tr>
                    <tr>
                        <td>19:30</td>
                        <td>4K2D</td>
                    </tr>
                    <tr>
                        <td>14:50</td>
                        <td>2D</td>
                    </tr>
                </table>
            </div>

            <div id="boton">
                <button>
                    <a href="../comprarTicket/comprarTicket.jsp">Reservar</a>
                </button>
            </div>

        </div>

</body>

</html>