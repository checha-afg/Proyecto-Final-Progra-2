<%-- 
    Document   : index_producto
    Created on : 20/10/2021, 20:19:59
    Author     : joseg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Marcas"%>
<%@page import="java.util.HashMap"%>
<%@page import="Modelo.Producto"%>
<%@page import="Controlador.*"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link href="css/estilo_menu.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/estilo_producto.css">
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

        <button type="button" class="btn_form" data-toggle="modal" data-target="#modal_producto" onclick="Limpiar()">
            Agregar 
        </button>
        <button type="button" class="btn_form  esconder" data-toggle="modal" data-target="#modal_producto44" onclick="Limpiar()">
            prueba
        </button>




        <div class="container-fluid p-3 my-3   text-black">
            <div class="formulario_titulo">
                <h5>Formulario Productos</h5>
            </div>
            <br>
            <div class="modal fade"  data-backdrop="static" data-keyboard="false" tabindex="-1" id="modal_producto" role="dialog">
                <div class="modal-dialog modal-xl">
                    <div class="modal-content formula_modal">
                        <div class="modal-body  ">

                            <button type="button" class="enlaze_modal" onclick="window.location = 'index_producto.jsp'">Marcas</button> 

                            <form action="prod44" method="post" class="formulario" name="formulario"  class="form-group" id="formulario" enctype="multipart/form-data" >
                                <!-- ID -->
                                <div class="formulario__grupo" id="grupo_txt_id_producto1">
                                    <label for="lbl_id" class="formulario__label">ID:</label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_id_producto1" id="txt_id_producto1" value="0" readonly>
                                    </div>
                                </div>

                                <!-- Producto -->
                                <div class="formulario__grupo" id="grupo__txt_producto1">
                                    <label for="lbl_producto" class="formulario__label">Producto </label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_producto1" id="txt_producto1" pattern="^[a-zA-Z0-9,.!? ]*$" placeholder="Producto" required >
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Solo esta permitido letras,numeros,comas y puntos.</p>
                                </div>

                                <!-- Existencia -->

                                <div class="formulario__grupo" id="grupo__txt_existencia">
                                    <label for="lbl_descripcion" class="formulario__label">Existencia </label>
                                    <div class="formulario__grupo-input">
                                        <input type="number" class="formulario__input" name="txt_existencia1" id="txt_existencia1" required>
                                    </div>
                                </div>

                                <!--Descripción -->

                                <div class="formulario__grupo" id="grupo__txt_descripcion1">
                                    <label for="lbl_descripcion" class="formulario__label">Descripcion </label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_descripcion1" id="txt_descripcion1" pattern="^[a-zA-Z0-9,.!? ]*$" placeholder="Descripcion del producto" required>
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Solo esta permitido letras,numeros,comas y puntos.</p>
                                </div>

                                <!-- Precio Costo -->

                                <div class="formulario__grupo" id="grupo__txt_pcos">
                                    <label for="lbl_descripcion" class="formulario__label">Precio Costo </label>
                                    <div class="formulario__grupo-input">
                                        <span class="input-group-text" style="position: absolute; margin-left: 0px; margin-top: 0px;">Q</span>
                                        <input type="number" style="padding-left: 40px;" class="formulario__input" step="0.01" name="txt_costo1" id="txt_costo1" required class="form-control" placeholder="Q##. ##">
                                    </div>
                                </div>
                                <!-- Precio Venta-->
                                <div class="formulario__grupo" id="grupo__txt_pven">
                                    <label for="lbl_descripcion" class="formulario__label">Precio Venta </label>
                                    <div class="formulario__grupo-input">
                                        <span class="input-group-text" style="position: absolute; margin-left: 0px; margin-top: 0px;">Q</span>
                                        <input type="number" style="padding-left: 40px;"  class="formulario__input" step="0.01" name="txt_venta1" id="txt_venta1" required placeholder="Q##. ##">
                                    </div>
                                </div>
                                <!-- Imagen -->
                                <div class="formulario__grupo" id="grupo__txt_imagen">
                                    <label for="lbl_imagen" class="formulario__label">Imagen </label>
                                    <div class="formulario__grupo-input">

                                        <input  type="file" required  name="txt_imagen1" id="txt_imagen1"   placeholder="Link de la imagen del producto" >

                                    </div>
                                </div>

                                <!-- Marcas -->

                                <div class="formulario__grupo" id="grupo__txt_marcas">
                                    <label for="lbl_marcas" class="formulario__label">Marcas</label>
                                    <div class="formulario__grupo-input">
                                        <select name="drop_marcas1" id="drop_marcas1" class="formulario__input" required>
                                            <%
                                                Marcas marca = new Marcas();
                                                HashMap<String, String> desplegar = marca.mostrar_marcas();
                                                for (String i : desplegar.keySet()) {
                                                    out.println("<option value=" + i + ">" + desplegar.get(i) + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>    
                                <!-- Fecha Ingreso -->

                                <div class="formulario__grupo" id="grupo__txt_fni">
                                    <label for="lbl_fecha" class="formulario__label">Fecha Ingreso </label>
                                    <input type="text" class="formulario__input" name="txt_fecha_ingreso1" id="txt_fecha_ingreso1" value="YYYY/MM/DD HH:MM" readonly="">
                                </div>     
                                <br> 
                                <div class=" formulario__grupo-btn-enviar">
                                    <button  name="btn_agregar_productos" id="btn_agregar_productos" value="agregar_producto" class="formulario__btn" >Agregar</button>
                                </div>


                            </form>  

                            <div class="modal-footer">
                                <button  type="button" class="btn btn-warning mod_salir" onclick="recargar()"  onclick="unselect()" data-dismiss="modal">Salir</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>       
            <!-- ---------------------------- Segundo Formulario MODIFICAR ELIMINAR ------------------------------------------------------- -->                                    

            <div class="modal fade"  id="modal_producto44" role="dialog"  data-backdrop="static" data-keyboard="false" tabindex="-1" >
                <div class=" modal-xl modal-dialog  modal-dialog-scrollable ">
                    <div class="modal-content modal_ventana">
                        <div class="modal-body  ">


                            <button type="button" class="enlaze_modal" onclick="window.location = 'index_producto.jsp'">Marcas</button> 
                            <button type="button" class="enlaze_modal" data-toggle="modal" data-target="#modal_producto99" onclick="Limpiar()">
                                Modificar Imagen 
                            </button>


                            <form action="Eliminar" method="post" class="formulario " name="formulario2"  class="form-group" id="formulario2" >
                                <!-- ID -->
                                <div class="formulario__grupo" id="grupo_txt_id">
                                    <label for="lbl_id" class="formulario__label">ID:</label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_id_producto" id="txt_id_producto" value="0" readonly>
                                    </div>
                                </div>

                                <!-- Producto -->
                                <div class="formulario__grupo" id="grupo__txt_producto">
                                    <label for="lbl_producto" class="formulario__label">Producto </label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_producto" id="txt_producto" pattern="[a-zA-Z0-9_-]{6,16}$" placeholder="Producto" required >
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Solo esta permitido letras,numeros,comas y puntos.</p>
                                </div>

                                <!-- Existencia -->

                                <div class="formulario__grupo" id="grupo__txt_existencia">
                                    <label for="lbl_descripcion" class="formulario__label">Existencia </label>
                                    <div class="formulario__grupo-input">
                                        <input type="number" class="formulario__input" name="txt_existencia" id="txt_existencia" required>
                                    </div>
                                </div>

                                <!--Descripción -->

                                <div class="formulario__grupo" id="grupo__txt_descripcion">
                                    <label for="lbl_descripcion" class="formulario__label">Descripcion </label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_descripcion" id="txt_descripcion" placeholder="Descripcion del producto" required>
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Solo esta permitido letras,numeros,comas y puntos.</p>
                                </div>

                                <!-- Precio Costo -->

                                <div class="formulario__grupo" id="grupo__txt_pcos">
                                    <label for="lbl_descripcion" class="formulario__label">Precio Costo </label>
                                    <div class="formulario__grupo-input">
                                        <span class="input-group-text" style="position: absolute; margin-left: 0px; margin-top: 0px;">Q</span>
                                        <input type="number" style="padding-left: 40px;" class="formulario__input" step="0.01" name="txt_costo" id="txt_costo" required class="form-control" placeholder="Q##. ##">
                                    </div>
                                </div>
                                <!-- Precio Venta-->
                                <div class="formulario__grupo" id="grupo__txt_pven">
                                    <label for="lbl_descripcion" class="formulario__label">Precio Venta </label>
                                    <div class="formulario__grupo-input">
                                        <span class="input-group-text" style="position: absolute; margin-left: 0px; margin-top: 0px;">Q</span>
                                        <input type="number" style="padding-left: 40px;"  class="formulario__input" step="0.01" name="txt_venta" id="txt_venta" required placeholder="Q##. ##">
                                    </div>
                                </div>

                                <!-- Marcas -->

                                <div class="formulario__grupo" id="grupo__txt_marcas">
                                    <label for="lbl_marcas" class="formulario__label">Marcas</label>
                                    <div class="formulario__grupo-input">
                                        <select name="drop_marcas" id="drop_marcas" class="formulario__input" required>
                                            <%
                                                Marcas marca33 = new Marcas();
                                                HashMap<String, String> desplegar1 = marca33.mostrar_marcas();
                                                for (String i : desplegar.keySet()) {
                                                    out.println("<option value=" + i + ">" + desplegar1.get(i) + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>    
                                <!-- Fecha Ingreso -->

                                <div class="formulario__grupo" id="grupo__txt_fni">
                                    <label for="lbl_fecha" class="formulario__label">Fecha Ingreso </label>
                                    <input type="text" class="formulario__input" name="txt_fecha_ingreso" id="txt_fecha_ingreso" value="YYYY/MM/DD HH:MM" readonly="">
                                </div>     
                                <br> 
                                <div class=" formulario__grupo-btn-enviar">
                                    <button  name="btn_modificar" id="btn_modificar" value="modificar" class="formulario__btn1" >Modificar</button>
                                    <button  name="btn_2" id="btn_2" onclick="return confirmar2();" value="eliminar"class="formulario__btn2" > Eliminar </button>
                                </div>
                                <button name="btn_eliminar" id="btn_eliminar"  value="eliminar" class="btn_falso" > </button>


                            </form>  

                            <div class="modal-footer">
                                <button  type="button" class="btn btn-warning mod_salir" onclick="recargar()"  onclick="unselect()" data-dismiss="modal">Salir</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>    

            <!-- ------------------------------------------------------------------------- Nueva Ventana Modal ---------------------------------------------------------------------------------- -->
            <div class="modal fade"   id="modal_producto99" role="dialog">
                <div class="modal-dialog modal-xl modal-dialog-centered">
                    <div class="modal-content modal_ventana">
                        <div class="modal-body">
                            <button type="button" class="enlaze_modal" onclick="window.location = 'index_producto.jsp'">Marcas</button> 
                            <form action="mod1" method="post" class="formulario" name="formulario22"  class="form-group" id="formulario22" enctype="multipart/form-data" >
                                <!-- ID -->
                                <div class="formulario__grupo" id="grupo_txt_id">
                                    <label for="lbl_id" class="formulario__label">ID:</label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_id_producto22" id="txt_id_producto22" value="0" readonly>
                                    </div>
                                </div>

                                <!-- Producto -->
                                <div class="formulario__grupo" id="grupo__txt_producto">
                                    <label for="lbl_producto" class="formulario__label">Producto </label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_producto22" id="txt_producto22" pattern="[a-zA-Z0-9_-]{6,16}$" placeholder="Producto" readonly >
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Solo esta permitido letras,numeros,comas y puntos.</p>
                                </div>

                                <!--Descripción -->
                                <div class="formulario__grupo" id="grupo__txt_descripcion">
                                    <label for="lbl_descripcion" class="formulario__label">Descripcion </label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_descripcion22" id="txt_descripcion22" pattern="[a-zA-Z0-9_-]{6,16}$" placeholder="Descripcion del producto" readonly>
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Solo esta permitido letras,numeros,comas y puntos.</p>
                                </div>

                                <!-- Imagen -->
                                <div class="formulario__grupo" id="grupo__txt_imagen">
                                    <label for="lbl_imagen" class="formulario__label">Imagen </label>
                                    <div class="formulario__grupo-input">

                                        <input  type="file"   name="txt_imagen22" id="txt_imagen22"   placeholder="Link de la imagen del producto" >
                                    </div>
                                </div>

                                <div class="formulario__grupo-btn-enviar  ">
                                    <img class=" estilo_imagen" id="obtener_img" src="" >
                                </div>

                                <div class=" formulario__grupo-btn-enviar">
                                    <button  name="btn_agregar_productos" id="btn_agregar_productos" value="agregar_producto" class="formulario__btn " >Modificar</button>
                                </div>
                            </form>  
                            <div class="modal-footer">
                                <button  type="button" class="btn btn-warning mod_salir"   onclick="unselect()" data-dismiss="modal">Salir</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>       



            <br> <br>
            <table class="table table-hover table-bordered">
                <thead class="thead-dark   titulos">
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
                <tbody class="formula_tabla " id="tbl_productos">
                    <%
                        Producto producto = new Producto();
                        DefaultTableModel tabla = new DefaultTableModel();
                        tabla = producto.leer();
                        int cero = 0;
                        for (int t = 0; t < tabla.getRowCount(); t++) {
                            cero++;
                            out.println("<tr data-id=" + tabla.getValueAt(t, 0) + " data-id_pues=" + tabla.getValueAt(t, 9) + " data-id_img2=" + tabla.getValueAt(t, 10) + " >");
                            out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 2) + "</td>");
                            out.println("<td> <img src=" + tabla.getValueAt(t, 3) + " alt='imagen' class='estilo_imagen2'></td>");
                            out.println("<td>" + tabla.getValueAt(t, 4) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 5) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 6) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 7) + "</td>");
                            out.println("<td>" + tabla.getValueAt(t, 8) + "</td>");
                            out.println("</tr>");
                        }
                        out.println("<div class='contador_lbl'>" + "<h5 >" + "No. Productos " + cero + "</h5>" + "</div>");
                    %>
                </tbody>
            </table>
            <script src="https://kit.fontawesome.com/2c36e9b7b1.js" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script type = "text/javascript " src = "js/formulario_productos.js" > </script> 
        </div>
    </body>
</html>
