const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');
 
 $("#tbl_marcas").on("click", "tr td", function (evt)
    {
        var target, id, id_marca, marca;
        target = $(evt.target);
        id = target.parent().data("id");
        id_marca = target.parent().data("id_marca");
        marca = target.parent("tr").find("td").eq(0).html();

        $("#txt_id_marca").val(id);
        $("#txt_marca").val(marca);
$("#modal_marca").modal('show');
    });
    
    
    const expresiones = {

    nombre: /^[A-Z]{1}[a-zA-ZÀ-ÿ\s]{3,40}$/ // Letras y espacios, pueden llevar acentos.

};
const campos = {
    nombre: false
};
const validarFormulario = (e) => {
    switch (e.target.name) {
        case "txt_marca":
            validarCampo(expresiones.nombre, e.target, 'txt_marca');
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
const btn_agregar = document.querySelector('#btn_agregar_marcas');
const btn_modificar = document.querySelector('#btn_modificar_marcas');
let validar = () => {
    let inputs_requerido = document.querySelectorAll('#formulario [required]');
    let error = false;
    for (let i = 0; i < inputs_requerido.length; i++) {
        if (inputs_requerido[i].value === '') {
            error = true;
        } else {
            inputs_requerido[i].classList.remove('input_error2');
        }
    }
    return error;
};
let validar2 = () => {
    let inputs_requerido2 = document.querySelectorAll('#formulario [required]');
    let error2 = false;
    for (let i = 0; i < inputs_requerido2.length; i++) {
        if (inputs_requerido2[i].value === '') {
            error2 = true;
        } else {
            inputs_requerido2[i].classList.remove('input_error2');
        }
    }
    return error2;
};
let obtener_datos = () => {
    let error = validar();
    if (error) {
        error44();
    } else {

        correcto();
    }
};
let obtener_datos2 = () => {
    let error2 = validar2();
    if (error2) {
        error44();
    } else {
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
btn_agregar.addEventListener('click', obtener_datos);
btn_modificar.addEventListener('click', obtener_datos2);

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
                html: '<h5 style=color:lime><br><b>Datos Eliminados Correctamente!!"</b></h5>',
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