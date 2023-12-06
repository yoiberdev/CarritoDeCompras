<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Pago</title>
    <!-- Agrega enlaces a Bootstrap y Font Awesome -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="index.jsp"><i class="fas fa-home"></i> Home</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="./Controlador?accion=Nuevo"> Store<span class="sr-only">(current)</span></a>
                    </li>              
                    <li class="nav-item">
                        <a class="nav-link" href="./Controlador?accion=home"><i class="fas fa-plus-circle"></i> Seguir Comprando</a>
                </ul>
                <ul class="navbar-nav btn-group my-2 my-lg-0" role="group">
                    <a style="color: white; cursor: pointer" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fas fa-user-tie"></i> ${logueo}</a>
                    <div class="dropdown-menu text-center dropdown-menu-right">
                        <a class="dropdown-item" href="#"><img src="img/user.png" alt="60" height="60"/></a>                        
                        <a class="dropdown-item" href="#">${nombre}</a>
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#myModal">${correoVal}</a>
                        <div class="dropdown-divider"></div>                      
                    </div>
                </ul>    

            </div>
        </nav>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h5 class="mb-0">
                            <i class="fas fa-credit-card"></i> Información de Pago
                        </h5>
                    </div>
                    <div class="card-body">
                        <form action="Controlador?accion=GenerarCompra" method="post">
                            <div class="form-group">
                                <label for="nombre">
                                    <i class="fas fa-user"></i> Nombre del titular de la tarjeta
                                </label>
                                <input type="text" class="form-control" id="nombre" placeholder="Escribe el nombre del titular">
                            </div>
                            <div class="form-group">
                                <label for="tarjeta">
                                    <i class="fas fa-credit-card"></i> Número de Tarjeta
                                </label>
                                <input type="text" class="form-control" id="tarjeta" placeholder="Escribe el número de tarjeta">
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="vencimiento">
                                        <i class="fas fa-calendar-alt"></i> Fecha de Vencimiento
                                    </label>
                                    <input type="text" class="form-control" id="vencimiento" placeholder="MM/AA">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="cvv">
                                        <i class="fas fa-lock"></i> CVV
                                    </label>
                                    <input type="text" class "form-control" id="cvv" placeholder="CVV">
                                </div>
                            </div>
                            <div class="form-group">
                                <hr>
                            </div>
                            <button type="submit" class="btn btn-primary">
                                Pagar <i class="fas fa-check"></i>
                            </button>
                            <a href="carrito.jsp"><button class="btn btn-danger">Cancelar <i class="fas fa-times"></i></button>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Agrega los enlaces a los scripts de Bootstrap y jQuery (opcional) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
