const formulario = document.querySelector('#form_verificar_validacion')
const formularioFinalizar = document.querySelector('#form_finalizar_validacion')

formulario?.addEventListener('submit', e => {
  e.preventDefault()
  const idLlamada = document.querySelector('#id_llamada').value
  const nombreCliente = document.querySelector('#nombre_cliente').value
  const categoriaSeleccionada = document.querySelector(
    '#categoria_seleccionada'
  ).value
  const opcionSeleccionada = document.querySelector(
    '#opcion_seleccionada'
  ).value
  const subOpcionSeleccionada = document.querySelector(
    '#sub_opcion_seleccionada'
  ).value
  const validacion = document
    .querySelector('#validacion_name')
    .firstChild.data.trim()
  const validacionData = document.querySelector('#validacion_data').value

  const body = {
    idLlamada,
    nombreCliente,
    categoriaSeleccionada,
    opcionSeleccionada,
    subOpcionSeleccionada,
    validacion,
    validacionData,
  }
  const csrfToken = document.querySelector('[name=csrfmiddlewaretoken]').value
  fetch('/verificar_validacion', {
    method: 'POST',
    headers: { 'X-CSRFToken': csrfToken },
    body: JSON.stringify(body),
  })
    .then(response => response.json())
    .then(data => {
      if (data.status === 'exito') {
        window.location.href = '/informacion_valida'
      } else {
        window.location.href = '/informacion_invalida'
      }
    })
})

formularioFinalizar?.addEventListener('submit', e => {
  e.preventDefault()
  const descripcionLlamada = document.querySelector(
    '#descripcion_llamada'
  ).value

  const body = {
    descripcionLlamada,
  }

  const csrfToken = document.querySelector('[name=csrfmiddlewaretoken]').value
  fetch('/finalizar_validacion', {
    method: 'POST',
    headers: { 'X-CSRFToken': csrfToken },
    body: JSON.stringify(body),
  })
    .then(() => {})
    .then(() => {
      window.location.href = '/finalizar_validacion'
    })
})
