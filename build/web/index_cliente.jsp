<%-- 
    Document   : index_cliente
    Created on : 13/10/2021, 22:19:32
    Author     : joseg
--%>

<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="Modelo.Cliente"%>
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
        <link rel="stylesheet" href="css/estilo_cliente.css">
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

        <button type="button" class="  btn_form"  data-toggle="modal" data-target="#modal_cliente" onclick="Limpiar2()">
            Formulario
        </button>
        <div class="container-fluid  p-3 my-3  text-black">
            <div class="formulario_titulo">
                <b>   <h5>Formulario Clientes </h5></b>
            </div>
            <br>
            <div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" id="modal_cliente" role="dialog">
                <div class="modal-dialog modal-xl " >
                    <div class="modal-content formula_modal">
                        <div class="modal-body ">
                            <form action="src_cliente" class="formulario" method="post" id="formulario" >

                                <div class="formulario__grupo" id="grupo__txt_id">
                                    <label for="nombre" class="formulario__label">ID:</label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_id" id="txt_id" value="0" readonly>
                                    </div>
                                </div>
                                <div class="formulario__grupo" id="grupo__txt_nombre">
                                    <label for="nombre" class="formulario__label">Nombres: </label>
                                    <div class="formulario__grupo-input">

                                        <input type="text" class="formulario__input  " name="txt_nombre" id="txt_nombre"  pattern="[A-Z]{1}[a-zA-ZÀ-ÿ\s]{3,40}([ ][A-Z]{1}[a-zA-ZÀ-ÿ\s]{3,40})?"  placeholder="Nombres" required>
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Los nombres deben empezar con inicial mayuscula.</p>
                                </div>

                                <!-- Grupo: Apellido -->
                                <div class="formulario__grupo" id="grupo__txt_apellido">
                                    <label for="apellido" class="formulario__label">Apellidos: </label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_apellido" id="txt_apellido" pattern="[A-Z]{1}[a-zA-ZÀ-ÿ\s]{3,40}[ ][A-Z]{1}[a-zA-ZÀ-ÿ\s]{3,40}" placeholder="Apellidos" required>
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Los apellidos deben iniciar con iniciales mayuscula.</p>
                                </div>

                                <!-- Grupo: nit -->
                                <div class="formulario__grupo" id="grupo__txt_nit">
                                    <label for="nit" class="formulario__label">Nit: </label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_nit" id="txt_nit"   pattern="[0-9]{1,6}[-][0-9]{1}" placeholder="######-#" required>
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Nit Válido ######-#</p>
                                </div>

                                <!-- Genero -->

                                <div class="formulario__grupo" id="grupo_txt_genero">
                                    <label for="genero" class="formulario__label">Genero:</label>
                                    <div class="formulario__grupo-input">
                                        <input   name="gen" type="radio" id="uno" value="Masculino" required />Masculino           
                                        <input    name="gen"  type="radio" id="dos" value="Femenino"  />Femenino
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                </div>
                                <!-- Grupo: Teléfono -->
                                <div class="formulario__grupo" id="grupo__txt_telefono">
                                    <label for="telefono" class="formulario__label">Teléfono:</label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_telefono" id="txt_telefono" pattern="[+]{1}[0-9]{1,4}[ ][0-9]{7,14}" placeholder="+### ########" required>
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Extension del pais, numero de teléfono.</p>
                                </div>

                                <!-- Grupo: Correo Electronico -->
                                <div class="formulario__grupo" id="grupo__txt_correo">
                                    <label for="correo" class="formulario__label">Correo Electrónico</label>
                                    <div class="formulario__grupo-input">
                                        <input type="email" class="formulario__input " name="txt_correo" id="txt_correo" placeholder="correo@correo.com" required>
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">El correo solo puede contener letras, numeros, puntos, guiones y guion bajo.</p>
                                </div>

                                <div class=" formulario__grupo-btn-enviar">
                                    <button  name="btn_agregar" id="btn_agregar" value="agregar"    class="formulario__btn " >Agregar</button>
                                    <button  name="btn_modificar" id="btn_modificar" value="modificar" class="formulario__btn1" >Modificar</button>
                                    <button  name="btn_2" id="btn_2" onclick="return confirmar2();" value="eliminar"class="formulario__btn2" > Eliminar </button>
                                </div>
                                <button name="btn_eliminar" id="btn_eliminar"  value="eliminar"class="btn_falso" >  </button>
                                <br>
                            </form>                  
                            <div class="modal-footer">
                                <button type="button"  class="btn btn-warning mod_salir" onclick="recargar()"   data-dismiss="modal">Salir</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class= "container ">
                <table class="table table-hover table-bordered">
                    <thead class="thead-dark   titulos">
                        <tr>
                            <th>Nombres</th> 
                            <th>Apellidos</th>
                            <th>Nit</th>
                            <th>Genero</th>
                            <th>Teléfono</th>
                            <th>Correo Electronico</th>
                            <th>Fecha Ingreso</th>
                        </tr>
                    </thead>
                    <tbody class="formula_tabla" id="tbl_cliente">
                        <% 
                        Cliente cliente = new Cliente();
                        DefaultTableModel tabla = new DefaultTableModel();
                        tabla= cliente.leer3();
                        int cero =0;
                        for (int r=0; r<tabla.getRowCount(); r++){
                        cero++;  
                        out.println("<tr data-id=" + tabla.getValueAt(r,0) + " >");
                        out.println("<td>" + tabla.getValueAt(r,1) + "</td>");
                        out.println("<td>" + tabla.getValueAt(r,2) + "</td>");
                        out.println("<td>" + tabla.getValueAt(r,3) + "</td>");             
                        out.println("<td>" + tabla.getValueAt(r,4) + "</td>");
                        out.println("<td>" + tabla.getValueAt(r,5) + "</td>");     
                        out.println("<td>" + tabla.getValueAt(r,6) + "</td>");
                        out.println("<td>" + tabla.getValueAt(r,7) + "</td>");
                        out.println("</tr>");
                        }
                        out.println("<div class='contador_lbl'>"+"<h5 >"+"No. Clientes "+cero+"</h5>"+"</div>");        
                        %>
                    </tbody>
                </table>
            </div>
            <script src="https://kit.fontawesome.com/2c36e9b7b1.js" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script type = "text/javascript " src = "js/formulario_cliente.js" > </script> 
        </div>
    </body>
</html>
