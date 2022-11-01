const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');
function Limpiar() {

    $("#txt_id").val(0);
    $("#txt_nombres").val('');
    $("#txt_apellidos").val('');
    $("#txt_direccion").val('');
    $("#txt_telefono").val('');
    $("#txt_dpi").val('');
    $("#txt_fn").val('');
    $("#drop_puestos").val(0);
    $("#txt_fn").val('');
    $("#txt_fni").val('');

}

function unselect() {
    document.querySelectorAll('[name=gen]').forEach((x) => x.checked = false);
}
function setRadio(obj)
{
    obj.checked = false;
}

$('#tbl_empleados').on('click', 'tr td', function (evt) {
    var target, id, id_pu, nombres, apellidos, direccion, telefono, dpi, genero, f_nacido, f_labor, f_ingreso;
    var unos, gemm;

    target = $(event.target);
    id = target.parent().data('id');
    id_pu = target.parent().data('id_pues');
    nombres = target.parent("tr").find("td").eq(0).html();
    apellidos = target.parent("tr").find("td").eq(1).html();
    direccion = target.parent("tr").find("td").eq(2).html();
    telefono = target.parent("tr").find("td").eq(3).html();
    dpi = target.parent("tr").find("td").eq(4).html();
    genero = target.parent("tr").find("td").eq(5).html();
    f_nacido = target.parent("tr").find("td").eq(6).html();
    f_labor = target.parent("tr").find("td").eq(7).html();
    f_ingreso = target.parent("tr").find("td").eq(8).html();
    gemm = genero;


    $("#txt_id").val(id);
    $("#txt_nombres").val(nombres);
    $("#txt_apellidos").val(apellidos);
    $("#txt_direccion").val(direccion);
    $("#txt_telefono").val(telefono);
    $("#txt_dpi").val(dpi);
    $("#txt_fn").val(f_nacido);
    $("#txt_fni").val(f_labor);
    $("#drop_puestos").val(id_pu);

    if (gemm === "Masculino") {
        unos = "uno";

    } else {
        unos = "dos";

    }
    $("#modal_empleado").modal('show');

    $("#" + unos).val(genero).click();

});



const expresiones = {

    nombre: /^[A-Z]{1}[a-zA-ÿ]{3,40}([ ][A-Z]{1}[a-zA-ÿ]{3,40})?$/, // Letras y espacios, pueden llevar acentos.
    apellido: /^[A-Z]{1}[a-zA-ZÀ-ÿ\s]{4,40}[ ][A-Z]{1}[a-zA-ZÀ-ÿ\s]{4,40}$/,
    correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
    telefono: /^[+]{1}[0-9]{1,4}[ ][0-9]{7,14}$/, // 7 a 14 numeros.
    nit: /^[0-9]{1,6}[-][0-9]{1}$/,
    direccion: /^[A-Z]{1}[a-zA-ZÀ-ÿ\s]{4,40}[ ][A-Z]{1}[a-zA-ZÀ-ÿ\s]{4,40}$/,
    dpi: /^[0-9]{4}[ ][0-9]{5}[ ][0-9]{4}$/
};

const campos = {
    nombre: false,
    apellido: false,
    correo: false,
    telefono: false,
    nit: false,
    direccion: false,
    dpi: false
};

const validarFormulario = (e) => {
    switch (e.target.name) {
        case "txt_nombres":
            validarCampo(expresiones.nombre, e.target, 'txt_nombres');
            break;
        case "txt_apellidos":
            validarCampo(expresiones.apellido, e.target, 'txt_apellidos');
            break;
        case "txt_direccion":
            validarCampo(expresiones.direccion, e.target, 'txt_direccion');
            break;
        case "txt_telefono":
            validarCampo(expresiones.telefono, e.target, 'txt_telefono');
            break;

        case "txt_dpi":
            validarCampo(expresiones.dpi, e.target, 'txt_dpi');
            break;
    }
};
  
const validarCampo = (expresion, input, campo) => {
    if (expresion.test(input.value)) {
        document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
        document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');
        document.querySelector(`#grupo__${campo} i`).classList.add('fa-check-circle');
        document.querySelector(`#grupo__${campo} i`).classList.remove('fa-times-circle');
        document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
        campos[campo] = true;
    } else {
        document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
        document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
        document.querySelector(`#grupo__${campo} i`).classList.add('fa-times-circle');
        document.querySelector(`#grupo__${campo} i`).classList.remove('fa-check-circle');
        document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
        campos[campo] = false;
    }
};


inputs.forEach((input) => {
    input.addEventListener('keyup', validarFormulario);
    input.addEventListener('blur', validarFormulario);
});

function recargar() {
   document.location.reload();
}


'use strict';
const btn_agregar = document.querySelector('#btn_agregar');
const btn_modificar = document.querySelector('#btn_modificar');

let validar = () => {
    var rad1 = $('input[name=gen]:checked').val();
   
    let inputs_requerido = document.querySelectorAll('#formulario [required]');
    let error = false;
    for (let i = 0; i < inputs_requerido.length; i++) {
        if (inputs_requerido[i].value === ''||(typeof(rad1) === "undefined")) {         
            error = true;
        } else {
            inputs_requerido[i].classList.remove('input_error2');

        }
    }
    return error;
};

let validar2 = () => {
    var rad1 = $('input[name=gen]:checked').val();
   
    let inputs_requerido2 = document.querySelectorAll('#formulario [required]');
    let error2 = false;
    for (let i = 0; i < inputs_requerido2.length; i++) {
        if (inputs_requerido2[i].value === ''||(typeof(rad1) === "undefined")) {         
            error2 = true;
        } else {
            inputs_requerido2[i].classList.remove('input_error2');

        }
    }
    return error2;
};

let obtener_datos=()=>{
 let error=validar();   
 if(error){
    error44();
    
    }else{
  correcto();
   }
 };

let obtener_datos2=()=>{
 let error2=validar2();   
 if(error2){
 error44();
 }else{
 correcto();
 }
 };
 
 function error44() {
    Swal.fire({
        icon: 'error',
        title: 'Error!!',
        html: '<h5 style=color:red><br><b>Debe Llenar los campos correctamente</b></h5>',
        // confirmButtonColor: '#a52a2a'
        showConfirmButton: true,
        confirmButtonColor: '#ff0000'
    });
}

function correcto() {
    Swal.fire({
        icon: 'success',
        title: 'Excelente!!',
        html: '<h5 style=color:lime><br><b>Datos Ingresados Correctamente</b></h5>',
        //   background: "#1e2122",
        showConfirmButton: false

    });
}
btn_agregar.addEventListener('click',obtener_datos);
btn_modificar.addEventListener('click',obtener_datos2);

function confirmar2(evt) {
     
    Swal.fire({
        title: 'Eliminar',
        text: "Desea eliminar el registro?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value === true) {
            Swal.fire({
                title: 'Eliminado',
                html: '<h5 style=color:lime><br><b>Datos Eliminados Correctamente!!</b></h5>',
                icon: 'success',
                showConfirmButton: false
            });

            $("#btn_eliminar").click();
        } else {
            Swal.fire({
                confirmButtonColor: '#d33',
                icon: 'error',
                title: 'Cancelado',
                text: 'Datos No Eliminados'
               
            });
        }
    });
    return false;
}