<%-- 
    Document   : prod
    Created on : 24/09/2021, 23:54:48
    Author     : joseg
--%>

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
        <title>Productos</title>
    </head>
    <body>
        
        <div class="container">
            <form action="controlador" method="post" class="form-group" >
                <h1>Formulario de Productos</h1>
                <label form="lbl_id">ID del Producto</label>
                <input type="text" name="txt_id_producto" id="txt_id_producto" class="form-control" value="0" readonly="">
                
                <div class="row">
                        <div class="row g-3">
                            <div class="col-md-6">
                              <label for="lbl_producto"><b>Producto</b></label>
                              <input type="text" class="form-control" name="txt_producto" id="txt_producto" placeholder="Producto" aria-label="Producto">
                            </div>

                            <div class="col-md-2">
                              <label for="lbl_existencia"><b>Existencia</b></label>
                              <input type="number" class="form-control" name="txt_existencia" id="txt_existencia">
                          </div>
                        </div>
                </div>
                    <br>
                    <label for="lbl_descripcion"><b>Descripción</b></label>
                    <input type="text" name="txt_descripcion" id="txt_descripcion" class="form-control" placeholder="Descripcion del producto">
                    <br>
                    <div class="row">
                        <div class="row g-6">
                            <div class="col-md-6">
                                <label for="lbl_costo"><b>Precio Costo</b></label>
                                <div class="input-group mb-3">

                                    <span class="input-group-text" id="basic-addon1">Q</span>
                                    <input type="number" step="0.01" name="txt_costo" id="txt_costo" class="form-control" placeholder="Q##. ##">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label for="lbl_venta"><b>Precio de Venta</b></label>
                                <div class="input-group mb-3">

                                    <span class="input-group-text" id="basic-addon1">Q</span>    
                                    <input type="number" step="0.01" name="txt_venta" id="txt_venta" class="form-control" placeholder="Q##. ##">
                                </div>
                            </div>
                        </div>
                    </div>
                    <label for="lbl_imagenes"><b>Imagen</b></label>
                    <input type="file" name="txt_imagen" id="txt_imagen" class="form-control" placeholder="Link de la imagen del producto">
                    <br>
                    <div class="row">
                        <div class="row g-6">
                            <div class="col-md-6">
                                <label for="lbl_marcas"><b>Marca del Producto</b></label>
                                <select name="drop_marcas" id="drop_marcas" class="form-select">
                                <%
                                    Marcas marca= new Marcas();
                                    HashMap<String,String> desplegar= marca.mostrar_marcas();
                                    for(String i:desplegar.keySet())
                                    {
                                        out.println("<option value="+i+">"+desplegar.get(i)+ "</option>");
                                    }
                                %>
                              </select>
                            </div>
                                    <div class="col-md-6">
                                    <label for="lbl_date"><b>Fecha ingreso</b></label>
                                    <input type="text" class="form-control" name="txt_fecha_ingreso" id="txt_fecha_ingreso" value="YYYY/MM/DD HH:MM" readonly="">
                              </div>     
                        </div>      
                    </div>

                              <br> <br>
                    <div class="row">
                        <div class="row g-6">
                            <div class="d-grid gap-2 col-4 mx-auto">
                                <button name="btn_agregar_productos" id="btn_agregar_productos" class="btn btn-success" value="agregar_producto">Agregar Producto</button>
                            </div>
                                  <br>
                            <div class="d-grid gap-2 col-4 mx-auto">
                                <button name="btn_modificar_productos" id="btn_modificar_productos" class="btn btn-primary" value="modificar_producto">Modificar Producto</button>
                            </div>
                            <br>
                            <div class="d-grid gap-2 col-4 mx-auto">
                                <button name="btn_eliminar_productos" id="btn_eliminar_productos" class="btn btn-danger" value="eliminar_producto">Eliminar Producto</button>
                            </div>
                            <center>
                            <div class="list-group">
                                    <br> <br>
                                    <a href="Producto.jsp" class="list-group-item list-group-item-action active" aria-current="true">
                                      Formulario Productos
                                    </a>
                                    <a href="Marcas.jsp" class="list-group-item list-group-item-action">Formulario Marcas</a>
                                    <a href="index.jsp" class="list-group-item list-group-item-action">Inicio</a>
                                  </div>
                            </center>
                        </div>
                    </div>
            </form>         
            <br> <br>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Producto </th>
                        <th>Descripcion </th>
                        <th>Imagen</th>
                        <th>Precio Costo </th>
                        <th>Precio Venta </th>
                        <th>Existencia</th>
                        <th>Fecha ingreso</th>
                        <th>Marca </th>
                    </tr>
                </thead>
                <tbody id="tbl_productos">
                    <%
                    Producto producto = new Producto();
                    DefaultTableModel tabla = new DefaultTableModel();
                    tabla = producto.leer();
                    for (int t=0;t <tabla.getRowCount();t++){
                        out.println("<tr data-id="+tabla.getValueAt(t, 0)+" data-id_producto="+tabla.getValueAt(t, 9)+">");
                        out.println("<td>"+ tabla.getValueAt(t, 1)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 2)+"</td>");
                        out.println("<td> <img src="+ tabla.getValueAt(t, 3)+" alt='imagen' width='100' height='100'></td>");
                        out.println("<td>"+ tabla.getValueAt(t, 4)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 5)+"</td>");
                        out.println("<td>"+ tabla.getValueAt(t, 6)+"</td>");
                        out.println("<td>"+tabla.getValueAt(t,7)+"</td>");
                        out.println("<td>"+tabla.getValueAt(t,8)+"</td>");
                        //out.println("<td>"+tabla.getValueAt(t,9)+"</td>");
                        out.println("</tr>");
                    }
                    %>
                </tbody>
            </table>
        </div>
        
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        
        <script type="text/javascript">
            $("#tbl_productos").on("click","tr td", function(evt)
            {
                var target, id, id_producto, producto, descripcion, imagen, precio_costo, precio_venta, existencia, fecha_ingreso, marca;
                target=$(evt.target);
                id=target.parent().data("id");
                id_producto=target.parent().data("id_producto");
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
                
            });
            
        </script>
    </body>
</html>
