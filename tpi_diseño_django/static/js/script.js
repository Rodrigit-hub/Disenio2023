const formulario = document.querySelector('#form_verificar_validacion')
const csrfToken = document.querySelector('[name=csrfmiddlewaretoken]').value

formulario.addEventListener('submit', e => {
  e.preventDefault()
  const idLlamada = document.querySelector('#id_llamada').value
  const nombreCliente = document.querySelector('#nombre_cliente').value
  const estadoLlamada = document.querySelector('#estado_llamada').value
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
    estadoLlamada,
    categoriaSeleccionada,
    opcionSeleccionada,
    subOpcionSeleccionada,
    validacion,
    validacionData,
  }

  fetch('/verificar_validacion', {
    method: 'POST',
    headers: { 'X-CSRFToken': csrfToken },
    body: JSON.stringify(body),
  })

  console.log(body)
})
