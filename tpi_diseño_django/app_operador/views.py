from urllib.parse import urlencode
from django.shortcuts import render, redirect
from django.urls import reverse
from django.http import HttpRequest, HttpResponse, HttpResponseNotAllowed
from .controller import GestorRtaOperador
from .models import Llamada, Estado, Cliente, SubOpcionLlamada

# Create your views here.


def index(request):
    llamada = Llamada.objects.first()
    context = {
        'llamada': llamada,
    }
    print(llamada.getNombreCliente())
    return render(request, 'app_operador/base.html', context)


def gestor(request):

    if not Cliente.objects.exists():
        cliente = Cliente(dni='123456789', nombre='John Doe',
                          nroCelular='1234567890')
        cliente.save()
    else:
        cliente = Cliente.objects.first()

    if not SubOpcionLlamada.objects.exists():
        sub_opcion = SubOpcionLlamada(nombre='SubOpcion1', nroOrden=1)
        sub_opcion.save()
    else:
        sub_opcion = SubOpcionLlamada.objects.first()

    if not Estado.objects.exists():
        estado_iniciada = Estado(nombre='iniciada')
        estado_iniciada.save()

        estado_en_curso = Estado(nombre='en curso')
        estado_en_curso.save()

        estado_finalizada = Estado(nombre='finalizada')
        estado_finalizada.save()

    if not Llamada.objects.exists():
        estado_actual = Estado.objects.first()
        llamada_actual = Llamada(
            cliente=cliente, subOpcion=sub_opcion, estadoActual=estado_actual)
        llamada_actual.save()
    else:
        llamada_actual = Llamada.objects.first()
    # Continuar con el código existente ...
    # llamada_actual = Llamada.objects.filter(id=2).first()

    print(cliente)
    print(sub_opcion)
    print(llamada_actual)

    # Inicializo el gestor con una llamada aleatoria
    gestor = GestorRtaOperador(llamada_actual)

    #
    gestor.recibirLlamada()
    # llamadaEnCurso = gestor.getLlamadaEnCurso()

    # print(fechaHoraActual)
    # gestor.recibirLlamada()
    # print(llamadaEnCurso)
    return HttpResponse(f'Hello')


def verificar_validacion(request: HttpRequest):
    if request.method == 'POST':
        id_llamada = request.POST.get('id_llamada')
        nombre_cliente = request.POST.get('nombre_cliente')
        estado_llamada = request.POST.get('estado_llamada')

        params = {
            'id_llamada': id_llamada,
            'nombre_cliente': nombre_cliente,
            'estado_llamada': estado_llamada,
        }

        url = reverse('segundo_form')
        url_with_params = f'{url}?{urlencode(params)}'

        return redirect(url_with_params)
    else:
        return HttpResponseNotAllowed(['POST'])


def segundo_form(request: HttpRequest):
    id_llamada = request.GET.get('id_llamada')
    return HttpResponse(f'<h1>El id llamada es {id_llamada}</h1>')
