<%-- 
    Document   : menu
    Created on : 20/10/2021, 20:23:37
    Author     : joseg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Menu Desplegable</title>
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
            <li><a href="">Ventas</a>
                <ul>
                    <li><a href="index_cliente.jsp">Clientes</a></li>
                    <li><a href="index_empleado.jsp">Empleados</a>
                        <ul>
                            <li><a href="index_puesto.jsp">Puestos</a></li>
                        </ul>
                    </li>
                </ul>
            </li>

            <li><a href="">Reportes</a></li>

            <li><a href="">Compras</a>
                <ul>
                    <li><a href="index_prooveedores.jsp">Proveedores</a></li>
                </ul>
            </li>
        </ul>
    </div>
</body>
</html>