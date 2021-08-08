<%-- 
    Document   : adminPeliculas
    Created on : 4 ago. 2021, 19:47:28
    Author     : josue
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
    <link rel="stylesheet" href="../styles.css">
    <link rel="stylesheet" href="./adminPeliculas.css">
    <title>Administrar Películas</title>
</head>

<body>
    <div class="margin">
    </br>
        <div class="container">
            <!-- primera parte -->
            <div class="row">
                <div class="col">
                    <div class="mb-3 row">
                        <label for="titulo" class="col-sm-4 col-form-label">Título: </label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="titulo">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="sinopsis" class="col-sm-4 col-form-label">Sinopsis: </label>
                        <div class="col-sm-7">
                            <textarea type="text" class="form-control" id="sinopsis" rows="7"></textarea>
                        </div>
                    </div>
                </div>
                <div class="col col_remaster">
                    <div class="row item_image">
                        <div>
                            <img src="../imagenes/geminis.jpg" height="150" width="150" alt="imagen" />
                        </div>

                    </div>
                    <div class="row">
                        <div class="input-group mb-3 button_image">
                            <label class="custom-file-upload" for="inputGroupFile01">Agregar Portada</label>
                            <input type="file" class="form-control" id="inputGroupFile01" style="display: none;">
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="col col_remaster">
                        <div class="row item_image">
                            <div>
                                <video width="250" height="250" src="../imagenes/trailer.mp4" type='video/mp4' controls>
                                </video>

                            </div>

                        </div>
                        <div class="row container_button">
                            <button type="button" class="btn btn-primary button_trailer">Agregar Trailer</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- segunda -->
            <div class="segunda">
                <div class="row">
                    <div class="col">
                        <div class="row">
                            <div class="col">
                                Duración:
                            </div>
                            <div class="col-sm-3">
                                <input type="number" class="form-control" id="hora">
                            </div>
                            <div class="col-sm-1">H</div>
                            <div class="col-sm-3">
                                <input type="number" class="form-control" id="hora">
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="montoTotal mb-3 row ">
                            <label for="monto_total" class="col-sm-3 col-form-label">Idioma: </label>
                            <div class="col-sm-5">
                                <select class="form-select" aria-label="Default select example">
                                    <option selected>Seleccione</option>
                                    <option value="1">Español</option>
                                    <option value="2">Ingles</option>
                                    <option value="3">Japones</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="row">
                            <div class="col">
                                Subtítulos
                            </div>
                            <div class="col">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="efectivo">
                                    <label class="form-check-label" for="efectivo">
                                        Si
                                    </label>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="tarjeta">
                                    <label class="form-check-label" for="tarjeta">
                                        No
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- tercera -->
            <div class="row">
                <div class="col">
                    <div class="montoTotal mb-3 row ">
                        <label for="monto_total" class="col-sm-3 col-form-label">Genero: </label>
                        <div class="col-sm-5">
                            <select class="form-select" aria-label="Default select example">
                                <option selected>Seleccione</option>
                                <option value="1">Terror</option>
                                <option value="2">Acción</option>
                                <option value="3">Romántico</option>
                                <option value="3">Comedia</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="mb-3 row">
                        <label for="director" class="col-sm-4 col-form-label">Director: </label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="director">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="protagonista" class="col-sm-4 col-form-label">Protagonista: </label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="protagonista">
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="montoTotal mb-3 row ">
                        <label for="monto_total" class="col-sm-4 col-form-label">Clasificación:</label>
                        <div class="col-sm-5">
                            <select class="form-select" aria-label="Default select example">
                                <option selected>Seleccione</option>
                                <option value="1">A</option>
                                <option value="2">B</option>
                                <option value="3">C</option>
                                <option value="3">D</option>
                            </select>
                        </div>
                    </div>
                    <div class="montoTotal mb-3 row ">
                        <label for="monto_total" class="col-sm-4 col-form-label">Resolución: </label>
                        <div class="col-sm-5">
                            <select class="form-select" aria-label="Default select example">
                                <option selected>Seleccione</option>
                                <option value="1">1080P</option>
                                <option value="2">720P</option>
                                <option value="3">300P</option>
                            </select>
                        </div>
                    </div>
                    <div class="montoTotal mb-3 row ">
                        <label for="monto_total" class="col-sm-4 col-form-label">Formato de video: </label>
                        <div class="col-sm-5">
                            <select class="form-select" aria-label="Default select example">
                                <option selected>Seleccione</option>
                                <option value="1">MOV</option>
                                <option value="2">AVI</option>
                                <option value="3">MP4</option>
                                <option value="3">FLV</option>
                            </select>
                        </div>
                    </div>
                </div>

            </div>
            <!-- cuarta -->
            <div class="row">

                <div class="col centrar">
                    <button type="button" class="btn btn-success button_trailer">Confirmar</button>
                </div>
                <div class="col centrar">
                    <button type="button" class="btn btn-danger button_trailer">Cancelar</button>
                </div>
            </div>
        </div>
    </div>
</body>

</html>