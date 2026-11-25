const modalEliminar = document.getElementById('modalEliminar');

if (modalEliminar) {
    modalEliminar.addEventListener('show.bs.modal', (event) => {
        const boton = event.relatedTarget;
        const formulario = modalEliminar.querySelector('#formularioEliminar');

        formulario.action = boton.dataset.url;
    });
}