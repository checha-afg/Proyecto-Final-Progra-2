<%-- 
    Document   : Maestro_compras
    Created on : 22/10/2021, 00:46:34
    Author     : joseg
--%>

<%@page import="Modelo.Compras"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.prueba"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="Modelo.Ventas_Detalle"%>
<%@page import="Modelo.Proveedor"%>
<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.Marcas"%>
<%@page import="Modelo.Compras_detalle"%>
<%@page import="java.util.HashMap"%>
<%@page import="Modelo.Producto"%>
<%@page import="Controlador.controlador_compras"%>
<%@page import="javax.swing.table.DefaultTableModel"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <link href="css/estilo_menu.css" rel="stylesheet" type="text/css"/>
        <title>Maestro Venta Detalle</title>
    </head>
    <body>
        
        <div id="header">
            <ul class="nav">
                <img src="imagenes/onitech.png" alt=""/>
                <li><a href="index_inicio_principal.jsp">Inicio</a></li>
                <li><a href="index_producto.jsp">Productos</a>
                    <ul>
                        <li><a href="index_marcas.jsp">Marcas</a></li>
                    </ul>
                </li>
                <li><a href="Maestro_ventas.jsp">Ventas</a>
                    <ul>
                        <li><a href="index_cliente.jsp">Clientes</a></li>
                        <li><a href="index_empleado.jsp">Empleados</a>
                            <ul>
                                <li><a href="index_puesto.jsp">Puestos</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li><a href="Maestro_compras.jsp">Compras</a>
                    <ul>
                        <li><a href="index_prooveedores.jsp">Proveedores</a></li>
                    </ul>
                </li>
                <li><a href="">Reportes</a></li>
                <li >
                    <%
       response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
       if(session.getAttribute("txtUsuario")==null&&session.getAttribute("nombre")==null){
           response.sendRedirect("index.html");
       }
                    %>
                    <a > 
                        <form action="sr_cerrar_sesion" >
                            <input class="cerrar_sesion" type="submit"  value="Cerrar Sesion">
                        </form>
                    </a>

                </li>
            </ul>
        </div>
        
        <div class="container">
            <form action="controlador_compras" method="post" class="form-group" >
                <h1>Formulario de Compras</h1>
                
                <%
                    Ventas_Detalle venta_d=  new Ventas_Detalle();
                    Compras_detalle compra_d= new Compras_detalle();
                    int idcompra=0, idcompra2;
                    idcompra2=compra_d.idcompra_max();
                    idcompra=compra_d.idcompra_max()+1;
                    String id_compra2=String.valueOf(idcompra2);
                    String id_compra=String.valueOf(idcompra);
                    
                    Compras compra= new Compras();
                    int orden=0;
                    orden=compra.no_orden()+1;
                    String no_orden=String.valueOf(orden);
                %>
                
                <div class="row">
                        <div class="row g-3">
                            <div class="col-md-2">
                                <label form="lbl_id">ID del detalle</label>
                                <input type="text" name="txt_id_compra_detalle" id="txt_id_compra_detalle" class="form-control" value="<%= id_compra %>" readonly=""> 
                            </div>
                            <div class="col-md-2">
                              <label for="lbl_idCompra">Id compra</label>
                              <input type="text" class="form-control" name="txt_compra" id="txt_compra" value="<%= id_compra %>" placeholder="Numero de la compra" aria-label="numero de la compra" readonly="">
                            </div>
                            <div class="col-md-2">
                              <label for="lbl_orden">Orden</label>
                              <input type="text" class="form-control" name="txt_orden_compra" id="txt_orden_compra" value="<%= no_orden %>"  readonly="">
                            </div>
                            <div class="col-md-4">
                              <label for="lbl_fecha_ingreso">Fecha de Ingreso</label>
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
                                    /*double precio_unitario=1;
                                    String precio_producto;
                                    int id_producto=5;//Integer.valueOf(request.getParameter("drop_producto"));
                                    precio_unitario=venta_d.precio_unitario(id_producto);
                                    precio_producto=String.valueOf(precio_unitario);   */  
                                    
                              %>
                          <div class="col-md-4">
                              <label for="lbl_precio_unitario"><b>Precio Unitario</b></label>
                              <input type="number" step="0.01" class="form-control" name="txt_precio_unitario" id="txt_precio_unitario" >
                          </div>

                        </div>
                </div>
                    <br>
       
                              <div class="row">
                        <div class="row g-6">
                            <div class="col-md-6">
                                <label for="lbl_proveedor"><b>Proveedor</b></label>
                                <select name="drop_proveedor" id="drop_proveedor" class="form-select">
                                <%
                                    Proveedor proveedor= new Proveedor();
                                    HashMap<String,String> mostrar_e= proveedor.mostrar_proveedor();
                                    for(String i:mostrar_e.keySet())
                                    {
                                        out.println("<option value="+i+">"+mostrar_e.get(i)+ "</option>");
                                    }
                                %>
                              </select>
                            </div>
                            <div class="col-md-6">
                                <br>
                                <a href="index_empleado.jsp" class="btn btn-primary">Proveedores</a>
                            </div>
                        </div>
                    </div>
                              
                              <br>   
                    <div class="row">
                        <div class="row g-6">
                            <div class="col-md-6">
                                <label for="lbl_fecha_orden"><b>Fecha de Orden</b></label>
                                <input type="date" name="txt_fecha_orden" id="txt_fecha_orden" class="form-control" placeholder="Fecha en que se está ordenando">
                            </div>
                        </div>
                    </div>
                    <br>

                              <br> <br>
                    <div class="row">
                        <div class="row g-6">
                            <div class="d-grid gap-2 col-4 mx-auto">
                                <button name="btn_agregar_compra" id="btn_agregar_compra" class="btn btn-success" value="agregar_compra">Agregar Compra</button>
                            </div>
                                  <br>
                            <div class="d-grid gap-2 col-4 mx-auto">
                                <button name="btn_modificar_compra" id="btn_modificar_compra" class="btn btn-primary" value="modificar_compra">Modificar Compra</button>
                            </div>
                            <br>
                            <div class="d-grid gap-2 col-4 mx-auto">
                                <button name="btn_eliminar_compra" id="btn_eliminar_compra" class="btn btn-danger" value="eliminar_compra">Eliminar Compra</button>
                            </div>

                        </div>
                    </div>
                              <br>
                    <div class="row">
                        <div class="row g-6">
                            <div class="d-grid gap-2 col-4 mx-auto">
                            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_compras" onclick="limpiar()">Agregar Otro Producto</button>
                            </div>
                        </div>
                    </div>
            </form>         
            <br> <br>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Orden Compra </th>
                        <th>Proveedor </th>
                        <th>Producto</th>
                        <th>cantidad </th>
                        <th>precio costo</th>
                        <th>Fecha Orden</th>
                        <th>Fecha Ingreso </th>

                    </tr>
                </thead>
                <tbody id="tbl_compras_detalle">
                    <%
                    Compras_detalle compras_d= new Compras_detalle();
                    DefaultTableModel tabla = new DefaultTableModel();
                    tabla = compras_d.leer();
                    for (int t=0;t <tabla.getRowCount();t++){
                        out.println("<tr data-id="+tabla.getValueAt(t, 0)+" data-id_proveedor="+tabla.getValueAt(t, 8)+ " data-id_producto= "+tabla.getValueAt(t, 9) + " data-id_compra= "+tabla.getValueAt(t, 10) +">");
                        out.println("<td>"+ tabla.getValueAt(t, 1)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 2)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 3)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 4)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 5)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 6)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 7)+"</td>");
                        
                        out.println("</tr>");
                    }
                    %>
                </tbody>
            </table>
        </div>
                
                
                
                <div class="container">
            <div class="modal fade" id="modal_compras" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">       
                        <div class="modal-body">

                            <form action="controlador_compras2" method="post" class="form-group">
                                <label form="lbl_id_compra"><b>ID</b></label>
                                <input type="text" name="txt_id_compra" id="txt_id_compra" class="form-control" value="<%= id_compra2 %>" readonly="">

                                <div class="row">
                                    <div class="row g-3">
                                        <div class="col-md-6">
                                          <label for="lbl_producto2"><b>Producto</b></label>
                                            <select name="drop_producto2" id="drop_producto2" class="form-select">
                                            <%

                                                HashMap<String,String> mostrar2= producto.mostrar_producto();
                                                for(String i:mostrar.keySet())
                                                {
                                                    out.println("<option value="+i+">"+mostrar2.get(i)+ "</option>");
                                                }
                                            %>
                                        </select>
                                      </div>
                                        <div class="col-md-6"> 
                                            <label for="lbl_cantidad2"><b>Cantidad</b></label>
                                            <input type="number" class="form-control" name="txt_cantidad2" id="txt_cantidad2">
                                        </div>
                                        <div class="col-md-8"> 
                                            <label for="lbl_cantidad2"><b>Precio Costo Unitario</b></label>
                                            <input type="number" step="0.01" class="form-control" name="txt_precio2" id="txt_precio2">
                                        </div>
                                    </div>
                                </div>
                                

                                <br>
                                <button name="btn_agregar_nuevo_prod" id="btn_agregar_nuevo_prod" value="Agregar_nuevo_prod" class="btn btn-primary ">Agregar</button>
                                <button type="button" class="btn btn-warning" data-dismiss="modal">Cerrar</button>
                            </form>
                                    
                        </div>
                    </div>
                                    
                </div>
            </div>
        </div>
        
         <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        
        <script type="text/javascript">
            $("#tbl_compras_detalle").on("click","tr td", function(evt)
            {
                var target, id, id_proveedor, id_producto, idcompra, orden_compra,cantidad,precio_costo,fecha_orden,fecha_ingreso;
                target=$(evt.target);
                id=target.parent().data("id");
                id_proveedor=target.parent().data("id_proveedor");
                id_producto=target.parent().data("id_producto");
                idcompra=target.parent().data("id_compra");
                orden_compra=target.parent("tr").find("td").eq(0).html();
                cantidad=target.parent("tr").find("td").eq(3).html();
                precio_costo=target.parent("tr").find("td").eq(4).html();
                fecha_orden=target.parent("tr").find("td").eq(5).html();
                fecha_ingreso=target.parent("tr").find("td").eq(6).html();

                //nombresc, apellidosc, nit
                cliente=target.parent("tr").find("td").eq(6).html();
                //nombres, apellidos
                empleado=target.parent("tr").find("td").eq(7).html(); //el 6 y el 7 son para nombrec y apellidoc y el 8 para el nit
                fechaingreso=target.parent("tr").find("td").eq(11).html();
                marca_producto=target.parent("tr").find("td").eq(9).html();
                
                $("#txt_id_compra_detalle").val(id); //
                $("#txt_compra").val(idcompra); //
                $("#txt_orden_compra").val(orden_compra); //
                $("#txt_fecha_ingreso").val(fecha_ingreso);
                $("#drop_producto").val(id_producto);
                $("#txt_cantidad").val(cantidad); //
                $("#txt_precio_unitario").val(precio_costo); //
                $("#drop_proveedor").val(id_proveedor);
                $("#txt_fecha_orden").val(fecha_orden);
                
                $("#txt_id_compra").val(idcompra);
            });
            $("#txt_id_compra").on("click", function(evt)
            {
                
                $("#txt_id_compra").val(<%=id_compra2%>);
            });
            
        </script>
    </body>
</html>


