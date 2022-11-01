<%-- 
    Document   : login
    Created on : 20/10/2021, 20:22:57
    Author     : joseg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="css/elogin.css" type="text/css" media="all">
        <%
            response.setHeader("Cach-Control","no-cache, must-revalidate");
            response.setHeader("Pragma","no-cache");
            response.setDateHeader("Expires", 0);
        %>
    </head>
    <body>
        <div class="cuerpoFormulario" id="cuerpoFormulario">
            <form class="formularioLogin" id="formularioLogin" method="post" action="sr_iniciar_sesion">
                <h1>Inicio de Sesión</h1>
                <br>
                <%-- <p>Usuario:</p>--%>
                <div class="texto">
                     <i class="fas fa-user-circle"></i>
                    <input type="text" class="txt" id="txtUsuario" required="" name="txtUsuario" placeholder="Ingrese su usuario" autocomplete="off">
                </div>
                 <%--<p>Contraseña:</p>--%>
                <br>
                <br>
                <div class="texto">
                    <i class="fas fa-key"></i>
                    <input type="password" class="txt" id="txtContrasena" required="" name="txtContrasena" placeholder="Ingrese su contraseña" autocomplete="off">
                </div>     
                <br>
                <br>
                <input type="submit" value="Iniciar Sesion" class="btn">
                <br>
                <br>
                <input type="button" value="Limpiar datos" class="btn" onclick="borrarDatosLogin()">
            </form>
        </div>
                      
    </body>
    <script src="js/funcionesLogin.js"></script>
</html>