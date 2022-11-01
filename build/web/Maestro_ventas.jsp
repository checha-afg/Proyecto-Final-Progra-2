<%-- 
    Document   : Maestro_ventas
    Created on : 26/09/2021, 18:48:35
    Author     : joseg
--%>
<%@page import="Modelo.Ventas"%>
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
<%@page import="Controlador.sr_cerrar_sesion"%>
<%@page import="javax.swing.table.DefaultTableModel"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ventas</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/estilo_ventas.css">
        <link href="css/estilo_menu.css" rel="stylesheet" type="text/css"/>

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
                <li><a href="index_compras.jsp">Compras</a>
                    <ul>
                        <li><a href="index_prooveedores.jsp">Proveedores</a></li>
                    </ul>
                </li>
                <li><a href="Menu_reportes.jsp">Reportes</a></li>
                <li>
                    <%
                        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                        if (session.getAttribute("txtUsuario") == null && session.getAttribute("nombre") == null) {
                            response.sendRedirect("index.html");
                        }  %>
                    <a > 
                        <form action="sr_cerrar_sesion" >
                            <input class="cerrar_sesion" type="submit"  value="Cerrar Sesion">
                        </form>
                    </a>
                </li>
            </ul>
        </div>

        <div class="Unido" class="formulario" >
            <!-- Primer Formulario Para Insertar A ventas -->
            <div class="container-fluid  p-3 my-3  text-black">

                <div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" id="modal_ventas" role="dialog">
                    <div class="modal-xl modal-dialog  modal-dialog-scrollable" >
                        <div class="modal-content formula_modal">
                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Formulario Ventas</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>


                            <div class="modal-body ">
                                <form action="control_ventas" class="formulario" method="post" id="formulario" >

                                    <div class="formulario__grupo" id="grupo__txt_id">
                                        <label for="nombre" class="formulario__label">ID</label>
                                        <div class="formulario__grupo-input">
                                            <input type="text" class="formulario__input" name="txt_id" id="txt_id" value="0" readonly>
                                        </div>
                                    </div>
                                    <div class="formulario__grupo" id="grupo__txt_factura">
                                        <label for="nombre" class="formulario__label">No. Factura </label>
                                        <div class="formulario__grupo-input">

                                            <input type="text" class="formulario__input  " name="txt_factura" id="txt_factura"  readonly>
                                        </div>
                                    </div>

                                    <!-- Grupo: Serie -->
                                    <div class="formulario__grupo" id="grupo__txt_apellido">
                                        <label for="apellido" class="formulario__label">Serie </label>
                                        <div class="formulario__grupo-input">
                                            <input type="text" class="formulario__input" name="txt_serie" id="txt_serie" value="A"  readonly>
                                        </div>
                                    </div>

                                    <!-- Grupo: Fecha Factura -->
                                    <div class="formulario__grupo" id="grupo__txt_ffact">
                                        <label for="nit" class="formulario__label">Fecha Factura </label>
                                        <div class="formulario__grupo-input">
                                            <input type="date" class="formulario__input" name="txt_ffact"  id="txt_ffact" required placeholder="yyyy-MM-dd" >
                                        </div>
                                    </div>

                                    <!-- Seleccionar Id_cliente -->
                                    <div class="formulario__grupo" id="grupo__txt_cliente">
                                        <label for="lbl_puesto" class="formulario__label">Clientes</label>
                                        <div class="formulario__grupo-input">
                                            <select name="drop_cliente" class="formulario__input" id="drop_cliente" >
                                                <%
                                                    Cliente c_cliente = new Cliente();
                                                    HashMap<String, String> drop = c_cliente.seleccionar2();
                                                    for (String i : drop.keySet()) {
                                                        out.println("<option value=" + i + ">" + drop.get(i) + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </div>
                                    </div>

                                    <!-- Seleccionar Id_Empleado -->
                                    <div class="formulario__grupo" id="grupo__txt_puesto">
                                        <label for="lbl_puesto" class="formulario__label">Empleados</label>
                                        <div class="formulario__grupo-input">
                                            <select name="drop_empleado" class="formulario__input" id="drop_empleado" >
                                                <%
                                                    Empleado c_empleado = new Empleado();
                                                    HashMap<String, String> drop2 = c_empleado.seleccionar2();
                                                    for (String i : drop2.keySet()) {
                                                        out.println("<option value=" + i + ">" + drop2.get(i) + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </div>
                                    </div>
                                    <!-- Fecha Ingreso-->
                                    <div class="formulario__grupo" id="grupo__txt_fni">
                                        <label for="genero" class="formulario__label">Fecha Ingreso:</label>
                                        <div class="formulario__grupo-input">
                                            <input class="formulario__input" type="text" name="txt_fni" id="txt_fni"  >
                                        </div>
                                    </div>

                                    <div class=" formulario__grupo-btn-enviar">
                                        <button  name="btn_modificar" id="btn_modificar" value="modificar" class="nekros" >Modificar</button>
                                        <button  name="btn_2" id="btn_2" onclick="return confirmar2();" value="eliminar"class="nekros_rojo" > Eliminar </button>
                                    </div>
                                    <button name="btn_eliminar" id="btn_eliminar"  value="eliminar"class="btn_falso" >  </button>
                                    <br>

                                </form>  
                                <!-- Ventas Tabla -->
                                <div class= "">
                                    <table class="table table-hover table-bordered">
                                        <thead class="thead-dark   titulos">
                                            <tr>
                                                <th>No. venta</th> 
                                                <th>No. factura</th> 
                                                <th>Serie</th>
                                                <th>Fecha Factura</th>
                                                <th>Cliente</th>
                                                <th>Empleado</th>
                                                <th>Fecha Ingreso</th>

                                            </tr>
                                        </thead>
                                        <tbody class="formula_tabla" id="tbl_ventas">
                                            <%
                                                Ventas c_vende = new Ventas();
                                                DefaultTableModel tabla = new DefaultTableModel();
                                                int cero = 0;
                                                tabla = c_vende.leer3();
                                                for (int r = 0; r < tabla.getRowCount(); r++) {
                                                    cero++;
                                                    out.println("<td>" + tabla.getValueAt(r, 0) + "</td>");
                                                    out.println("<td>" + tabla.getValueAt(r, 1) + "</td>");
                                                    out.println("<td>" + tabla.getValueAt(r, 2) + "</td>");
                                                    out.println("<td>" + tabla.getValueAt(r, 3) + "</td>");
                                                    out.println("<td>" + tabla.getValueAt(r, 4) + "</td>");
                                                    out.println("<td>" + tabla.getValueAt(r, 5) + "</td>");
                                                    out.println("<td>" + tabla.getValueAt(r, 6) + "</td>");
                                                    out.println("</tr>");
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="button"  class="btn btn-warning mod_salir" onclick="recargar()"   data-dismiss="modal">Salir</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
            </div>
            <!-- Primer Formulario Para Insertar A ventas Detalle -->
            <div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" id="modal_detalle" role="dialog">
                <div class="modal-xl modal-dialog  modal-dialog-scrollable " >
                    <div class="modal-content formula_modal">
                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Formulario Detalle</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <div class="modal-body ">
                            <form action="control_detalles" class="formulario" method="post"  >

                                <div class="formulario__grupo">
                                    <label for="lbl_detalles" class="formulario__label">ID</label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_id_detalle" id="txt_id_detalle" value="0" readonly>
                                    </div>
                                </div>

                                <div class="formulario__grupo" >
                                    <label for="No_venta" class="formulario__label">No. venta</label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="No_ventas" id="No_ventas" value="0" readonly>
                                    </div>
                                </div>

                                <!-- Seleccionar Productos -->
                                <div class="formulario__grupo" >
                                    <label for="lbl_producto" class="formulario__label">Productos</label>
                                    <div class="formulario__grupo-input">
                                        <select name="drop_prod" class="formulario__input" id="drop_prod" >
                                            <%     Producto selectPro = new Producto();
                                                HashMap<String, String> drop44 = selectPro.sele_prod();
                                                for (String i : drop44.keySet()) {
                                                    out.println("<option value=" + i + ">" + drop44.get(i) + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>


                                <div class="formulario__grupo">
                                    <label for="cantidad" class="formulario__label">Cantidad</label>
                                    <div class="formulario__grupo-input">
                                        <input type="number" class="formulario__input" name="cantidad_detalle" id="cantidad_detalle" value="0" >
                                    </div>
                                </div>



                                <div class=" formulario__grupo-btn-enviar">
                                    <button  name="btn_modificar_venta" id="btn_modificar_venta" value="modificar_venta" class="nekros" >Modificar</button>
                                    <button  name="btn_3" id="btn_3" onclick="return confirmar3();" value="eliminar" class="nekros_rojo" > Eliminar </button>
                                </div>
                                <button name="btn_eliminar2" id="btn_eliminar2"  value="eliminar2" class="btn_falso" >  </button>
                                <br>
                            </form>   
                            <!-- Ventas Tabla -->
                            <div class= "">
                                <table class="table table-hover table-bordered">
                                    <thead class="thead-dark   titulos">
                                        <tr>
                                            <th>No. Venta</th> 
                                            <th>Producto</th>
                                            <th>Cantidad</th>
                                            <th>Precio</th>

                                        </tr>
                                    </thead>
                                    <tbody class="formula_tabla" id="tbl_ventasDetalle">
                                        <%
                                            Ventas_Detalle ventas_det = new Ventas_Detalle();
                                            DefaultTableModel tabla4 = new DefaultTableModel();
                                            tabla4 = ventas_det.Mostrar();
                                            for (int r = 0; r < tabla4.getRowCount(); r++) {
                                                out.println("<tr data-id22=" + tabla4.getValueAt(r, 0) + " data-idprodd=" + tabla4.getValueAt(r, 5) + " >");
                                                out.println("<td>" + tabla4.getValueAt(r, 1) + "</td>");
                                                out.println("<td>" + tabla4.getValueAt(r, 2) + "</td>");
                                                out.println("<td>" + tabla4.getValueAt(r, 3) + "</td>");
                                                out.println("<td>" + tabla4.getValueAt(r, 4) + "</td>");
                                                out.println("</tr>");
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                            <div class="modal-footer">
                                <button type="button"  class="btn btn-warning mod_salir" onclick="recargar()"   data-dismiss="modal">Salir</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- The Modal -->
            <div class="modal fade" id="modal_menu">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Acciones</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <div class=" formulario__grupo-btn-enviar">
                                <button  name="btn_ventas" id="btn_ventas" value="ventas"    class="btn btn-danger" onclick="dos()" >Ventas </button>
                                <button  name="detalle" id="detalle" value="detalle" class="btn btn-warning"  onclick="tres()">Detalle</button>
                            </div>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>


            <form action="ser_ventas?menu=Venta" method="post" class="formulario">
                <div  class="MA1  parte1">
                    <h3 class="titulo">Productos Disponibles</h3>
                    <br>

                    <div class="parent2 ">
                        <div class="div_uno"> 
                            <!-- Codigo Producto -->
                            <div class="Centrar" id="grupo__txt_codigo">
                                <label for="lbl_producto" class="formulario__label" >Codigo</label>

                                <div class="formulario__grupo-input">
                                    <input type="text" class="producto_input " value="${proo.getId()}" name="codigoproducto" id="codigoproducto" readonly required>

                                </div>
                            </div>  
                        </div>
                        <div class="div_dos">
                            <!-- Codigo Producto -->
                            <div class="" id="grupo__txt_producto">
                                <label for="lbl_producto" class="formulario__label">Producto</label>
                                <div class="formulario__grupo-input">
                                    <input type="text" class="producto_input " value="${proo.getProducto()}" name="nombreproducto" id="nombreproducto" required readonly >
                                </div>
                            </div>  
                        </div>
                        <div class="div_tres">
                            <!-- Precio Producto -->
                            <div class="" id="grupo__txt_precio">
                                <label for="lbl_precio" class="formulario__label" >Precio</label>
                                <div class="formulario__grupo-input">
                                    <input type="text" class="producto_input " value="${proo.getPrecio()}" name="precio" id="precio" readonly required="" >
                                </div>
                            </div> 
                        </div>
                        <div class="div_cuatro">
                            <!-- Existencia Producto -->
                            <div class="" id="grupo__txt_existencia">
                                <label for="lbl_direccion" class="formulario__label" >Existencia</label>
                                <div class="formulario__grupo-input">
                                    <input type="text" class="producto_input " value="${proo.getExistencia()}" name="existencia" id="existencia" readonly required >
                                </div>
                            </div>  
                        </div>
                        <div class="div_cinco"> 
                            <!-- Cantidad Producto -->
                            <div class="" id="grupo__txt_cantidad">
                                <label for="lbl_cantidad" class="formulario__label" >Cantidad</label>
                                <div class="formulario__grupo-input">
                                    <input type="number" class="producto_input" onkeypress="return event.charCode >= 48" min="1"  name="cantidad" value="1"  id="cantidad">
                                </div>
                            </div>
                        </div>
                        <div class="div_seis"> 
                            <button type="button" class="nekros_amarillo"  data-toggle="modal" data-target="#modal_menu" >
                                Menu
                            </button>
                        </div>
                    </div>

                    <br>

                    <div class="centrar_boton " >

                        <button type="submit" name="accion" value="Agregar" class="nekros" >Agregar</button>  

                        <button type="submit" name="accion" value="Modificar" class="nekros_verde" >Modificar</button>  
                    </div>

                    <div class= " ">
                        <table class="table table-hover table-bordered">
                            <thead class="thead-dark   titulos">
                                <tr>
                                    <th>Producto</th> 
                                    <th>Descripcion</th>
                                    <th>Precio</th>
                                    <th>Existencia</th>
                                    <th>Marca</th>
                                </tr>
                            </thead>
                            <tbody class="formula_tabla" id="tbl_prod">
                                <%                                    Producto cprod = new Producto();
                                    DefaultTableModel tabla77 = new DefaultTableModel();
                                    tabla77 = cprod.leer2();
                                    for (int r = 0; r < tabla77.getRowCount(); r++) {
                                        out.println("<tr data-id=" + tabla77.getValueAt(r, 0) + " data-id_pues=" + tabla77.getValueAt(r, 6) + " >");
                                        out.println("<td>" + tabla77.getValueAt(r, 1) + "</td>");
                                        out.println("<td>" + tabla77.getValueAt(r, 2) + "</td>");
                                        out.println("<td>" + tabla77.getValueAt(r, 3) + "</td>");
                                        out.println("<td>" + tabla77.getValueAt(r, 4) + "</td>");
                                        out.println("<td>" + tabla77.getValueAt(r, 5) + "</td>");
                                        out.println("</tr>");
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>


                <div class="casa26 parte2">
                    <div class="parent">
                        <div class="div1">
                            <!-- Logotipo-->
                            <img src="imagenes/onitech.png">   
                        </div>
                        <div class="div2">
                            <h4 class="formulario_pre">Productos y Sevicios Onitech</h4>    
                        </div>
                        <div class="div3">
                            <h4 class="formulario_pre">3ra Calle Poniente</h4>    
                        </div>
                        <div class="div4">
                            <!-- Fecha Automatica -->
                            <div class="formulario_fecha" id="grupo__txt_Fecha">
                                <label for="lbl_direccion" class="formulario__label" >Fecha </label>
                                <div class="formulario__grupo-input">
                                    <input type="text" class="formulario__input " name="txt_fecha" id="txt_fecha" readonly >
                                </div>
                            </div>  
                        </div>
                        <div class="div5"> 
                            <!-- No factura -->
                            <div class="formulario__grupo" id="grupo__txt_puesto">
                                <label for="lbl_puesto" class="formulario__label">Factura:</label>
                                <div class="formulario__grupo-input">
                                    <select name="drop_fact" class="formulario__input" id="drop_fact" readonly >
                                        <%                                    Ventas max_fact = new Ventas();
                                            HashMap<String, String> fact = max_fact.max_fact();
                                            for (String e : fact.keySet()) {
                                                out.println("<option value=" + fact.get(e) + ">" + fact.get(e) + "</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="div6">
                            <!--No Serie -->
                            <div class="formulario_fecha" id="grupo__txt_serie">
                                <label for="lbl_direccion" class="formulario__label" >Serie</label>
                                <div class="formulario__grupo-input">
                                    <input type="text" class="formulario__input" name="txt_serie" id="txt_serie" value="A" readonly >
                                </div>
                            </div>  

                        </div>
                        <div class="div7">
                            <!-- Obtner Nombre,Apellidos y Nit del cliente -->
                            <div class="formulario__grupo " id="grupo__txt_puesto">
                                <label for="lbl_puesto" class="formulario__label">Clientes:</label>
                                <div class="formulario__grupo-input">
                                    <select name="drop_cliente" class="formulario__input" id="drop_cliente" readonly >
                                        <%
                                            Cliente c_cliente2 = new Cliente();
                                            HashMap<String, String> drop55 = c_cliente2.seleccionar();
                                            for (String m : drop55.keySet()) {
                                                out.println("<option value=" + m + ">" + drop55.get(m) + "</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>
                        </div>


                        <div class="div8"> 
                            <!-- Obtnener Nombre, Apellidos Y Puesto -->
                            <div class="formulario__grupo container" id="grupo__txt_puesto">
                                <label for="lbl_puesto" class="formulario__label">Empleados:</label>
                                <div class="formulario__grupo-input">
                                    <select name="drop_empleado" class="formulario__input" id="drop_empleado" >
                                        <%
                                            Empleado c_empleado88 = new Empleado();
                                            HashMap<String, String> drop12 = c_empleado88.seleccionar();
                                            for (String i : drop12.keySet()) {
                                                out.println("<option value=" + i + ">" + drop12.get(i) + "</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>  
                        </div>
                    </div>

                    <div class="blanco">
                        <div class="table table-hover table-bordered ">
                            <table class="table table-hover " id="tbl_ventas" >
                                <thead class="thead-dark   titulos">
                                    <tr>
                                        <th>No.</th>
                                        <th>Codigo</th>
                                        <th>Producto</th>
                                        <th>Precio</th>
                                        <th>Cantidad</th>
                                        <th>Sub-Total</th>
                                        <th class="accion">Acciones</th>
                                    </tr>
                                </thead>
                                <tbody class="formula_tabla">
                                    <c:forEach var="list" items="${lista}" >
                                        <tr>
                                            <td>${list.getItem()}</td>
                                            <td>${list.getIdproducto()}</td>
                                            <td>${list.getDescripcion()}</td>
                                            <td>Q. ${list.getPrecio()}</td>
                                            <td>${list.getCantidad()}</td>
                                            <td>${list.getSubtotal()}</td>
                                            <td class="d-flex"> 
                                                <a href="ser_ventas?menu=Venta&accion=editar&id=${list.getIdproducto()}&id33=${list.getItem()} "class="btn btn-warning" >Editar</a>
                                                <a href="ser_ventas?menu=Venta&accion=remover&id22=${list.getItem()}" class="btn btn-danger" style="margin-left: 2px" >Borrar</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer d-flex">
                            <button id="accion" name="accion" value="Agregar_venta" class="nekros" >Agregar venta </button>
                            <button id="accion" name="accion" value="Cancelar" class="nekros_rojo" >Cancelar </button>

                            <div class="col-sm-3 ml-auto">
                                <input type="text" name="txt_total" value="Q. ${total}" style="font-weight: bold" readonly class="form-control">  
                            </div>
                        </div>
                    </div>

                </div>
            </form>  
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script type = "text/javascript " src = "js/formulario_prod.js" > </script>                   
            <script type = "text/javascript " src = "js/form_venta.js" > </script>  
        </div>
    </body>
</html>

