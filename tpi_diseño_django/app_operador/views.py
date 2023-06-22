from urllib.parse import urlencode
from django.shortcuts import render, redirect
from django.urls import reverse
from django.http import HttpRequest, HttpResponse, HttpResponseNotAllowed
from .controller import GestorRtaOperador
from .models import Llamada

# Create your views here.


def index(request):
    llamada = Llamada.objects.first()
    context = {
        'llamada': llamada,
    }
    print(llamada.getNombreCliente())
    return render(request, 'app_operador/base.html', context)


def gestor(request):
    gestor = GestorRtaOperador()
    # estadoEnCurso = gestor.buscarEstadoEnCurso()
    # fechaHoraActual = gestor.obtenerFechaHoraActual()
    # print(fechaHoraActual)
    gestor.recibirLlamada()
    # print(estadoEnCurso)
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
