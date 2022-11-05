/* global Swal */

function bienvenido(){
Swal.fire({
    title: "Bienvenido",
    imageUrl: 'C:\Users\chech\OneDrive\Escritorio\Proyecto Final Progra2\web\Imagenes\hola.gif',
    imageWidth: 250,
    imageHeight: 250,
    imageAlt: 'Bienvenido',
    //icon: "success",
    /*showClass: {
    popup: 'animate__animated animate__fadeInDown'
    },
    hideClass: {
        popup: 'animate__animated animate__fadeOutUp'
    },*/
    backdrop: `
    rgba(0,0,128,0.5)
    url("Imagenes/nyancat.gif")
    left top
    no-repeat
  `

}).then((result) => {
  if (result.isConfirmed) {
    location ="paginaInicio.jsp";
  }
  else{
      location ="paginaInicio.jsp";
  }
});    
}

function creado(){
Swal.fire({
    title: "Exito",
    text: 'Usuario creado correctamente',
    imageUrl: 'Imagenes/registrado.gif',
    imageWidth: 496,
    imageHeight: 278,
    imageAlt: 'Registrado',
    //icon: "success",
    showClass: {
    popup: 'animate__animated animate__fadeInDown'
    },
    hideClass: {
        popup: 'animate__animated animate__fadeOutUp'
    }

}).then((result) => {
  if (result.isConfirmed) {
    location ="index.html";
  }
  else{
      location ="index.html";
  }
});    
}

function error(){
    Swal.fire({
    title: "Error...",
    text: 'Al parecer algo ha salido mal',
    imageUrl: 'Imagenes/fallo.gif',
    imageWidth: 498,
    imageHeight: 280,
    imageAlt: 'Error',
    //icon: "error",
    showClass: {
    popup: 'animate__animated animate__fadeInDown'
    },
    hideClass: {
        popup: 'animate__animated animate__fadeOutUp'
    }

}).then((result) => {
  if (result.isConfirmed) {
    location ="index.html";
  }
  else{
      location ="index.html";
  }
});    
}
