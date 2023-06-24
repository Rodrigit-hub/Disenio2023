import json
from django.shortcuts import render
from django.http import HttpRequest, HttpResponse, JsonResponse
from .controller import GestorRtaOperador
from .models import Llamada, Estado, Cliente, SubOpcionLlamada, CategoriaLlamada, OpcionLlamada, InformacionCliente, Validacion
# from .forms import VerificarValidacionForm

#     Consultas de saldo: Permite a los usuarios verificar el saldo actual de su tarjeta de crédito.

# Pagos: Proporciona opciones para realizar pagos de la tarjeta de crédito, ya sea mediante transferencias bancarias, pagos en línea u otros métodos disponibles.

# Historial de transacciones: Permite a los usuarios acceder al historial de transacciones realizadas con la tarjeta de crédito, incluyendo detalles como la fecha, el monto y la descripción de cada transacción.

# Bloqueo y desbloqueo de la tarjeta: Permite a los usuarios bloquear o desbloquear temporalmente su tarjeta de crédito en caso de pérdida, robo o sospecha de uso no autorizado.

# Solicitud de aumento de límite de crédito: Permite a los usuarios solicitar un aumento en el límite de crédito de su tarjeta.

# Cambio de PIN: Permite a los usuarios cambiar el número de identificación personal (PIN) asociado a su tarjeta de crédito.

# Atención al cliente: Proporciona opciones para comunicarse con un representante de servicio al cliente en caso de consultas, problemas o asistencia adicional.


# Esta es la ruta raíz a la cual vamos a pasar la llamada
# para ser procesada por el operador
# SE ENCARGA DE QUE EXISTA UNA LLAMADA ANTES DE INSTANCIAR EL GESTOR

# Inicializo el gestor con la llamada obtenida en el proceso de arriba
gestor = GestorRtaOperador()


def index(request):
    # En caso de que no haya ningún cliente cargado en la base de datos
    # Instanciamos uno y lo guardamos
    if not Cliente.objects.exists():
        cliente = Cliente(
            dni='123456789',
            nombre='John Doe',
            nroCelular='1234567890'
        )
        cliente.save()
    else:
        # En caso de que haya cliente cargado
        # Traemos el primero
        cliente = Cliente.objects.first()

    # En caso de que no haya ninguna sub opción cargada en la base de datos
    # Instanciamos una y la guardamos
    # Atención al cliente
    # Bloqueo y desbloqueo de tarjeta
    # Historial de transacciones

    # Categoría
    # Informar un robo

    # OPCIONES
    # Informar un robo y solicitar una nueva tarjeta (1)
    # Informar un robo y anular la tarjeta (2)

    # SUBOPCIONES
    # Si cuenta con los datos de la tarjeta (1)
    # Si no cuenta con los datos de la tarjeta (2)
    # Si deasea comunicarse con un responsable de atención al cliente
    # Finalizar llamada

    # En caso de que no estén los estados cargados
    # Los instanciamos a todos y los guardamos
    if not Estado.objects.exists():
        estado_iniciada = Estado(nombre='iniciada')
        estado_iniciada.save()

        estado_en_curso = Estado(nombre='en curso')
        estado_en_curso.save()

        estado_finalizada = Estado(nombre='finalizada')
        estado_finalizada.save()

    # En caso de que no haya llamada en la bd
    # Instanciamos una, pasándole cliente, subOpcion y estadoActual, y la guardamos
    if not Llamada.objects.exists():
        estadoActual = Estado.objects.first()
        llamadaActual = Llamada(
            cliente=cliente,
            estadoActual=estadoActual,
        )
        llamadaActual.save()
    else:
        # En caso de que haya alguna llamada
        # Traemos la primera
        llamadaActual = Llamada.objects.first()

    if not CategoriaLlamada.objects.exists():
        categoriaSeleccionada = CategoriaLlamada(
            audioMensajeOpciones='Informar un robo y solicitar una nueva tarjeta (1) - Informar un robo y anular la tarjeta (2)',
            mensajeOpciones='',
            nroOrden=1,
            nombre='Informar un robo'
        )
        categoriaSeleccionada.save()
        categoriaSeleccionada.llamada.set([llamadaActual])
    else:
        # En caso de que haya sub opción cargada
        # Traemos la primera
        categoriaSeleccionada = CategoriaLlamada.objects.first()

    if not OpcionLlamada.objects.exists():
        opcionSeleccionada = OpcionLlamada(
            audioMensajeSubOpciones='Si cuenta con los datos de la tarjeta (1) - Si no cuenta con los datos de la tarjeta (2) - Si deasea comunicarse con un responsable de atención al cliente- Finalizar llamada',
            mensajeSubOpciones='',
            nroOrden=1,
            nombre='Informar un robo y solicitar una nueva tarjeta',
            categoria=categoriaSeleccionada
        )
        opcionSeleccionada.save()
        opcionSeleccionada.llamada.set([llamadaActual])
    else:
        # En caso de que haya sub opción cargada
        # Traemos la primera
        opcionSeleccionada = OpcionLlamada.objects.first()

    if not SubOpcionLlamada.objects.exists():
        subOpcionSeleccionada = SubOpcionLlamada(
            nroOrden=1,
            nombre='Si cuenta con los datos de la tarjeta',
            opcion=opcionSeleccionada
        )
        subOpcionSeleccionada.save()
        subOpcionSeleccionada.llamada.set([llamadaActual])

    else:
        # En caso de que haya sub opción cargada
        # Traemos la primera
        subOpcionSeleccionada = SubOpcionLlamada.objects.first()

    if not Validacion.objects.exists():
        validacion = Validacion(
            audioMensajeValidacion='',
            nombre='Fecha de naciemiento',
            subOpcion=subOpcionSeleccionada
        )
        validacion.save()
    else:
        # En caso de que haya sub opción cargada
        # Traemos la primera
        validacion = Validacion.objects.first()

    if not InformacionCliente.objects.exists():
        tipoInfomacion = InformacionCliente(
            datoAValidar='11/09/2000',
            validacion=validacion,
            cliente=cliente,
        )
        tipoInfomacion.save()
    else:
        # En caso de que haya sub opción cargada
        # Traemos la primera
        tipoInfomacion = InformacionCliente.objects.first()

    # Ya instanciado el gestor, debería comenzar el CU
    # Probablemente haya que pasarle más cosas en un futuro
    gestor.recibirLlamada(llamadaActual)
    # datosLlamada = gestor.obtenerDatosLlamada()
    validaciones = gestor.buscarValidaciones()
    gestor.finalizarLlamada()

    # Context creado para enviar los datos al html para poder ser visualizados por el operador
    context = {
        'llamada': llamadaActual,
        'cliente': cliente,
        'categoria': categoriaSeleccionada,
        'opcion': opcionSeleccionada,
        'subOpcion': subOpcionSeleccionada,
        'validaciones': validaciones
    }

    return render(request, 'app_operador/base.html', context)


def verificar_validacion(request: HttpRequest):
    data = json.loads(request.body)
    id_llamada = data.get('idLlamada')
    validacion = data.get('validacion')
    validacion_data = data.get('validacionData')

    esInformacionValida = gestor.validarInformacionCliente(
        id_llamada=id_llamada,
        validacion=validacion,
        validacion_data=validacion_data
    )

    if esInformacionValida:
        return JsonResponse({"status": "Validado con éxito"})
    else:
        return JsonResponse({"status": "Información inválida"})
