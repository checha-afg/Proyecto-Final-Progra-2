<%-- 
    Document   : index_empleado
    Created on : 13/10/2021, 22:21:00
    Author     : joseg
--%>

<%@page import="Modelo.Puesto" %>
<%@page import="Modelo.Empleado" %>
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://necolas.github.io/normalize.css/8.0.1/normalize.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet"> 

        <link rel="stylesheet" href="css/estilo_empleado.css">
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
                <li><a href="Maestro_compras.jsp">Compras</a>
                    <ul>
                        <li><a href="index_prooveedores.jsp">Proveedores</a></li>
                    </ul>
                </li>
                <li><a href="">Reportes</a></li>
                  <li>
                    <%
       response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
       if(session.getAttribute("txtUsuario")==null&&session.getAttribute("nombre")==null){
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
        <br>

        <button type="button" class="btn_form" data-toggle="modal" data-target="#modal_empleado" onclick="Limpiar()">
            Formulario
        </button>

        <div class="container-fluid p-3 my-3  text-black">

            <div class="formulario_titulo">
                <h5>Formulario Empleados </h5>
            </div>
            <br>
            <div class="modal fade"  data-backdrop="static" data-keyboard="false" tabindex="-1" id="modal_empleado" role="dialog">
                <div class="modal-dialog modal-xl">
                    <div class="modal-content formula_modal">
                        <div class="modal-body  ">

                            <form  action="index_puesto.jsp">
                                <input class="enlaze_puesto" type="submit" value="Puestos" />
                            </form>

                            <form action="src_empleado" method="post" class="formulario" class="form-group" id="formulario">
                                <div class="formulario__grupo" id="grupo_txt_id">
                                    <label for="lbl_id" class="formulario__label">ID:</label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_id" id="txt_id" value="0" readonly>
                                    </div>
                                </div>
                                <!-- Nombresss -->
                                <div class="formulario__grupo" id="grupo__txt_nombres">
                                    <label for="lbl_puesto" class="formulario__label">Nombres: </label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_nombres" id="txt_nombres" pattern="[A-Z]{1}[a-zA-ÿ]{3,40}([ ][A-Z]{1}[a-zA-ÿ]{3,40})?" placeholder="Nombres"   required>
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Formato: Iniciales Mayusculas </p>
                                </div>

                                <!-- Apellidos -->
                                <div class="formulario__grupo" id="grupo__txt_apellidos">
                                    <label for="lbl_apellidos" class="formulario__label">Apellidos: </label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_apellidos" id="txt_apellidos" pattern="[A-Z]{1}[a-zA-ZÀ-ÿ\s]{4,40}[ ][A-Z]{1}[a-zA-ZÀ-ÿ\s]{4,40}" placeholder="Apellidos"   required>
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Inciales Mayusculas </p>
                                </div>

                                <!-- Direccion -->
                                <div class="formulario__grupo" id="grupo__txt_direccion">
                                    <label for="lbl_direccion" class="formulario__label">Dirección: </label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_direccion" id="txt_direccion" pattern="[A-Z]{1}[a-zA-ZÀ-ÿ\s]{4,40}[ ][A-Z]{1}[a-zA-ZÀ-ÿ\s]{4,40}" placeholder="Direccion"  required>
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Lugar,Pais </p>
                                </div>

                                <!-- Telefono -->
                                <div class="formulario__grupo" id="grupo__txt_telefono">
                                    <label for="lbl_telefono" class="formulario__label">Telefono: </label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_telefono" id="txt_telefono" pattern="[+]{1}[0-9]{1,4}[ ][0-9]{7,14}" placeholder="+### ########"  required>
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">extension del pais, numero de telefono</p>
                                </div>

                                <!-- DPI-->
                                <div class="formulario__grupo" id="grupo__txt_dpi">
                                    <label for="lbl_dpi" class="formulario__label">DPI: </label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_dpi" id="txt_dpi" pattern="[0-9]{4}[ ][0-9]{5}[ ][0-9]{4}" placeholder="#### ##### ####"    required>
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Formato No valido</p>
                                </div>

                                <!-- Genero -->
                                <div class="formulario__grupo" id="grupo_txt_genero">
                                    <label for="genero" class="formulario__label">Genero:</label>
                                    <div class="formulario__grupo-input">
                                        <input   name="gen" type="radio" id="uno" value="Masculino" required />Masculino           
                                        <input    name="gen"  type="radio" id="dos" value="Femenino"  />Femenino

                                    </div>
                                </div>          

                                <div class="formulario__grupo" id="grupo__txt_fn">
                                    <label for="lbl_fn" class="formulario__label">Fecha Nacimiento:</label>
                                    <div class="formulario__grupo-input">
                                        <input class="formulario__input" type="date" name="txt_fn" id="txt_fn" required placeholder="yyyy-MM-dd">
                                    </div>
                                </div>


                                <div class="formulario__grupo" id="grupo__txt_fni">
                                    <label for="genero" class="formulario__label">Fecha Ingreso:</label>
                                    <div class="formulario__grupo-input">
                                        <input class="formulario__input" type="date" name="txt_fni" id="txt_fni" required placeholder="yyyy-MM-dd">
                                    </div>
                                </div>

                                <div class="formulario__grupo" id="grupo__txt_puesto">
                                    <label for="lbl_puesto" class="formulario__label">Puestos:</label>
                                    <div class="formulario__grupo-input">
                                        <select name="drop_puestos" class="formulario__input" id="drop_puestos" >
                                            <%
                                                Puesto puesto= new Puesto();
                                                HashMap<String,String> drop= puesto.seleccionar();
                                                for(String i:drop.keySet())
                                                {
                                                    out.println("<option value="+i+">"+drop.get(i)+ "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>

                                <div class=" formulario__grupo-btn-enviar">
                                    <button  name="btn_agregar" id="btn_agregar" value="agregar"    class="formulario__btn " >Agregar</button>
                                    <button  name="btn_modificar" id="btn_modificar" value="modificar" class="formulario__btn1" >Modificar</button>
                                    <button  name="btn_2" id="btn_2" onclick="return confirmar2();" value="eliminar"class="formulario__btn2" > Eliminar </button>
                                </div>
                                <button name="btn_eliminar" id="btn_eliminar"  value="eliminar"class="btn_falso" > </button>
                                <br>
                            </form>
                            <div class="modal-footer">
                                <button  type="button" class="btn btn-warning mod_salir" onclick="recargar()"  onclick="unselect()" data-dismiss="modal">Salir</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <table class="table table-hover table-bordered">
                <thead class="thead-dark   titulos">
                    <tr>
                        <th>Nombres</th> 
                        <th>Apellidos</th>
                        <th>Dirección</th>
                        <th>Teléfono</th>
                        <th>DPI</th>
                        <th>Genero</th>
                        <th>Fecha Nacimiento</th>
                        <th>Fecha Labor</th>
                        <th>Fecha Ingreso</th>
                        <th>Puesto</th>

                    </tr>
                </thead>
                <tbody class="formula_tabla" id="tbl_empleados">

                    <% 
                    Empleado empleado = new Empleado();
                    DefaultTableModel tabla = new DefaultTableModel();
                    tabla= empleado.leer3();
                    int cero=0;
                    for (int r=0; r<tabla.getRowCount(); r++){
                    cero++;
                    out.println("<tr data-id=" + tabla.getValueAt(r,0) + " data-id_pues=" + tabla.getValueAt(r,11) + " >");
                    out.println("<td>" + tabla.getValueAt(r,1) + "</td>");
                    out.println("<td>" + tabla.getValueAt(r,2) + "</td>");
                    out.println("<td>" + tabla.getValueAt(r,3) + "</td>");             
                    out.println("<td>" + tabla.getValueAt(r,4) + "</td>");
                    out.println("<td>" + tabla.getValueAt(r,5) + "</td>");     
                    out.println("<td>" + tabla.getValueAt(r,6) + "</td>");
                    out.println("<td>" + tabla.getValueAt(r,7) + "</td>");
                    out.println("<td>" + tabla.getValueAt(r,8) + "</td>");
                    out.println("<td>" + tabla.getValueAt(r,9) + "</td>");
                    out.println("<td>" + tabla.getValueAt(r,10) + "</td>");
                    out.println("</tr>");
                    }
                   out.println("<div class='contador_lbl'>"+"<h5 >"+"No. Empleados "+cero+"</h5>"+"</div>");
                    %>
                </tbody>
            </table>

            <script src="https://kit.fontawesome.com/2c36e9b7b1.js" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script type = "text/javascript " src = "js/formulario_empleado.js" > </script> 
        </div>
    </body>
</html>