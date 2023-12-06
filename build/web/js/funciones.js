$(document).ready(function () {
    $("tr #btnDelete").click(function () {
        var idp = $(this).parent().find("#idp").val();
        swal({
            title: "Está seguro de eliminar?",
            text: "Once delete, you will not...",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((willDelete) => {
            if (willDelete) {
                eliminar(idp);
                swal("Poof! Your file has been delete", {
                    icon: "success",
                }).then((willDelete) => {
                    if (willDelete) {
                        parent.location.href = "Controlador?accion=Carrito";
                    }
                });
            } else {
                swal("Producto no eliminado");
            }

        });
    });
    function eliminar(idp) {
        var url = "Controlador?accion=Delete";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp=" + idp,
            success: function (data, textStatuts, jqXHR) {
            }

        });
    }
    $("div #agregar").click(function () {
        var id = $(this).parent().find("#idpro").val();
        var url = "Controlador?accion=AgregarCarrito";
        $.ajax({
            type: 'POST',
            url: url,
            data: "id=" + id
        }).done(
                function (data) {
                    $("#contador").text(data);
                }
        );
    });

    $("tr #Cantidad").click(function () {
        var idp = $(this).parent().find("#idpro").val();
        var cantidad = $(this).parent().find("#Cantidad").val();
        var url = "Controlador?accion=ActualizarCantidad";
        $.ajax({
            type: 'POST',
            url: url,
            //En data está la info de las var que se envian al Controlador
            data: "idp=" + idp + "&Cantidad=" + cantidad
        }).done(
                function (data) {
                    console.log(data);

                    $("#Cantidad").text(JSON.stringify(data.cantidad) + ".0");
                    $("#" + idp).text(JSON.stringify(parseFloat(data.carritosub)) + ".0");
                    $("[id$=totalPagar]").attr("value", "$ " + JSON.stringify(parseFloat(data.totalPagar)) + ".00");
                    idp = "";
                    cantidad = "";

                });
    });
        $("#btnBuscar").click(function () {
        var nombre = $("#txtBuscar").val();
        var url = "Controlador?accion=Buscar";
        $.ajax({
            type: 'POST',
            url: url,
            data: "Buscar=" + nombre,
            success: function (data) {
                // Aquí puedes actualizar la página con los resultados de la búsqueda
                // Puedes implementar esta parte según tus necesidades
            }
        });
    });
const quantityInputs = document.querySelectorAll(".quantity");
const decreaseButtons = document.querySelectorAll(".decrease");
const increaseButtons = document.querySelectorAll(".increase");

for (let i = 0; i < quantityInputs.length; i++) {
    decreaseButtons[i].addEventListener("click", function() {
        let quantityInput = quantityInputs[i];
        let currentValue = parseInt(quantityInput.value);
        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
        }
    });

    increaseButtons[i].addEventListener("click", function() {
        let quantityInput = quantityInputs[i];
        let currentValue = parseInt(quantityInput.value);
        quantityInput.value = currentValue + 1;
    });
}

});








