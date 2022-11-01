var suma, hoy, f, fila;

hoy = new Date();
f = new Date();

//var fecha = hoy.getDate() + '-' + ( hoy.getMonth() + 1 ) + '-' + hoy.getFullYear();
var hora = hoy.getHours() + ':' + hoy.getMinutes() + ':' + hoy.getSeconds();
var fecha = f.getFullYear() + '-' + (f.getMonth() + 1) + '-' + f.getDate();
var fechaYHora = fecha + ' ' + hora;
$("#txt_fni").val(fechaYHora);
$("#txt_ffact").val(fecha);

//////////////////////// Obtner numero de Filas de la tabla
//var x = document.getElementById("tbl_cliente").rows.length;
fila = $('#tbl_cliente tr').length;
suma = fila + 1;

$("#txt_factura").val(suma);

function dos() {
    $("#modal_ventas").modal('show');
    $("#modal_menu").removeClass('show');

    //$("#modalID .close").click()
}

function tres() {
    $("#modal_detalle").modal('show');
    $("#modal_menu").removeClass('show');
}


$('#tbl_ventas').on('click', 'tr td', function (evt) {
    var target, id, No_factura, Serie, Fecha_factura, cliente, empleado, Fecha_ingreso;
    target = $(event.target);
    id = target.parent("tr").find("td").eq(0).html();
    No_factura = target.parent("tr").find("td").eq(1).html();
    Serie = target.parent("tr").find("td").eq(2).html();
    Fecha_factura = target.parent("tr").find("td").eq(3).html();
    cliente = target.parent("tr").find("td").eq(4).html();
    empleado = target.parent("tr").find("td").eq(5).html();
    Fecha_ingreso = target.parent("tr").find("td").eq(6).html();


    $("#txt_id").val(id);
    $("#txt_factura").val(No_factura);
    $("#txt_serie").val(Serie);
    $("#txt_ffact").val(Fecha_factura);
    $("#drop_cliente").val(cliente);
    $("#drop_empleado").val(empleado);
    $("#txt_fni").val(Fecha_ingreso);
    $("#modal_ventas").modal('show');


});
function confirmar2(evt) {

    Swal.fire({
        title: 'Eliminar',
        text: "Desea eliminar el registro?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value === true) {
            Swal.fire({
                title: 'Eliminado',
                html: '<h5 style=color:lime><br><b>Datos Eliminados Correctamente!!</b></h5>',
                icon: 'success',
                showConfirmButton: false
            });

            $("#btn_eliminar").click();
        } else {
            Swal.fire({
                confirmButtonColor: '#d33',
                icon: 'error',
                title: 'Cancelado',
                text: 'Datos No Eliminados'

            });
        }
    });
    return false;
}


$('#tbl_ventasDetalle').on('click', 'tr td', function (evt) {
    var target, id, No_venta, producto, cantidad, PrecioU, productoId;
    target = $(event.target);
    id = target.parent().data('id22');
    productoId = target.parent().data('idprodd');
    No_venta = target.parent("tr").find("td").eq(0).html();
    producto = target.parent("tr").find("td").eq(1).html();
    cantidad = target.parent("tr").find("td").eq(2).html();
    PrecioU = target.parent("tr").find("td").eq(3).html();

    $("#txt_id_detalle").val(id);
    $("#No_ventas").val(No_venta);
    $("#drop_prod").val(productoId);
    $("#cantidad_detalle").val(cantidad);
 //   $("#drop_precio").val(productoId);
    $("#modal_detalle").modal('show');


});


function confirmar3(evt) {

    Swal.fire({
        title: 'Eliminar',
        text: "Desea eliminar el registro?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value === true) {
            Swal.fire({
                title: 'Eliminado',
                html: '<h5 style=color:lime><br><b>Datos Eliminados Correctamente!!</b></h5>',
                icon: 'success',
                showConfirmButton: false
            });

            $("#btn_eliminar2").click();
        } else {
            Swal.fire({
                confirmButtonColor: '#d33',
                icon: 'error',
                title: 'Cancelado',
                text: 'Datos No Eliminados'

            });
        }
    });
    return false;
}