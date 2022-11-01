function cargarElementoDinamicamente(url,elemento){
    var request = new XMLHttpRequest();
    request.open("GET",url,false);
    request.send(null);
    elemento.innerHTML = request.responseText;
}

function cargarValidarUsuario(){
    cargarElementoDinamicamente("validarUsuario.jsp",document.getElementById("contenidoDinamico"));
}

function cargarLogin(){
    cargarElementoDinamicamente("login.jsp",document.getElementById("contenidoDinamico"));
}