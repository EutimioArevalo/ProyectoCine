<%-- 
    Document   : seleccionarAsiento
    Created on : 30 jul. 2021, 12:15:52
    Author     : timoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
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
    </head>
    <%

        HttpSession sesion = request.getSession();

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

    %>

    <body>

        <div class="container">
            <div class="titulo_centrar">
                <h4>Seleccione sus asientos:</h4>
                <p><%=idCuenta%></p>
                <p><%=idPelicula%></p>
                <p><%=idRol%></p>
            </div>

            <!-- tabla de asientos  -->
            <div class="container_table">

                <table class="table">
                    <thead>
                        <tr>
                            <th>
                                <h5 style="color:white;">A</h5>
                            </th>
                            <th>
                                <h5 style="color:white;">B</h5>
                            </th>
                            <th>
                                <h5 style="color:white;">C</h5>
                            </th>
                            <th>
                                <h5 style="color:white;">D</h5>
                            </th>
                            <th>
                                <h5 style="color:white;">E</h5>
                            </th>
                            <th>
                                <h5 style="color:white;">F</h5>
                            </th>
                            <th>
                                <h5 style="color:white;">G</h5>
                            </th>
                            <th>
                                <h5 style="color:white;">H</h5>
                            </th>
                            <th>
                                <h5 style="color:white;">I</h5>
                            </th>
                            <th>
                                <h5 style="color:white;">J</h5>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <h5 style="color:white;">A1</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">B1</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">C1</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">D1</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">E1</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">F1</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">G1</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">H1</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">I1</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">J1</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <h5 style="color:white;">A2</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">B2</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">C2</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">D2</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">E2</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">F2</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">G2</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">H2</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">I2</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">J2</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <h5 style="color:white;">A3</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">B3</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">C3</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">D3</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">E3</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">F3</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">G3</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">H3</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">I3</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">J3</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <h5 style="color:white;">A4</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">B4</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">C4</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">D4</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">E4</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">F4</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">G4</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">H4</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">I4</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">J4</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <h5 style="color:white;">A5</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">B5</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">C5</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">D5</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked>
                            </td>
                            <td>
                                <h5 style="color:white;">E5</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">F5</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" >
                            </td>
                            <td>
                                <h5 style="color:white;">G5</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" >
                            </td>
                            <td>
                                <h5 style="color:white;">H5</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">I5</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked">
                            </td>
                            <td>
                                <h5 style="color:white;">J5</h5><input class="form-check-input" type="checkbox" value=""
                                                                       id="flexCheckChecked" checked onclick="return false;">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="row centrar">
                <div class="col">               
                    <a href="../comprarSnacks/comprarSnacks.jsp" class="btn btn-primary" tabindex="-1" role="button"
                       >CONTINUAR</a>
                </div>
                <div class="col">
                    <a href="../inicio/inicio.jsp" class="btn btn-danger btn-lg" tabindex="-1" role="button"
                       >CANCELAR</a>
                </div>
            </div>
    </body>

</html>
