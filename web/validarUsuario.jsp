<%-- 
    Document   : validarUsuario
    Created on : 20/10/2021, 20:25:26
    Author     : joseg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="css/evalidar.css" media="all">
        <%
            response.setHeader("Cach-Control","no-cache, must-revalidate");
            response.setHeader("Pragma","no-cache");
            response.setDateHeader("Expires", 0);
        %>
    </head>
    <body>
        <div class="cuerpoFormulario" id="cuerpoFormulario">
            <form class="formularioAlta" id="formularioAlta" method="post" action="sr_guardar_login" autocomplete="off">
                <h1>Registro Usuario</h1>
                <br>
                <div class="texto">
                <i class="fas fa-user-edit"></i>
                <%-- <label>Codigo: </label>--%><input type="number" class="txt" id="txtCurp" onkeypress="return event.charCode >= 48" min="1" required="" onkeyup="generarUsuario()" name="txtCurp" placeholder="Codigo" pattern="[J]{1}[0-9]{3}">
                </div>
                <br>
                <br>
                <div class="texto">
                <i class="fas fa-file-signature"></i>
                <%-- <label>Nombre: </label>--%><input type="text" class="txt" id="txtNombre" required="" onkeyup="generarUsuario()" name="txtNombre" placeholder="Nombre" pattern="[A-Z]{1}[a-zA-ZÀ-ÿ\s]{2,20}([ ][A-Z]{1}[a-zA-ZÀ-ÿ\s]{2,20})?">
                </div>
                <br>
                <br>
                <div class="texto">
                <i class="fas fa-file-signature"></i>
                <%-- <label>Apellidos: </label>--%><input type="text" class="txt" id="txtApellidos" required="" onkeyup="generarUsuario()" name="txtApellidos" placeholder="Apellidos" pattern="[A-Z]{1}[a-zA-ZÀ-ÿ\s]{2,20}([ ][A-Z]{1}[a-zA-ZÀ-ÿ\s]{2,20})?">
                </div>
                <br>
                <br>
                <div class="texto">
                <i class="fas fa-unlock"></i>
               <%--  <label>Contraseña: </label>--%><input type="password" class="txt" id="txtContrasena" required="" onkeyup="verificarContrasena()" placeholder="Contraseña" >
                </div> 
               <br>
                <br>
                <div class="texto">
                <i class="fas fa-lock"></i> 
                <%-- <label>Confirme la contraseña: </label>--%><input type="password" class="txt " id="txtRepetirContrasena" required="" onkeyup="verificarContrasena()" name="txtContrasena" placeholder="Verificar Contraseña" >
                </div>
                <br>
                <label class="avisoContrasena" id="avisoContrasena" ></label>
                <br>
                <label>Usuario Generado: </label><input type="text" class="txt" id="txtUsuarioGeneradoAutomaticamente" required="" readonly="" name="txtUsuarioGeneradoAutomaticamente" placeholder="Usuario Generado">
                <br>
                <br> <br>
                <div class="Boton_44">
                <input type="submit" value="Enviar Datos" class="btn" id="btnEnviarDatos" disabled="">
                <input type="button" value="Borrar datos" class="btn" id="btnBorrardatos" onclick="borrarDatosValidarUsuario()">
            </div>
                </form>
            <script src="js/funcionesValidarUsuario.js"></script>
        </div>
    </body>
</html>
