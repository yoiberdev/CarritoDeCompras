<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <img class="logo" src="img/portada.png" alt="Logo">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous">
        <link href="css/newcss.css" rel="stylesheet" type="text/css"/>
        
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
                        <a class="nav-link" href="Controlador?accion=Carrito"><i class="fas fa-cart-plus">( <label id="contador"  style="color: #2C3E50">${contador}</label> )</i>Carrito</a>
<!--                        <a class="nav-link" href="Controlador?accion=Carrito" tabindex="-1" aria-disabled="true"><i class="fas fa-cart-plus">(<label style="color: orange">\${contador}</label>)</i>Carrito</a>-->
                    </li>
                </ul>
 
            </div>
        </nav>
        <div class="container mt-2">
            <div class="row">
                <c:forEach var="p" items="${productos}">
                    
                    <div class="col-sm-3">
                        <div class="card-deck">
                            <div class="card text-white bg-dark border-secondary mb-3" style="max-width: 14rem;">
                                <img src="ControladorIMG?id=${p.getId()}" width="200" height="200" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title"></h5>
                                    <p class="card-text" id="readMoreContent">${p.getDescripcion()}</p>
                                    <span class="text-center text-muted" style="text-decoration: line-through; display: block;">$ 0.00</span>
                                    <div class="text-center mb-2">
                                        <span class="h6 text-danger" style="font-weight: lighter;">$.${p.getPrecio()}</span>
                                        <span style="background-color: #EB0029; color: #ffffff; padding: 2px 6px; border-radius: 5px;">-26%</span>
                                    </div>
                                    <div class="input-group mb-2">
                                    </div>
                                    <a href="#" id="agregar" class="btn btn-secondary btn-block">Agregar a Carrito</a>
                                    <input type="hidden" id="idpro" value="${p.getId()}">
                                    <a href="Controlador?accion=Comprar&id=${p.getId()}" class="btn btn-secondary btn-block mt-2">Comprar</a>
                                    <p class="card-text">
                                        <small class="text-muted" id="last-updated">Last updated 1 m.</small>
                                    </p>
                                </div>
                            </div>
                            <!-- Repite este bloque para cada tarjeta -->
                        </div>
                    </div>
                    
                                    

                </c:forEach>
            </div>
        </div>
        <!-- Modal Iniciar Session o Registrarse -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="container col-lg-9">                   
                    <div class="modal-content">                   
                        <div class="pr-2 pt-1">                         
                            <button type="button" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                            </button>                    
                        </div>
                        <div class="text-center">                         
                            <img src="img/user.png" width="100" height="100">                         
                        </div>
                        <div class="modal-header text-center">                      
                            <ul class="nav nav-pills">                           
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="pill" href="#pills-iniciar">Iniciar Sesion</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="pill" href="#pills-registrar">Registrarse</a>
                                </li>                          
                            </ul>  
                        </div>
                        <div class="modal-body"> 
                            <div class="tab-content" id="pills-tabContent">
                                <!-- Iniciar Session -->
                                <div class="tab-pane fade show active" id="pills-iniciar" role="tabpanel">
                                    <form action="Controlador" method="post">
                                        <div class="form-group">
                                            <label>Email address</label>
                                            <input type="email" name="txtemail" class="form-control" placeholder="email@example.com">
                                        </div>
                                        <div class="form-group">
                                            <label>Password</label>
                                            <input type="password" name="txtpass" class="form-control" placeholder="Password">
                                        </div>                                   
                                        <button type="submit" name="accion" value="Validar" class="btn btn-danger btn-block">Iniciar</button>
                                    </form>
                                </div>
                                <!-- Registrarse -->
                                <div class="tab-pane fade" id="pills-registrar" role="tabpanel">
                                    <form action="SvClientes" method="post">                               
                                        <div class="form-group">
                                            <label>Nombres</label>
                                            <input type="text" name="nombres" class="form-control" placeholder="Nombres completos">
                                        </div>
                                        <div class="form-group">
                                            <label>Dni</label>
                                            <input type="number" name="dni" class="form-control" placeholder="01245678">
                                        </div>
                                        <div class="form-group">
                                            <label>Direccion</label>
                                            <input type="text" name="direccion" class="form-control" placeholder="Lima - Independencia">
                                        </div>
                                        <div class="form-group">
                                            <label>Correo</label>
                                            <input type="email" name="correo" class="form-control" placeholder="email@example.com">
                                        </div>
                                        <div class="form-group">
                                            <label>Password</label>
                                            <input type="password" name="password" class="form-control" placeholder="Password">
                                        </div>                                  
                                        <button type="submit" name="nuevoCliente" value="Registrar" class="btn btn-danger btn-block">Crear Cuenta</button>
                                    </form>
                                </div>                          
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
        </div>





        <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="js/funciones.js" type="text/javascript"></script>
        
    </body>
</html>
