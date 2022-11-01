function bienvenido(){
Swal.fire({
    title: "Bienvenido",
    imageUrl: 'Imagenes/exito.gif',
    imageWidth: 400,
    imageHeight: 386,
    imageAlt: 'Custom image',
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
    imageUrl: 'Imagenes/exito2.gif',
    imageWidth: 496,
    imageHeight: 278,
    imageAlt: 'Custom image',
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
    imageUrl: 'imagenes/error.gif',
    imageWidth: 400,
    imageHeight: 400,
    imageAlt: 'Custom image',
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
