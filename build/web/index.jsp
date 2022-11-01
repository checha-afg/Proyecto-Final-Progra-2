<%-- 
    Document   : index
    Created on : 16/09/2022, 18:42:39
    Author     : chech
--%>

<%@page import="Modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>LogIn</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/estilos.css" media="all">
    </head>
    <body onpageshow="cargarLogin()">
        <nav class="menu" id="menu">
            <img src="Imagenes/umg.png" alt="logo" class="logo" align="left">
            <ul>
                <li><a href="#" onclick="cargarLogin()">Iniciar Sesión</a></li>
                <li><a href="#" onclick="cargarValidarUsuario()">Registrarse</a></li>
            </ul>
        </nav>
        
        <section class="contenidoDinamico" id="contenidoDinamico">
            
        </section>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
    <script src="js/funcionesIndex.js"></script>
    <script src="js/funcionesLogin.js"></script>
    <script src="js/funcionesValidarUsuario.js"></script>
</html>

