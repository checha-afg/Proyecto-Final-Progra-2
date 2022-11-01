var c, No_factura;
var f = new Date();

var fecha = f.getFullYear() + '-' + (f.getMonth() + 1) + '-' + f.getDate();

//alert(c);
$("#txt_fecha").val(fecha);

$('#tbl_prod').on('click', 'tr td', function (evt) {
    var target, id, producto, descripcion, precio, existencia, marca;
    target = $(event.target);
    id = target.parent().data('id');
    producto = target.parent("tr").find("td").eq(0).html();
    descripcion = target.parent("tr").find("td").eq(1).html();
    precio = target.parent("tr").find("td").eq(2).html();
    existencia = target.parent("tr").find("td").eq(3).html();
    marca = target.parent("tr").find("td").eq(4).html();
    
    $("#codigoproducto").val(id);
    $("#nombreproducto").val(producto);
    $("#precio").val(precio);
    $("#existencia").val(existencia);

});