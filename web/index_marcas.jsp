<%-- 
    Document   : index_marcas
    Created on : 20/10/2021, 20:18:33
    Author     : joseg
--%>

<%@page import="Modelo.Marcas"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Marcas</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/estilo_marca.css">
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
                <li >
                    <%
       response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
       if(session.getAttribute("txtUsuario")==null&&session.getAttribute("nombre")==null){
           response.sendRedirect("index.html");
       } %>
                    <a > 
                        <form action="sr_cerrar_sesion" >
                            <input class="cerrar_sesion" type="submit"  value="Cerrar Sesion">
                        </form>
                    </a>

                </li>
            </ul>
        </div>
        <br>
        <button type="button" class="  btn_form"  data-toggle="modal" data-target="#modal_marca" onclick="Limpiar2()">
            Formulario
        </button>
        <div class="container p-3 my-3 text-black">
            <div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" id="modal_marca" role="dialog">
                <div class="modal-dialog modal-xl ">
                    <div class="modal-content formula_modal">
                        <div class="modal-body ">
                            <form  action="index_producto.jsp">
                                <input class="enlaze_producto" type="submit" value="Productos" />
                            </form>
                            <form action="src_marca" method="post" class="form-group" name="formulario" id="formulario" >
                                <div class="formulario__grupo" id="grupo_txt_id_marca">
                                    <label for="lbl_id" class="formulario__label">ID:</label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_id_marca" id="txt_id_marca" value="0" readonly>
                                    </div>
                                </div>

                                <div class="formulario__grupo" id="grupo__txt_marca">
                                    <label for="lbl_marca" class="formulario__label">Marca: </label>
                                    <div class="formulario__grupo-input">
                                        <input type="text" class="formulario__input" name="txt_marca" id="txt_marca" placeholder="Nombre de la marca" pattern="[A-Z]{1}[a-zA-ZÀ-ÿ\s]{3,40}" required>
                                        <i class="formulario__validacion-estado fas fa-times-circle"></i>
                                    </div>
                                    <p class="formulario__input-error">Solo esta permitido letras </p>
                                </div>
                                <br>

                                <center>    
                                    <button  name="btn_agregar_marcas" id="btn_agregar_marcas" value="agregar_marca"    class="formulario__btn " >Agregar</button>
                                    <button  name="btn_modificar_marcas" id="btn_modificar_marcas" value="modificar_marca" class="formulario__btn1" >Modificar</button>
                                    <button  name="btn_2" id="btn_2" onclick="return confirmar2();" value="eliminar"class="formulario__btn2" > Eliminar </button>
                                </center>
                                <button name="btn_eliminar_marcas" id="btn_eliminar"  value="eliminar_marca"class="btn_falso" > </button>

                            </form>   
                            <div class="modal-footer">
                                <button type="button" class="btn btn-warning mod_salir" onclick="recargar()"  data-dismiss="modal">Salir</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="formulario_titulo">
                <h5>Formulario Marcas</h3>
            </div>
            <br>
            <div class= "container ">
                <table class="table table-hover table-bordered">
                    <thead class="thead-dark   titulos">
                        <tr>
                            <th>Marcas</th>
                        </tr>
                    </thead>
                    <tbody class="formula_puesto" id="tbl_marcas">
                        <%
                        Marcas marca = new Marcas();
                        DefaultTableModel tabla = new DefaultTableModel();
                        tabla = marca.leer();
                        int cero=0;
                        for (int t=0;t <tabla.getRowCount();t++){
                            cero++;
                            out.println("<tr data-id="+tabla.getValueAt(t, 0)+" data-id_marca="+tabla.getValueAt(t, 1)+">");
                            out.println("<td>"+ tabla.getValueAt(t, 1)+"</td>");
                            //out.println("<td>"+tabla.getValueAt(t,9)+"</td>");
                            out.println("</tr>");
                        }
                           out.println("<div class='contador_lbl'>"+"<h5 >"+"Marcas Encontradas: "+cero+"</h5>"+"</div>");
                        %>
                    </tbody>
                </table>
            </div>
            <script src="https://kit.fontawesome.com/2c36e9b7b1.js" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script type = "text/javascript " src = "js/formulario_marca.js" > </script> 
        </div>
    </body>
</html>