<%-- 
    Document   : Maestro_ventas
    Created on : 26/09/2021, 18:48:35
    Author     : joseg
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.prueba"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="Modelo.Ventas_Detalle"%>
<%@page import="Modelo.Empleado"%>
<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.Marcas"%>
<%@page import="java.util.HashMap"%>
<%@page import="Modelo.Producto"%>
<%@page import="Controlador.controlador"%>
<%@page import="javax.swing.table.DefaultTableModel"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <title>Maestro Venta Detalle</title>
    </head>
    <body>
        
        <div class="container">
            <form action="controlador_ventas" method="post" class="form-group" >
                <h1>Formulario de ventas</h1>
                
                <%
                    Ventas_Detalle venta_d=  new Ventas_Detalle();
                    int idventa=0;
                    idventa=venta_d.idventamax()+1;
                    String id_venta=String.valueOf(idventa);
                %>
                
                <div class="row">
                        <div class="row g-3">
                            <div class="col-md-2">
                                <label form="lbl_id">ID de la venta</label>
                                <input type="text" name="txt_id_venta_detalle" id="txt_id_venta_detalle" class="form-control" value="<%= id_venta %>" readonly=""> 
                                
                            </div>
                            <div class="col-md-2">
                              <label for="lbl_serie">Numero de Factura</label>
                              <input type="text" class="form-control" name="txt_numero_factura" id="txt_numero_factura" value="<%= id_venta %>" placeholder="Numero de la factura" aria-label="numero de la factura" readonly="">
                            </div>
                            <div class="col-md-2">
                              <label for="lbl_serie">Serie</label>
                              <input type="text" class="form-control" name="txt_serie" id="txt_serie" placeholder="serie de la factura" value="A" aria-label="serie de la factura" readonly="">
                            </div>
                            <div class="col-md-4">
                              <label for="lbl_serie">Fecha de Ingreso</label>
                              <input type="text" class="form-control" name="txt_fecha_ingreso" id="txt_fecha_ingreso" placeholder="Fecha de ingreso" aria-label="Fecha de Ingreso" readonly="">
                            </div>  
                        </div>
                </div>
                
                <div class="row">
                        <div class="row g-3">
                            <div class="col-md-4">
                              <label for="lbl_producto"><b>Producto</b></label>
                                <select name="drop_producto" id="drop_producto" class="form-select">
                                <%
                                    Producto producto = new Producto();
                                    HashMap<String,String> mostrar= producto.mostrar_producto();
                                    for(String i:mostrar.keySet())
                                    {
                                        out.println("<option value="+i+">"+mostrar.get(i)+ "</option>");
                                    }
                                %>
                              </select>
                          </div>
                            <div class="col-md-2">
                              <label for="lbl_cantidad"><b>Cantidad</b></label>
                              <input type="number" class="form-control" name="txt_cantidad" id="txt_cantidad">
                          </div>
                              
                              <%
                                    double precio_unitario=1;
                                    String precio_producto;
                                    int id_producto=5;//Integer.valueOf(request.getParameter("drop_producto"));
                                    precio_unitario=venta_d.precio_unitario(id_producto);
                                    precio_producto=String.valueOf(precio_unitario);     
                                    /*double precio_unitario=1;
                                    String precio_producto="100";
                                    String id_producto=request.getParameter("drop_producto");
                                    precio_unitario=venta_d.prueba_precio(id_producto);
                                    precio_producto=String.valueOf(precio_unitario); */
                              %>
                          <div class="col-md-6">
                              <label for="lbl_cantidad"><b>Precio Unitario</b></label>
                              <input type="number" class="form-control" name="txt_precio_unitario" id="txt_precio_unitario" value="<%= precio_producto%>" readonly>
                          </div>

                        </div>
                </div>
                    <br>
                    <div class="row">
                        <div class="row g-6">
                            <div class="col-md-6">
                                <label for="lbl_cliente"><b>Cliente</b></label>
                                <select name="drop_cliente" id="drop_cliente" class="form-select">
                                <%
                                    Cliente cliente= new Cliente();
                                    Map<String, ArrayList<String>> mostrar_cliente= cliente.mostrar_c();
                                    for(String i:mostrar_cliente.keySet())
                                    {
                                        out.println("<option value="+i+">"+mostrar_cliente.get(i)+ "</option>");
                                    }
                                    
                                    /*Cliente cliente= new Cliente();
                                    HashMap<String,String> mostrar_c= cliente.mostrar_clientes();
                                    for(String i:mostrar_c.keySet())
                                    {
                                        out.println("<option value="+i+">"+mostrar_c.get(i)+ "</option>");
                                    } */
                                %>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <br>
                                    <a href="index_cliente.jsp" class="btn btn-primary">Clientes</a>
                            </div>
                        </div>
                    </div>
                              <br>   
                              
                              
                              <div class="row">
                        <div class="row g-6">
                            <div class="col-md-6">
                                <label for="lbl_empleado"><b>Empleado</b></label>
                                <select name="drop_empleado" id="drop_empleado" class="form-select">
                                <%
                                    Empleado empleado= new Empleado();
                                    HashMap<String,String> mostrar_e= empleado.mostrar_empleado();
                                    for(String i:mostrar_e.keySet())
                                    {
                                        out.println("<option value="+i+">"+mostrar_e.get(i)+ "</option>");
                                    }
                                %>
                              </select>
                            </div>
                            <div class="col-md-6">
                                <br>
                                <a href="index_empleado.jsp" class="btn btn-primary">Empleados</a>
                            </div>
                        </div>
                    </div>
                              
                              <br>   
                              
                    <div class="row">
                        <div class="row g-6">
                            <div class="col-md-6">
                                <label for="lbl_fecha_facturacion"><b>Fecha de Facturacion</b></label>
                                <input type="date" name="txt_fecha_facturacion" id="txt_fecha_facturacion" class="form-control" placeholder="Fecha en que se está facturando">
                            </div>
                        </div>
                    </div>
                    <br>

                   

                              <br> <br>
                    <div class="row">
                        <div class="row g-6">
                            <div class="d-grid gap-2 col-4 mx-auto">
                                <button name="btn_agregar_venta" id="btn_agregar_venta" class="btn btn-success" value="agregar_venta">Agregar Venta</button>
                            </div>
                                  <br>
                            <div class="d-grid gap-2 col-4 mx-auto">
                                <button name="btn_modificar_venta" id="btn_modificar_venta" class="btn btn-primary" value="modificar_venta">Modificar Venta</button>
                            </div>
                            <br>
                            <div class="d-grid gap-2 col-4 mx-auto">
                                <button name="btn_eliminar_venta" id="btn_eliminar_venta" class="btn btn-danger" value="eliminar_venta">Eliminar Venta</button>
                            </div>
                            <center>
                <%--             <div class="list-group">
                                    <br> <br>
                                    <a href="Maestro_ventas.jsp" class="list-group-item list-group-item-action active" aria-current="true">
                                      Formulario Ventas
                                    </a>
                                    <a href="Marcas.jsp" class="list-group-item list-group-item-action">Formulario Clientes</a>
                                    <a href="index.jsp" class="list-group-item list-group-item-action">Fromulario Empleados</a>
                                  </div> --%>
                            </center>
                        </div>
                    </div>
            </form>         
            <br> <br>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>serie </th>
                        <th>No_f </th>
                        <th>fecha_f</th>
                        <th>producto </th>
                        <th>cantidad </th>
                        <th>precio_u</th>
                        <th>nombreC</th>
                        <th>ApellidoC </th>
                        <th>NIT</th>
                        <th>nombreE </th>
                        <th>ApellidoE</th>
                        <th>fechaingreso</th>
                    </tr>
                </thead>
                <tbody id="tbl_ventas_detalle">
                    <%
                    Ventas_Detalle ventas_D = new Ventas_Detalle();
                    DefaultTableModel tabla = new DefaultTableModel();
                    tabla = ventas_D.leer();
                    for (int t=0;t <tabla.getRowCount();t++){
                        out.println("<tr data-id="+tabla.getValueAt(t, 0)+" data-id_producto="+tabla.getValueAt(t, 13)+ " data-id_cliente= "+tabla.getValueAt(t, 14) +" data-id_empleado="+tabla.getValueAt(t, 15)+">");
                        out.println("<td>"+ tabla.getValueAt(t, 1)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 2)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 3)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 4)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 5)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 6)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 7)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 8)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 9)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 10)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 11)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 12)+"</td>");
                        out.println("</tr>");
                    }
                    %>
                </tbody>
            </table>
        </div>
        
        <script src="https://code.jquery.com/jquery-3.6.0.slim.js" integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
        
        <script type="text/javascript">
            $("#tbl_ventas_detalle").on("click","tr td", function(evt)
            {
                var target, id, id_producto, id_cliente, id_empleado,nofact,serie,fechaingreso,producto,cantidad,precio_unitario,cliente,empleado,fechafacturacion,marca_producto;
                target=$(evt.target);
                id=target.parent().data("id");
                id_producto=target.parent().data("id_producto");
                id_cliente=target.parent().data("id_cliente");
                id_empleado=target.parent().data("id_empleado");
                serie=target.parent("tr").find("td").eq(0).html();
                nofact=target.parent("tr").find("td").eq(1).html();
                fechafacturacion=target.parent("tr").find("td").eq(2).html();
                producto=target.parent("tr").find("td").eq(3).html();
                cantidad=target.parent("tr").find("td").eq(4).html();
                precio_unitario=target.parent("tr").find("td").eq(5).html();
                //nombresc, apellidosc, nit
                cliente=target.parent("tr").find("td").eq(6).html();
                //nombres, apellidos
                empleado=target.parent("tr").find("td").eq(7).html(); //el 6 y el 7 son para nombrec y apellidoc y el 8 para el nit
                fechaingreso=target.parent("tr").find("td").eq(11).html();
                marca_producto=target.parent("tr").find("td").eq(9).html();
                
                $("#txt_id_venta_detalle").val(id); //
                $("#txt_numero_factura").val(nofact); //
                $("#txt_serie").val(serie); //
                $("#txt_fecha_ingreso").val(fechaingreso);
                $("#drop_producto").val(id_producto);
                $("#txt_cantidad").val(cantidad); //
                $("#txt_precio_unitario").val(precio_unitario); //
                $("#drop_cliente").val(id_cliente);
                $("#drop_empleado").val(id_empleado);
                $("#txt_fecha_facturacion").val(fechafacturacion); //
                //$("#drop_marcas").val(id);
            });
            /*$("#tbl_ventas_detalle").on("click","tr td", function(evt)
            {
                var target, id, id_venta, producto, descripcion, imagen, precio_costo, precio_venta, existencia, fecha_ingreso, marca;
                target=$(evt.target);
                id=target.parent().data("id");
                id_venta=target.parent().data("id_producto");
                producto=target.parent("tr").find("td").eq(0).html();
                descripcion=target.parent("tr").find("td").eq(1).html();
                imagen=target.parent("tr").find("td").eq(2).html();
                precio_costo=target.parent("tr").find("td").eq(3).html();
                precio_venta=target.parent("tr").find("td").eq(4).html();
                existencia=target.parent("tr").find("td").eq(5).html();
                fecha_ingreso=target.parent("tr").find("td").eq(6).html();
                //marca=target.parent("tr").find("td").eq(7).html();
                $("#txt_id_producto").val(id);
                $("#txt_producto").val(producto);
                $("#txt_descripcion").val(descripcion);
                $("#txt_imagen").val(imagen);
                $("#txt_costo").val(precio_costo);
                $("#txt_venta").val(precio_venta);
                $("#txt_existencia").val(existencia);
                $("#txt_fecha_ingreso").val(fecha_ingreso);
                $("#drop_marcas").val(id_producto);
                
            });*/
            
        </script>
    </body>
</html>
