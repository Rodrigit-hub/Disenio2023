from django.shortcuts import render
from django.http import HttpResponse
from .controller import GestorRtaOperador

# Create your views here.


def index(request):
    return render(request, 'app_operador/base.html')


def gestor(request):
    gestor = GestorRtaOperador()
    estadoEnCurso = gestor.buscarEstadoEnCurso()
    # print(estadoEnCurso)
    # return HttpResponse(f'{estadoEnCurso.getNombre()}')
