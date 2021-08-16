<%-- 
    Document   : adminsnacks
    Created on : 30 jul. 2021, 12:14:59
    Author     : timoa
--%>

<%@page import="Modelo.Detallefactura"%>
<%@page import="Modelo.Factura"%>
<%@page import="Controlador.Jpa.SnackJpaController"%>
<%@page import="Controlador.Dao.SnackDAO"%>
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
    <%
    SnackDAO snack = new SnackDAO(); 
    SnackJpaController snackctrl= new SnackJpaController(); 
    Detallefactura detallefactura = new Detallefactura(); 
    %>
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
                                <th> Nombre</th>
                                <th>Descripción</th>
                                <th>Precio</th>
                            </tr>
                        <tbody>
                            <!--se definen el numero de filas-->
                        <form>
   
                            <%
                            // el numero de los combos se extrae de la base de datos 
                            for (int i = 0; i < snackctrl.findSnackEntities().size(); i++) {
                            %>
                             <tr>
                                <td> <input type="checkbox" name="listacombo" id="" ></td>

                                <td><input type="number" name="nrosnack" value="<%=i+1%>" ></td>
                                 <!-- Nombre -->
                                <td>Combo <%=i+1%></td>
                                <!-- descripcion -->
                                <td></td>
                                <!-- precio -->
                                <td></td>
                            </tr>
                            <%
                                }
                            %>
                            </form>

                        </tbody>
                        </thead>
                    </table>

                </div>

                <form>
                    <p2>Información:</p2>
                    <input class= "controls" type="text" name="info" id="nombresinfo" placeholder="Ingrese información">
                    <p2>Precio:</p2>
                    <input class= "controls"  type="number" name="precio" id="nombres" placeholder="Ingrese su precio">
                    <p2>Nombre:</p2>
                    <input class= "controls" type="text" name="nombre"  placeholder="Ingrese nombre">
                    <!-- btn -->
                    <input class= "buttons" name="btns" type="submit" value="Agregar">
                    <input class= "buttons" name="btns" type="submit" value="Modificar">
                    <input class= "buttons" name="btns" type="submit" value="Dar de baja">

                </form>

                <<form>
                    <input class= "button" name="btns" type="submit" value="Aceptar">
                </form>

            </section>
            <nav class="nav-main">


            </nav>
        </div>

    <%
        // se necesitan  id, nombre , descripcion, precio, idfactura 
        // el nombre es el combo + su numero
        String nombre= request.getParameter("nombre");
        String descripcion= request.getParameter("info");
        float precio = Float.parseFloat(request.getParameter("precio")); 
        String accion = request.getParameter("btns"); 
        int nrosnack = Integer.parseInt(request.getParameter("nrosnack")); 
        // revisar el ID de detallefactura 
        if (accion.equals("Agregar")) {
                snack.ingresar(nombre, descripcion, precio, detallefactura);
            } else if(accion.equals("Modificar")){
                snack.modificar(nombre, descripcion, precio, detallefactura); 
            }else if (accion.equals("Dar de baja")){
                snack.darbaja(nrosnack); 
            }else if (accion.equals("Aceptar")){
            // regresa a la vista anterior
            }; 

    %>
    </body>
</html>
