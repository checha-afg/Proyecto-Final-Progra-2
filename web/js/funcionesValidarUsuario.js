function borrarDatosValidarUsuario(){
    document.getElementById("formularioAlta").reset();
    var avisoContrasena = document.getElementById("avisoContrasena");
    var btnEnviarDatos = document.getElementById("btnEnviarDatos");
    avisoContrasena.innerHTML = "";
    avisoContrasena.style.color ="black";
    btnEnviarDatos.disabled = true;
}

function generarUsuario(){
    var curp = document.getElementById("txtCurp");
    var nombre = document.getElementById("txtNombre");
    var apellido = document.getElementById("txtApellidos");
    var usuarioGenerado = document.getElementById("txtUsuarioGeneradoAutomaticamente");
    var combinacion =  nombre.value.slice(0,3) + apellido.value.slice(0,3) +"C"+ curp.value +"@onitech.com";
    usuarioGenerado.value = combinacion;
    if(nombre.value.length===0||apellido.value.length===0||curp.value.length===0){
        usuarioGenerado.value="";
    }
}

function verificarContrasena(){
    var txtContrasena = document.getElementById("txtContrasena");
    var txtRepetirContrasena = document.getElementById("txtRepetirContrasena");
    var avisoContrasena = document.getElementById("avisoContrasena");
    var btnEnviarDatos = document.getElementById("btnEnviarDatos");
    var nombre = document.getElementById("txtNombre");
    var apellido = document.getElementById("txtApellidos");
    btnEnviarDatos.disabled = true;
    
    if(txtContrasena.value.length === 0 || txtRepetirContrasena.value.length === 0 ){
        avisoContrasena.innerHTML = "Necesita llenar ambos campos de contraseña";
        avisoContrasena.style.color ="orange";
        btnEnviarDatos.disabled = true;
    }else if(txtContrasena.value !== txtRepetirContrasena.value){
        avisoContrasena.innerHTML = "Las contraseñas no coiciden";
        avisoContrasena.style.color ="red";
        btnEnviarDatos.disabled = true;
    }else{
        avisoContrasena.innerHTML = "Contraseña válida";
        avisoContrasena.style.color ="green";
        btnEnviarDatos.disabled = false;
    }
}

function hola(){
    var avisoContrasena = document.getElementById("avisoContrasena");
    avisoContrasena.innerHTML = "Hola";
    avisoContrasena.style.color ="green";
}